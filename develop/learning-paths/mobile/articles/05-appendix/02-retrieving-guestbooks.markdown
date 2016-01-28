# Retrieving Guestbooks [](id=retrieving-guestbooks)

Now that you know this app's basic structure and the basics of making calls with
the Guestbooks Mobile SDK, you're ready to retrieve guestbooks from the server.
To retrieve guestbooks in the app, you'll follow these steps:

1. Create the model class for the guestbooks. You need this class so you can 
   transform the JSON received from the portlet into guestbook model objects 
   that you can work with in the app. 
 
2. Prepare the `GuestbooksActivity` class to receive guestbook model objects.

3. Create a callback class that makes the service call asynchronously in a 
   background thread, transforms the JSON results into guestbook model objects, 
   and then sends those objects back to `GuestbooksActivity`.

4. Using the callback class, make the service call from `GuestbooksActivity`. 

You'll get started with the first step, creating the model class for guestbooks.

## Creating the Model Class for Guestbooks [](id=creating-the-model-class-for-guestbooks)

The Guestbook Mobile SDK returns guestbooks from the portlet in a `JSONArray` 
that contains each guestbook in a `JSONObject`. To work efficiently with these 
guestbooks, you need a way of transforming them into proper guestbook objects.
You'll do this via a model class. First, create a new package called `model`
inside the `com.liferay.docs.liferayguestbook` package. Inside this new `model`
package, create a new class called `GuestbookModel`. Replace the
`GuestbookModel` class's contents with the following code: 

    package com.liferay.docs.liferayguestbook.model;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.Serializable;
    import java.util.Date;

    public class GuestbookModel implements Serializable {

        private long _guestbookId;
        private long _groupId;
        private long _companyId;
        private long _userId;
        private String _userName;
        private long _createDate;
        private long _modifiedDate;
        private String _name;

        public GuestbookModel(JSONObject json) throws JSONException {
            _guestbookId = json.getLong("guestbookId");
            _groupId = json.getLong("groupId");
            _companyId = json.getLong("companyId");
            _userId = json.getLong("userId");
            _userName = json.getString("userName");
            _createDate = json.getLong("createDate");
            _modifiedDate = json.getLong("modifiedDate");
            _name = json.getString("name");
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof GuestbookModel)) {
                return false;
            }

            GuestbookModel guestbook = (GuestbookModel) obj;

            return (_guestbookId == guestbook.getGuestbookId());
        }

        @Override
        public String toString() {
            return _name;
        }

        public long getGuestbookId() {
            return _guestbookId;
        }

        public long getGroupId() {
            return _groupId;
        }

        public long getCompanyId() {
            return _companyId;
        }

        public long getUserId() {
            return _userId;
        }

        public String getUserName() {
            return _userName;
        }

        public Date getCreateDate() {
            Date createDate = new Date(_createDate);
            return createDate;
        }

        public Date getModifiedDate() {
            Date modifiedDate = new Date(_modifiedDate);
            return modifiedDate;
        }

        public String getName() {
            return _name;
        }
    }

This class creates `GuestbookModel` objects that effectively represent 
`Guestbook` objects in the portlet. It does so by retrieving `Guestbook` 
parameters from the `JSONObject` returned by the Mobile SDK's remote service 
calls. The constructor does this by using the `getLong` and `getString` methods. 
To see how the `Guestbook` parameters are defined in the portlet, see the 
[Liferay MVC Learning Path article on Service Builder](/develop/learning-paths/mvc/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only parameters you really need in this class are `guestbookId` and 
`name`. Because you might need the rest later, however, it's best to add 
support for all of them now. 

You should also note the `toString` method in this class. It's very simple; it
only returns a guestbook's name. It's very important, though. Android calls
`toString` to render objects in the navigation drawer. If `toString` isn't
defined for an object, the drawer shows strings with the object's full package
path and internal ID. In other words, Android displays illegible text if you
don't define `toString` here. By defining `toString` to return each
`GuestbookModel`'s name, you're telling Android to show each guestbook's name in
the drawer. 

Next, you'll prepare `GuestbooksActivity` to recieve `GuestbookModel` objects. 

## Preparing GuestbooksActivity for Guestbooks [](id=preparing-guestbooksactivity-for-guestbooks)

Before making the server call, you need to prepare the `GuestbooksActivity` 
class to handle `GuestbookModel` objects. Specifically, you need to give the 
callback class you'll create in a moment a way to pass `GuestbookModel` objects 
to `GuestbooksActivity`. 

First, add a `_guestbooks` variable for these objects: 

    private List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();

After adding this variable, add the following imports:

    import com.liferay.docs.liferayguestbook.model.GuestbookModel;
    import java.util.ArrayList;
    import java.util.List;

Next, add the following `reloadGuestbooks` method:

    public void reloadGuestbooks(List<GuestbookModel> guestbooks) {
        _guestbooks.clear();
        _guestbooks.addAll(guestbooks);
    }

This method replaces any existing content in `_guestbooks` with the list of 
`GuestbookModel` objects supplied to it. You'll use `reloadGuestbooks` in the 
callback class to pass the `GuestbookModel` objects to `GuestbooksActivity`. Now 
you're ready to create this callback class. 

## Creating a Callback Class [](id=creating-a-callback-class)

Since Android doesn't allow network requests from its main UI thread, you must 
make them from another thread by creating a callback class that extends the 
Mobile SDK's 
[`GenericCallback`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/main/java/com/liferay/mobile/android/callback/typed/GenericCallback.java) 
class. See 
[Android's documentation](http://developer.android.com/guide/components/processes-and-threads.html#Threads) 
for more information on threading. At this point, you might be saying, "Oh no, 
threading in mobile apps? That sounds complicated!" Fear not! The Mobile SDK's 
`GenericCallback` class hides much of the added complexity. 

To create the callback class for retrieving guestbooks, first create a new 
package called `callback` in `com.liferay.docs.liferayguestbook`. Then create a 
new class in the `callback` package called `GetGuestbooksCallback`. Replace the 
code in the class with this code: 

    package com.liferay.docs.liferayguestbook.callback;

    import android.widget.Toast;

    import com.liferay.docs.liferayguestbook.GuestbooksActivity;
    import com.liferay.docs.liferayguestbook.model.GuestbookModel;
    import com.liferay.mobile.android.callback.typed.GenericCallback;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.List;

    public class GetGuestbooksCallback extends GenericCallback<List<GuestbookModel>> {

        private GuestbooksActivity _activity;

        public GetGuestbooksCallback(GuestbooksActivity activity) {
            _activity = activity;
        }

        @Override
        public void onFailure(Exception e) {
            String message = "Couldn't get guestbooks " + e.getMessage();

            Toast.makeText(_activity, message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(List<GuestbookModel> guestbooks) {
            _activity.reloadGuestbooks(guestbooks);
        }

        @Override
        public List<GuestbookModel> transform(Object obj) throws Exception {
            List<GuestbookModel> guestbooks = new ArrayList<GuestbookModel>();

            JSONArray jsonArray = (JSONArray)obj;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                guestbooks.add(new GuestbookModel(json));
            }

            return guestbooks;
        }
    }

This class is small, but it does a great deal. First, you should note that this 
class has a `GuestbooksActivity` instance as its only variable. This is so it 
can refer results back to `GuestbooksActivity`, which runs in Android's main UI 
thread. The `GetGuestbooksCallback`'s constructor thus does only one thing: it 
sets this variable. Next, the `onFailure` and `onSuccess` methods are 
overridden. As you probably guessed, `onFailure` is called when the request 
fails, while `onSuccess` is called when it succeeds. In this example, 
`onFailure` displays a toast message with the error. The `onSuccess` method 
sends the guestbooks to `GuestbooksActivity` by calling the activity's 
`reloadGuestbooks` method. Last but not least is the overridden `transform` 
method. Because guestbooks initially come back from the portlet as JSON, you 
need this `transform` method to convert them into a `List` of `GuestbookModel` 
objects. It's this `List` that's fed to the `onSuccess` method. You're probably 
starting to see that `reloadGuestbooks` is an important method. It receives 
guestbooks for processing in the app's main UI thread. 

Now that you have everything you need to retrieve guestbooks from the Guestbook 
portlet, you're ready to make the server call. 

## Making the Server Call [](id=making-the-server-call)

You must make the server call by using an instance of the Guestbook Mobile SDK 
service that contains the service method you want to call. To get the 
guestbooks, you'll create a `GuestbookService` instance and then call its 
`getGuestbooks` method. You must create this service instance by using an 
authenticated session that has a callback set to it. The previous article 
describes the basics of this. Now it's time to get specific! 

Although the callback class routes the call asynchronously through a background 
thread, you still issue the call from `GuestbooksActivity`. Add the following 
`getGuestbooks()` method to `GuestbooksActivity`: 

    protected void getGuestbooks() {
        Session session = SessionContext.createSessionFromCurrentSession();
        GetGuestbooksCallback callback = new GetGuestbooksCallback(this);
        session.setCallback(callback);

        GuestbookService service = new GuestbookService(session);
        try {
            service.getGuestbooks(LiferayServerContext.getGroupId());
        }
        catch (Exception e) {
            String message = "Couldn't get guestbooks " + e.getMessage();
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

Since the Mobile SDK requires an authenticated session to communicate with the 
portal, you first use `SessionContext.createSessionFromCurrentSession()` to 
create a session from the pre-existing session in the app. You might now be 
thinking, "Whoa there! I haven't created a session yet!" But actually, you have; 
you just didn't know it. Successful authentication with Login Screenlet creates 
a session that you can access with Screens's `SessionContext` class. Because 
you're counting on that session, however, you *must* use the Login Screenlet. 

You then create a new `GetGuestbooksCallback` instance and set it as the 
session's callback. This ensures that any service calls made with the session go 
through the callback. Next, you use the session to create a `GuestbookService` 
instance. In the `try` block, you then call the service's `getGuestbooks` method 
with the group ID (site ID) of the site you want to retrieve guestbooks from. 
You get the group ID from the `LiferayServerContext` class--another Screens 
class--that lets you retrieve the values you set in `server_context.xml`. As you 
can see, using the Login Screenlet provides a great deal of flexibility, even 
when you don't need to use any other Screenlets. 

Now you can call your new `getGuestbooks()` method. Place the `getGuestbooks()` 
call in the `onCreate` method of `GuestbooksActivity`, following the call to 
`setContentView(R.layout.activity_guestbooks)`. The first few lines of the 
`onCreate` method should now look like this: 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbooks);
        
        getGuestbooks();
        
        ...
    }

Android calls the activity's `onCreate` method to start the activity. This makes 
`onCreate` an ideal place to make the call to retrieve guestbooks. Android's 
[activity lifecycle documentation](http://developer.android.com/training/basics/activity-lifecycle/starting.html) 
describes the `onCreate` method in more detail. 

Awesome! Your app can now retrieve the Guestbook portlet's guestbooks. Next, 
you'll display them in the navigation drawer. 
