# Retrieve and Display Entries [](id=retrieve-and-display-entries)

If you completed the previous articles in this Learning Path, then you have an
Android app that retrieves and displays guestbooks from the 
[MVC Learning Path's](/develop/learning-paths/mvc/-/knowledge_base/6-2/beginning-liferay-development) 
Guestbook portlet. But that's all your app does. Tapping a guestbook in the 
navigation drawer doesn't do anything besides close the drawer to show the 
action bar and an empty screen. 

![Figure 1: Guestbook entries aren't showing yet in the app.](../../images/android-first-guestbook.png)

This article walks you through the steps required to retrieve and display 
guestbook entries from the portal. As you did to display guestbooks, you'll use 
`ListView` to display entries in a list. You'll also retrieve entries using the 
same basic steps you used to retrieve guestbooks. Therefore, this article 
doesn't explain the concepts behind these steps in detail. Refer to the previous 
article if you need this detail. As you did when you wrote the code to retrieve 
guestbooks from the portal, you'll begin here by encapsulating the data returned 
from the portal. 

## Encapsulating Entries [](id=encapsulating-entries)

As with guestbooks, entries retrieved from the portal should be encapsulated in 
your app. Create the `EntryModel` class in your app's `model` package. Replace 
its contents with the following code: 

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

Now that your `EntryModel` class exists, you can write the code that retrieves 
guestbook entries from the portlet.

## Getting Entries [](id=getting-entries)

Before making the server call, you need to create the 
[fragment](http://developer.android.com/guide/components/fragments.html) 
you'll use to display the entries in `GuestbooksActivity`. Using a fragment lets 
you swap out the activity's contents without recreating the activity from 
scratch each time the user selects a guestbook. Right click the 
`com.liferay.docs.liferayguestbook` package and select 
*New* &rarr; *Fragment* &rarr; *Fragment (Blank)*. In the wizard, uncheck all 
the checkboxes, name the fragment *EntriesFragment*, and then click *Finish*. 
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
have to define a `ListView` in a layout file. For its variables, the 
`EntriesFragment` class requires the `_guestbookId` variable so it can retrieve 
the corresponding guestbook's entries. The class also requires the `entries` 
variable so it can hold those entries. If you've dealt with Android fragments 
before, then you're probably familiar with the empty constructor and 
`newInstance` method. When the screen orientation changes or the user switches 
apps, Android needs a way to restore the fragment. Instead of recreating the 
fragment from scratch, the `newInstance` method lets Android restore it with the 
proper `guestbookId`. The `onCreate` method sets the fragment's `_guestbookId` 
value by using the bundle arguments set in `newInstance`. See 
[this blog post](http://www.androiddesignpatterns.com/2012/05/using-newinstance-to-instantiate.html) 
for more information on using a `newInstance` method to manage fragments. 

The final method in `EntriesFragment` is `reloadEntries`. Recall that the 
`reloadGuestbooks` method in `GuestbooksActivity` refreshes the guestbook list 
with the latest guestbooks returned from the server. The `reloadEntries` method 
does the same for entries. The callback class you'll write next calls 
`reloadEntries` when the server call succeeds.

### Creating a Callback Class

Recall that you needed a callback class to retrieve guestbooks asynchronously 
from the Guestbook portlet. You need an analogous callback class for retrieving 
entries. In the `callback` package, create the `GetEntriesCallback` class. 
Replace its contents with the following code:

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

Besides using entries instead of guestbooks, this class works the same as 
`GetGuestbooksCallback`. See the previous article for details on how this class 
works. 

Great! Your app now contains the basic infrastructure it needs to retrieve 
guestbook entries. You're ready to make the server call. 

### Making the Server Call [](id=making-the-server-call)

Next, you need a method for making the call that retrieves the portlet's 
entries. Recall that you retrieved guestbooks with the `getGuestbooks` method. 
You'll retrieve entries similarly with the `getEntries` method. Add it to the 
`EntriesFragment` class as follows: 

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
The only difference is that `getEntries` takes a guestbook's ID as a parameter, 
which it then uses to request the corresponding guestbook's entries. 

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
display them in the UI. 

## Displaying Entries [](id=displaying-entries)

Since you're using `ListView` to display the entries in the UI, you need to 
create a variable for holding the adapter. Add it to `EntriesFragment` as shown 
here: 

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

This adapter uses the default layout `simple_list_item_2`. Recall that you used 
the default layout `simple_list_item_1` for each guestbook in the drawer. That 
layout is sufficient for showing guestbook names because it displays a single 
string for each list item. However, each entry needs to display two strings: the 
message, and the name of the person who left it. This is where 
`simple_list_item_2` comes in; it lets you display two strings for each list 
item. 

The `getView` method is overridden so that you can tell `simple_list_item_2` 
what two strings to display. After using `findViewById` to retrieve the layout's 
two strings, `setText` is used to set their content to an entry's message and 
name. The `setTypeface` and `setTextSize` methods are then used to format the 
message in bold and shrink the name, respectively. You then use the 
`setListAdapter` method to set the adapter to the default `ListView` of 
`EntriesFragment`.

Like the guestbooks adapter, the entries adapter's contents also need to refresh 
following a successful server call. To do so, add the following code at the end 
of the `reloadEntries` method: 

    _adapter.notifyDataSetChanged();

Now that your `EntriesFragment` class can properly place entries in a 
`ListView`, you're ready to integrate it with `GuestbooksActivity`. First, you 
need to put a fragment container in the layout you want the fragment to appear 
in. In short, a fragment container is a layout used to hold a fragment. For more 
information, see 
[Android's documentation on adding fragments at runtime](http://developer.android.com/training/basics/fragments/fragment-ui.html#AddAtRuntime). 
Since you want the entries to appear in `GuestbooksActivity`, your first thought 
might be to put the fragment container directly in `activity_guestbooks.xml`. 
Don't do this. The Navigation Drawer template in Android Studio created the 
layout `content_guestbooks.xml` to hold the activity's content. Open this file 
and place the following fragment container inside the `RelativeLayout`: 

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

This code uses `newInstance` to create a new `EntriesFragment` instance that 
contains the selected guestbook's ID. A 
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