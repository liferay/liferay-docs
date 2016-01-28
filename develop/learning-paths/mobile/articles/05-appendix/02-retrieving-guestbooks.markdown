# Retrieving Guestbooks [](id=retrieving-guestbooks)

Now you're ready to use the Guestbooks Mobile SDK to retrieve guestbooks from 
the server. To retrieve guestbooks in the app, you'll follow these steps:

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

Recall that the Guestbook Mobile SDK returns guestbooks from the portlet in a 
`JSONArray` that contains each guestbook in a `JSONObject`. Just as you did when 
creating Get Guestbooks Screenlet, you must create a model class that turns each 
`JSONObject` into a proper guestbook object. You'll do this the exact same way 
you did in Get Guestbooks Screenlet. First, create a new package called `model`
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

This is the exact same 
[`GuestbookModel` class you created for Get Guestbooks Screenlet](/develop/learning-paths/mobile/-/knowledge_base/6-2/getting-started-with-the-get-guestbooks-screenlet#creating-the-model-class-for-guestbooks). 
Next, you'll prepare `GuestbooksActivity` to recieve `GuestbookModel` objects. 

## Preparing GuestbooksActivity for Guestbooks [](id=preparing-guestbooksactivity-for-guestbooks)

Before making the server call, you must prepare the `GuestbooksActivity` class 
to handle `GuestbookModel` objects. Specifically, you need to give the callback 
class you'll create in a moment a way to pass `GuestbookModel` objects to 
`GuestbooksActivity`. 

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
class. The `GenericCallback` class abstracts away much of the complexity 
involved with making calls from a background thread. See 
[Android's documentation](http://developer.android.com/guide/components/processes-and-threads.html#Threads) 
for more information on threading. 

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

This class is similar to 
[the callback class you created for Get Guestbooks Screenlet](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-the-get-guestbook-screenlets-server-call#creating-the-callback-class).
The `transform` methods in both classes are identical. Recall that this method 
transforms each `JSONObject` into `GuestbookModel` a object. The callback class 
here also contains the code required to pass its results to the class that 
issued the call. First, you should note that this class has a 
`GuestbooksActivity` instance as its only variable. This is so it can refer 
results back to `GuestbooksActivity`, which runs in Android's main UI thread. 
The `GetGuestbooksCallback`'s constructor thus does only one thing: it sets this 
variable. Next, the `onFailure` and `onSuccess` methods are overridden. As you 
probably guessed, `onFailure` is called when the request fails, while 
`onSuccess` is called when it succeeds. In this example, `onFailure` displays a 
toast message with the error. The `onSuccess` method sends the guestbooks to 
`GuestbooksActivity` by calling the activity's `reloadGuestbooks` method. 

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
