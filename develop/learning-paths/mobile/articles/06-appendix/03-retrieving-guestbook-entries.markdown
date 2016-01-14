# Retrieving Guestbook Entries [](id=retrieving-guestbook-entries)

So far, you have an Android app that retrieves and displays guestbooks from
the [MVC Learning Path's](/develop/learning-paths/mvc/-/knowledge_base/6-2/beginning-liferay-development) 
Guestbook portlet. But that's all your app does. Tapping a guestbook in the 
navigation drawer doesn't do anything besides close the drawer to show the 
Action Bar and an empty screen. 

![Figure 1: Guestbook entries aren't showing yet in the app.](../../images/android-first-guestbook.png)

Next, you'll learn the steps required to retrieve guestbook entries from the
Guestbook portlet. These steps are almost the same as those you used to retrieve
guestbooks. The only real difference, besides getting entries instead of
guestbooks, is that you'll use a fragment instead of an activity. 

The steps you'll follow to retrieve entries are listed here: 

1. Create a model class for the entries. As with guestbooks, you need a model 
   class to transform the JSON received from the portlet into entry model 
   objects that you can work with in your app. 

2. Create a fragment for retrieving the entries. In the next article, you'll 
   also use this fragment to display the entries. 

3. Create a callback class. You'll use this class to make the service call 
   asynchronously from a background thread, since Android doesn't allow network 
   requests from its main UI thread. 

4. Make the service call from the fragment, using the callback class. 

As you did when you retrieved guestbooks from the portlet, you'll begin here by 
creating a model class for the JSON returned from the portlet. 

## Creating the Model Class for Entries [](id=creating-the-model-class-for-entries)

As with guestbooks, the Guestbook Mobile SDK returns entries from the portlet in 
a `JSONArray` that contains each entry in a `JSONObject`. You need to convert 
these entries into proper entry model objects so you can work with them 
efficiently in your app. Create the `EntryModel` class in your app's `model` 
package. Replace its contents with the following code: 

    package com.liferay.docs.liferayguestbook.model;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.Serializable;
    import java.util.Date;


    public class EntryModel implements Serializable {

        private long _entryId;
        private long _groupId;
        private long _companyId;
        private long _userId;
        private String _userName;
        private long _createDate;
        private long _modifiedDate;
        private String _name;
        private String _email;
        private String _message;
        private long _guestbookId;

        public EntryModel(JSONObject json) throws JSONException {

            _entryId = json.getLong("entryId");
            _groupId = json.getLong("groupId");
            _companyId = json.getLong("companyId");
            _userId = json.getLong("userId");
            _userName = json.getString("userName");
            _createDate = json.getLong("createDate");
            _modifiedDate = json.getLong("modifiedDate");
            _name = json.getString("name");
            _email = json.getString("email");
            _message = json.getString("message");
            _guestbookId = json.getLong("guestbookId");
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof EntryModel)) {
                return false;
            }

            EntryModel entry = (EntryModel) obj;

            return (_entryId == entry.getEntryId());
        }

        @Override
        public String toString() {
            return _message;
        }

        public long getEntryId() {
            return _entryId;
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

        public String getEmail() {
            return _email;
        }

        public String getMessage() {
            return _message;
        }

        public long getGuestbookId() {
            return _guestbookId;
        }
    }

Besides working with entries instead of guestbooks, this class works the same as 
`GuestbookModel`. Now that your `EntryModel` class exists, you can create the 
fragment you'll use to retrieve and display the entries. 

## Creating a Fragment for the Entries [](id=creating-a-fragment-for-the-entries)

Using a fragment for the entries lets you swap out part of `GuestbookActivity`'s 
contents without recreating the entire activity from scratch each time a 
guestbook is selected. Now you'll create this fragment. Right click the 
`com.liferay.docs.liferayguestbook` package and select 
*New* &rarr; *Fragment* &rarr; *Fragment (Blank)*. In the wizard, uncheck all 
the checkboxes, name the fragment `EntriesFragment`, and then click *Finish*. 
The following screenshot illustrates this: 

![Figure 2: Create a new blank fragment for the entries.](../../images/android-create-fragment.png)

Replace the fragment's contents with the following code: 

    package com.liferay.docs.liferayguestbook;

    import android.os.Bundle;
    import android.support.v4.app.ListFragment;

    import com.liferay.docs.liferayguestbook.model.EntryModel;

    import java.util.ArrayList;
    import java.util.List;

    public class EntriesFragment extends ListFragment {

        private long _guestbookId;
        private List<EntryModel> _entries = new ArrayList<EntryModel>();


        public EntriesFragment() {
            // Required empty public constructor
        }

        public static EntriesFragment newInstance(long guestbookId) {
            EntriesFragment entriesFragment = new EntriesFragment();
            Bundle args = new Bundle();
            args.putLong("guestbookId", guestbookId);
            entriesFragment.setArguments(args);

            return entriesFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            _guestbookId = getArguments().getLong("guestbookId");
        }

        public void reloadEntries(List<EntryModel> entries) {
            _entries.clear();
            _entries.addAll(entries);
        }
    }

By extending `ListFragment`, the `EntriesFragment` class contains an implicit 
`ListView` that you'll use to display the entries. This means that you don't 
have to define a `ListView` in a layout file, as you did when displaying 
guestbooks in the drawer. For its variables, the `EntriesFragment` class 
requires the `_guestbookId` variable so it can retrieve the corresponding 
guestbook's entries. The class also requires the `entries` variable so it can 
hold those entries. 

If you have experience with Android fragments, then you're probably familiar 
with the empty constructor and `newInstance` method. When the screen orientation 
changes or the user switches apps, Android needs a way to restore the fragment. 
Instead of recreating the fragment from scratch, the `newInstance` method lets 
Android restore it with the proper `guestbookId`. The `onCreate` method sets the 
fragment's `_guestbookId` by using the bundle arguments set in `newInstance`. 
See 
[this blog post](http://www.androiddesignpatterns.com/2012/05/using-newinstance-to-instantiate.html) 
for more information on using a `newInstance` method to manage fragments. 

The final method in `EntriesFragment` is `reloadEntries`. Recall that when 
getting guestbooks, `GetGuestbooksCallback` calls the `reloadGuestbooks` method 
of `GuestbooksActivity`. This is how the callback sends the guestbooks to the 
activity. The `reloadEntries` method in `EntriesFragment` serves the analogous 
purpose for entries: the callback needs it to send the entries to the fragment. 
Next, you'll create this callback. 

## Creating a Callback Class [](id=creating-a-callback-class)

Recall that when you retrieved guestbooks, you needed a callback class to route 
the call asynchronously through a background thread. This callback class also 
received the guestbooks as JSON, transformed them into `GuestbookModel` objects, 
and then passed them to `GuestbooksActivity`. You need an analogous callback 
class for retrieving entries. In the `callback` package, create the 
`GetEntriesCallback` class. Replace its contents with the following code: 

    package com.liferay.docs.liferayguestbook.callback;

    import android.widget.Toast;

    import com.liferay.docs.liferayguestbook.EntriesFragment;
    import com.liferay.docs.liferayguestbook.model.EntryModel;
    import com.liferay.mobile.android.callback.typed.GenericCallback;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.List;

    public class GetEntriesCallback extends GenericCallback<List<EntryModel>> {

        private EntriesFragment _fragment;

        public GetEntriesCallback(EntriesFragment fragment) {
            _fragment = fragment;
        }

        @Override
        public void onFailure(Exception e) {
            String message = "Couldn't get entries " + e.getMessage();

            Toast.makeText(_fragment.getActivity(), message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(List<EntryModel> entries) {
            _fragment.reloadEntries(entries);
        }

        @Override
        public List<EntryModel> transform(Object obj) throws Exception {
            List<EntryModel> entries = new ArrayList<EntryModel>();

            JSONArray jsonArray = (JSONArray)obj;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                entries.add(new EntryModel(json));
            }

            return entries;
        }
    }

Besides using entries instead of guestbooks and `EntriesFragment` instead of 
`GuestbooksActivity`, this class works the same as `GetGuestbooksCallback`. See 
[the article on retrieving guestbooks](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieving-guestbooks#creating-a-callback-class) 
for details on how this class works. 

Great! Your app now contains the basic infrastructure it needs to retrieve 
guestbook entries. You're ready to make the server call. You'll do this in 
`EntriesFragment`.

## Making the Server Call [](id=making-the-server-call)

In your `EntriesFragment` class, you need a method for making the call to 
retrieve the portlet's entries. Recall that you retrieved guestbooks with the 
`getGuestbooks` method in `GuestbooksActivity`. You'll retrieve entries 
similarly by creating a `getEntries` method in `EntriesFragment`. Add this 
method to the `EntriesFragment` class: 

    protected void getEntries(long guestbookId) {
        Session session = SessionContext.createSessionFromCurrentSession();
        GetEntriesCallback callback = new GetEntriesCallback(this);
        session.setCallback(callback);

        EntryService service = new EntryService(session);
        try {
            service.getEntries(LiferayServerContext.getGroupId(), guestbookId);
        }
        catch (Exception e) {
            String message = "Couldn't get entries " + e.getMessage();
            Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

Like the `getGuestbooks` method, `getEntries` gets the current session, sets the 
session's callback, creates a service object, and then makes the server call. 
The only significant difference is that `getEntries` takes a guestbook's ID as a 
parameter. It needs this to request the corresponding guestbook's entries. 

Now you're ready to put `getEntries` to work! Do so now by adding the following 
`onActivityCreated` method to `EntriesFragment`: 

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getEntries(_guestbookId);
    }

By calling `getEntries` in `onActivityCreated`, you ensure that the fragment's 
view hierarchy and parent activity exist prior to making the server call. See 
Android's 
[fragment lifecycle documentation](http://developer.android.com/guide/components/fragments.html#Lifecycle) 
for more information. 

Awesome! Now that your app retrieves guestbook entries from the portlet, you can 
display them in your app's UI. 
