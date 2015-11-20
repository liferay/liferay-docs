# Retrieve and Display Entries [](id=retrieve-and-display-entries)

If you completed the previous articles in this Learning Path, then you have an
Android app that retrieves and displays guestbooks from the 
[MVC Learning Path's](/develop/learning-paths/mvc/-/knowledge_base/6-2/beginning-liferay-development) 
Guestbook portlet. But that's all your app does. Tapping a guestbook in the 
navigation drawer doesn't do anything besides close the drawer to show the 
Action Bar and an empty screen. 

![Figure 1: Guestbook entries aren't showing yet in the app.](../../images/android-first-guestbook.png)

This article walks you through the steps required to retrieve and display 
guestbook entries from the Guestbook portlet. These steps are almost the same as 
those in the previous article for retrieving and displaying guestbooks. The only 
real difference, besides getting entries instead of guestbooks, is that you'll 
display the results in a fragment instead of the navigation drawer. When a 
guestbook is selected in the drawer, you'll perform a fragment transaction to 
replace any entries currently displayed in the fragment with the selected 
guestbook's entries. 

The steps you'll follow for retrieving and displaying entries are listed here:

1. Create a model class for the entries. As with guestbooks, you need a model 
   class to transform the JSON received from the portlet into entry model 
   objects that you can work with in your app.

2. Create a fragment for displaying the entries. 

3. Create a callback class. You'll use this class to make the service call 
   asynchronously from a background thread, since Android doesn't allow network 
   requests from its main UI thread.

4. Make the service call from the fragment, using the callback class.

5. Display the entries in the fragment.

As you did when you retrieved guestbooks from the portlet, you'll begin here by 
creating a model class for the JSON returned from the portlet. 

## Creating the Model Class for Entries

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
fragment you'll use to display the entries.

## Creating a Fragment for the Entries

Using a fragment to display entries lets you swap out `GuestbookActivity`'s 
contents without recreating the entire activity from scratch each time the user 
selects a guestbook. Now you'll create this fragment. Right click the 
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
activity. The `reloadEntries` method in `EntriesFragment` serves the analagous 
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

Besides using entries instead of guestbooks, and `EntriesFragment` instead of 
`GuestbooksActivity`, this class works the same as `GetGuestbooksCallback`. See 
the previous article for details on how this class works. 

Great! Your app now contains the basic infrastructure it needs to retrieve 
guestbook entries. You're ready to make the server call. 

## Making the Server Call [](id=making-the-server-call)

In your `EntriesFragment` class, you need a method for making the call that 
retrieves the portlet's entries. Recall that you retrieved guestbooks with the 
`getGuestbooks` method in `GuestbooksActivity`. You'll retrieve entries 
similarly by creating a `getEntries` method in `EntriesFragment`. Add this 
method to the `EntriesFragment` class as follows: 

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

## Displaying Entries

To display entries, you need to add them to `EntriesFragment` and then add the 
fragment to `GuestbooksActivity`. Recall that because `EntriesFragment` extends 
`ListFragment`, it contains an implicit `ListView` object. You'll use this 
`ListView` to display the entries. You must, however, create an adapter to use 
with the `ListView`. After creating this adapter and setting it to the 
`ListView`, you'll add the fragment to `GuestbooksActivity` via a fragment 
container and a fragment transaction. 

First, you must create a variable for the adapter. Add it to `EntriesFragment` 
as follows: 

    private ArrayAdapter _adapter;

Now you need to create the adapter. Do so now by placing the following code in 
the `onActivityCreated` method, above the `getEntries` call:

    _adapter = new ArrayAdapter<EntryModel>(
        getActivity(), 
        android.R.layout.simple_list_item_2,
        android.R.id.text1, 
        _entries) {
        
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(_entries.get(position).getMessage());
                text2.setText(_entries.get(position).getName());

                text1.setTypeface(Typeface.DEFAULT_BOLD);
                text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                return view;
            }
        };
        
    setListAdapter(_adapter);

This adapter has significantly more code than the guestbooks adapter. This is 
because the entries adapter uses the default layout `simple_list_item_2`. Recall 
that the guestbooks adapter uses the default layout `simple_list_item_1`. This 
layout is sufficient for showing guestbook names because it displays only a 
single string for each list item. Each entry, however, should display two 
strings: the entry's message, and the name of the person who left it. The 
`simple_list_item_2` layout lets you display these strings, because it displays 
two strings for each list item. You need to tell it what two strings to display, 
though. This is why you override the `getView` method. After using 
`findViewById` to retrieve the `simple_list_item_2` layout's two strings, you 
use `setText` to set the strings' content to an entry's message and name. You 
then use the `setTypeface` and `setTextSize` methods, respectively, to format 
the entry's message in bold and shrink the name. You finish by using 
`setListAdapter` to set the adapter to `EntriesFragment`'s implicit `ListView`. 

Like the guestbooks adapter, you also need to refresh the entries adapter's 
contents following a successful server call. To do so, add the following code at 
the end of the `reloadEntries` method: 

    _adapter.notifyDataSetChanged();

Now that `EntriesFragment` can put entries in its `ListView`, you're ready to 
add the fragment to `GuestbooksActivity`. First, you need to put a fragment 
container in the layout you want the fragment to appear in. In short, a fragment 
container is a layout used to hold a fragment. For more information, see 
[Android's documentation on adding fragments at runtime](http://developer.android.com/training/basics/fragments/fragment-ui.html#AddAtRuntime). 
Since you want the entries to appear in `GuestbooksActivity`, your first thought 
might be to put the fragment container directly in `activity_guestbooks.xml`. 
Don't do this. Recall that the Navigation Drawer Activity template in Android 
Studio created the layout `content_guestbooks.xml` to hold the activity's main 
body content. Open `content_guestbooks.xml` and place the following fragment 
container inside the `RelativeLayout`: 

    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

Next, you need to write the code that handles `EntriesFragment` in 
`GuestbooksActivity`. Specifically, you need to display the fragment when a 
guestbook is selected. Recall that the `onItemClick` method in 
`GuestbooksActivity` selects a guestbook. Add the following code to this method, 
immediately above `drawer.closeDrawers()`:

    EntriesFragment entriesFragment = EntriesFragment.newInstance(selectedGuestbook.getGuestbookId());
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container, entriesFragment);
    transaction.commit();

You use `newInstance` to create a new `EntriesFragment` instance that contains 
the selected guestbook's ID. A 
[fragment transaction](http://developer.android.com/guide/components/fragments.html#Transactions) 
then adds this fragment to the fragment container.

That's it! Make sure your portal is running and then run the app. The app now 
displays the selected guestbook's entries. 

![Figure 3: The entries for the selected guestbook now appear in your app.](../../images/android-guestbook-entries.png)

Congratulations! You've successfully built the Guestbook Mobile SDK and used it 
in conjunction with Liferay Screens to develop an Android app that displays the 
Guestbook portlet's entities. But there's another way, a better way, to develop 
the same functionality: you can use Liferay Screens to create your own 
Screenlets. The following sections in this Learning Path show you how to do 
this. 
