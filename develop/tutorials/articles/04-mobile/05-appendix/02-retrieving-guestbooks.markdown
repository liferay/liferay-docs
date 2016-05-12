# Retrieving Guestbooks [](id=retrieving-guestbooks)

Now you're ready to use the Guestbooks Mobile SDK to retrieve guestbooks from 
the server. To retrieve guestbooks in the app, you'll follow these steps:

1. Create the model class for the guestbooks. You need this class so you can 
   transform the JSON received from the portlet into guestbook model objects 
   that you can work with in the app. 

2. Prepare the `GuestbooksActivity` class to receive guestbook model objects.

3. Create a callback class that makes the service call asynchronously in a 
   background thread, transforms the JSON results into guestbook model objects, 
   and then sends those objects to `GuestbooksActivity`.

4. Use the callback class to make the service call from `GuestbooksActivity`. 

You'll get started with the first step, creating the model class for guestbooks.

## Creating the Model Class for Guestbooks [](id=creating-the-model-class-for-guestbooks)

Recall that the Guestbook Mobile SDK returns guestbooks from the portlet in a 
`JSONArray` that contains each guestbook in a `JSONObject`. Just as you did when 
creating Get Guestbooks Screenlet, you must create a model class that turns each 
`JSONObject` into a proper guestbook object. You'll do this the exact same way 
you did in Get Guestbooks Screenlet. To create this model class, follow 
[these instructions](/develop/learning-paths/mobile/-/knowledge_base/6-2/getting-started-with-get-guestbooks-screenlet#creating-the-model-class-for-guestbooks) 
from the article on getting started with Get Guestbooks Screenlet. When you 
finish, you'll have an identical `GuestbookModel` class inside the new package 
`com.liferay.docs.model`. 

Next, you'll prepare `GuestbooksActivity` to receive `GuestbookModel` objects. 

## Preparing GuestbooksActivity for Guestbooks [](id=preparing-guestbooksactivity-for-guestbooks)

Before making the server call, you must prepare `GuestbooksActivity` to handle 
`GuestbookModel` objects. Specifically, you need to give the callback class 
you'll create in a moment a way to pass `GuestbookModel` objects to 
`GuestbooksActivity`. 

First, add a `_guestbooks` variable to `GuestbooksActivity`: 

    private List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();

After adding this variable, add the following imports:

    import com.liferay.docs.model.GuestbookModel;
    import java.util.ArrayList;
    import java.util.List;

Next, add the following `reloadGuestbooks` method to `GuestbooksActivity`: 

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
make them from another thread. You did this in your Screenlets by creating a 
callback class. You'll do the same thing here by creating a callback class that 
extends the Mobile SDK's 
[`GenericCallback`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/main/java/com/liferay/mobile/android/callback/typed/GenericCallback.java) 
class. The `GenericCallback` class abstracts away much of the complexity 
involved in threading. See 
[Android's documentation](http://developer.android.com/guide/components/processes-and-threads.html#Threads) 
for more information on threading. 

To create the callback class for retrieving guestbooks, first create a new 
package called `callback` in `com.liferay.docs`. Then create a new class in the 
`callback` package called `GetGuestbooksCallback`. Replace this class's code 
with the following: 

    package com.liferay.docs.callback;

    import android.widget.Toast;

    import com.liferay.docs.liferayguestbook.GuestbooksActivity;
    import com.liferay.docs.model.GuestbookModel;
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
[the callback class you created for Get Guestbooks Screenlet](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-get-guestbook-screenlets-server-call#creating-the-callback-class).
The `transform` methods in both classes are identical. Recall that this method 
transforms each `JSONObject` into a `GuestbookModel` object. The callback class 
here also contains the code required to pass these results to the class that 
issued the call. First, note that this class has a `GuestbooksActivity` instance 
as its only variable. This is so it can send results back to 
`GuestbooksActivity`, which runs in Android's main UI thread. The callback 
class's constructor thus does only one thing: it sets this variable. Next, the 
`onFailure` and `onSuccess` methods are overridden. As you probably guessed, 
`onFailure` is called when the request fails, and `onSuccess` is called when it 
succeeds. The `onFailure` method displays a toast message with the error. The 
`onSuccess` method sends the guestbooks to `GuestbooksActivity` by calling the 
activity's `reloadGuestbooks` method. 

Now that you have everything you need to retrieve guestbooks from the Guestbook 
portlet, you're ready to make the server call. 

## Making the Server Call [](id=making-the-server-call)

The Guestbook Mobile SDK call to retrieve guestbooks is almost identical to 
[the one in Get Guestbooks Screenlet's `GetGuestbooksInteractorImpl` class](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-get-guestbook-screenlets-server-call#creating-and-implementing-the-interactor-interface).
The only difference is the `getGuestbooks` method you'll create here doesn't 
take a group ID as a parameter. The Screenlet's `getGuestbooks` method requires 
this parameter to account for group ID changes made by the app developer. You'll 
instead use `LiferayServerContext`, a Screens helper class, to get the group ID 
set in `server_context.xml`. 

Besides this, the server call itself is exactly the same. You make it by 
creating an authenticated session, setting a callback to the session, creating 
a service instance, and then calling the service method. Add the following 
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

This requires you to add the following imports:

    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v62.guestbook.GuestbookService;
    import com.liferay.mobile.screens.context.LiferayServerContext;
    import com.liferay.mobile.screens.context.SessionContext;
    import com.liferay.docs.callback.GetGuestbooksCallback;
    import android.widget.Toast;

Now you can call this method. Place the `getGuestbooks()` call in the `onCreate` 
method of `GuestbooksActivity`, following the call to 
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
