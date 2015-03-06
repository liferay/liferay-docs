# Retrieve and Display Guestbooks 

The first order of business is to retrieve the guestbooks from the portlet and 
display them in the app's navigation drawer. Recall that the app you created in 
the first segment of this learning path contains a navigation drawer that slides 
out from the left side of the screen. Currently, it contains simple 
placeholders that are in dire need of replacing.

![Figure 1: The placeholders currently in the navigation drawer.](../../images/android-guestbook-first-run.png)

In this article you'll use the Mobile SDK to call the Guestbook portlet's remote 
services and replace the placeholders with guestbook names. This is conceptually 
simple, but a bit more complex in practice. Not to worry! This article guides 
you through each step in the process. First, you'll do some simple refactoring 
in your project.

## Refactoring

Before you get started calling the Guestbook portlet, you should refactor your 
project's activity and fragment classes by moving them into a new `activity` 
package. While this isn't required for the app to function, it makes it simpler 
to find and work with these classes as you add new classes. In Android Studio's 
project view:

1. Right click the package `com.liferay.docs.liferayguestbook` and select 
   *New* &rarr; *Package*. In the dialog that appears, type *activity* and click 
   *OK*.
   
2. Right click `MainActivity` and select *Refactor* &rarr; *Move*. In the *Move* 
   dialog that appears, make sure the radio button for *To package* is selected, 
   and click the button with the ellipsis to the right. Another dialog appears 
   that lets you select the new packge. In this dialog, expand 
   *liferayguestbook*, click *activity*, and then click *OK*. Back in the Move 
   dialog, click *Refactor*.
   
    ![Figure 1: The *Refactor* &rarr; *Move* dialog in Android Studio. Clicking the button with the red box lets you choose a new package for the file.](../../images/android-studio-refactor.png)
   
3. Repeat step 2 for the `NavigationDrawerFragment` class. The `MainActivity` 
   and `NavigationDrawerFragment` classes should now be in the `activity` 
   package you created in step 1.
   
    ![Figure 2: The classes are now in the `activity` package.](../../images/android-studio-after-refactor.png)

Great! Now you'll be able to quickly find these classes as you add new files and 
functionality to your app. Now it's time to get some guestbooks!

## Encapsulating Guestbooks

To work with guestbooks efficiently in your app, you need a way of encapsulating 
them. This lets you refer to common guestbook objects throughout your code. 
First, create a new package called *model* inside of the 
`com.liferay.docs.liferayguestbook` package. Right click the new `model` package 
and select *New* &rarr; *Java Class*. In the dialog that appears, name the class 
*GuestbookModel* and click *OK*. The new class then opens in Android Studio. 
Replace its contents with the following code:

    package com.liferay.docs.liferayguestbook.model;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.Serializable;


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

        GuestbookModel guestbook = (GuestbookModel)obj;

        return (_guestbookId == guestbook.getGuestbookId());
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

      public String getGuestbookName() {
        return _name;
      }
    }

This class creates `GuestbookModel` objects that effectively mirror `Guestbook` 
objects from the portlet. It does so by retrieving `Guestbook` parameters from 
the `JSONObject` returned by the Mobile SDK's remote service calls. This is done 
in the constructor by the `getLong` and `getString` methods. To see how the 
`Guestbook` fields are defined in the portlet, see the 
[Liferay MVC learning path article on Service Builder](/learning-paths/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only fields you really need in this class are `guestbookId` and 
`name`. Later in this learning path, however, you'll need the rest when you 
implement functionality in the app to add, update, and delete guestbooks in the 
portlet. It's simpler to add support for all of them now. Next, you'll add the 
basic infrastructure for requesting guestbooks from the portal. 

## Creating a Callback Class

Since Android doesn't allow network requests from its main UI thread, you have 
to make them from another thread by creating a callback class that extends the 
Mobile SDK's `GenericAsyncTaskCallback` class. At this point you might be 
saying, "Oh no, threading in mobile apps? That sounds complicated!" Fear not! 
The Mobile SDK's `GenericAsyncTaskCallback` class obscures much of the added 
complexity.

To create the callback class for retrieving guestbooks, first create a new 
pacakge called *callback* in `com.liferay.docs.liferayguestbook`. Then create a 
new class in the `callback` package called `GetGuestbooksCallback`. Replace the 
code in the class with the following code:

    package com.liferay.docs.liferayguestbook.callback;

    import android.widget.Toast;

    import com.liferay.docs.liferayguestbook.activity.MainActivity;
    import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
    import com.liferay.docs.liferayguestbook.model.GuestbookModel;

    import java.util.ArrayList;
    import java.util.List;

    import org.json.JSONArray;
    import org.json.JSONObject;

    public class GetGuestbooksCallback extends GenericAsyncTaskCallback<List<GuestbookModel>> {

      private MainActivity _activity;

      public GetGuestbooksCallback(MainActivity activity) {
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
    
First, you should note that this class has an instance of `MainActivity` as its 
only variable. This is so it can refer it's results back to the main activity, 
which runs in Android's main UI thread. The constructor of 
`GetGuestbooksCallback` thus does only one thing: it sets this variable. Next, 
the `onFailure` and `onSuccess` methods are overridden. As you probably guessed, 
`onFailure` is called when the request fails, and `onSuccess` is called when the 
request succeeds. In this case, `onFailure` only does one thing: it displays a 
toast message with the error. The `onSuccess` method also only does one thing, 
but it's a very important thing: it calls the main activity's `reloadGuestbooks` 
method. Don't worry about the `reloadGuestbooks` method being marked as an error 
by Android Studio. This method doesn't yet exist but you'll create it in a 
moment. Last but certainly not least is the overridden `transform` method. This 
method puts all the guestbooks it receives from the Mobile SDK call into a 
`List` of `GuestbookModel` objects. It's this `List` that's fed to the 
`onSuccess` method. You're probably starting to see why `reloadGuestbooks` is 
such and important method; it's receiving the guestbooks for processing in the 
app's main UI thread. Now it's time to write that processing code!

## Displaying Guestbooks in the Drawer

In the `MainActivity` class, create the variable for holding the `List` of 
`GuestbookModel` objects returned by the callback. This variable needs to be 
`public` and `static` so it can be referred to throughout the UI, independent of 
any single instance of `MainActivity`.

    public static List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();

Now open the `NavigationDrawerFragment` class. In it, declare the following 
variable.

    public ArrayAdapter _adapter;
    
Pull the `new ArrayAdapter` code out of the `setAdapter` method in 
`onCreateView`, and set it to `_adapter`. Then put `_adapter` as the argument to 
`setAdapter` (all within `onCreateView`).

Add `reloadGuestbooks`

Add `getGuestbooks`
