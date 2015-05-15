# Retrieve and Display Entries

If you completed the previous articles in this learning path, then you have an 
Android app that retrieves and displays guestbooks from the
[MVC learning path's](/learning-paths/-/knowledge_base/6-2/beginning-liferay-development) 
Guestbook portlet. However, that's all your app does. Tapping a guestbook in its 
navigation drawer doesn't show the guestbook's entries. In fact, tapping a 
guestbook doesn't do anything besides close the drawer to show the action bar 
with an empty screen. Also, the action bar's title is still hardcoded with the 
`"Section *"` strings Android Studio created as placeholders. 

![Figure 1: Guestbook entries aren't showing yet in the app.](../../images/android-entries-empty.png)

This article walks you through the steps required to retrieve and display 
guestbook entries from the portal. As you did to display guestbooks, you'll use 
`ListView` to display entries in a list. Also, the first two steps in this 
article follow the same basic pattern you used to retrieve guestbooks. For this 
reason, the concepts behind these steps aren't explained in detail here. Refer 
to this learning path's previous article for such an explanation. As you did 
when you wrote the code to retrieve guestbooks from the portal, you'll begin 
here by encapsulating the objects being retrieved. 

## Encapsulating the Entries

As with guestbooks, entries retrieved from the portal should be encapsulated in 
your app. Create a new class in your app's `model` package by right clicking it 
and selecting *New* &rarr; *Java Class*. Name the new class *EntryModel*. 
Replace its contents with the following code: 

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
            if (!(obj instanceof GuestbookModel)) {
                return false;
            }

            EntryModel entry = (EntryModel)obj;

            return (_entryId == entry.getEntryId());
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

Now that your `EntryModel` class exists, you can create the callback class used 
to retrieve a guestbook's entries.

## Creating a Callback Class

Recall from the article on retrieving and displaying guestbooks that Android 
doesn't allow network requests from its main UI thread. To retrieve a 
guestbook's entries, you need to make the request from another thread. This is 
handled by a callback class that extends the Mobile SDK's 
`GenericAsyncTaskCallback` class. Create a new class called `GetEntriesCallback`
in your app's `callback` package. Replace its contents with the following code: 

    package com.liferay.docs.liferayguestbook.callback;
    
    import android.widget.Toast;
    
    import com.liferay.docs.liferayguestbook.activity.MainActivity;
    import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
    import com.liferay.docs.liferayguestbook.model.EntryModel;
    
    import java.util.ArrayList;
    import java.util.List;
    
    import org.json.JSONArray;
    import org.json.JSONObject;
    
    public class GetEntriesCallback extends GenericAsyncTaskCallback<List<EntryModel>> {
    
        private MainActivity.EntriesFragment _fragment;

        public GetEntriesCallback(MainActivity.EntriesFragment fragment) {
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

For now, don't worry about the errors for `MainActivity.EntriesFragment` and 
`reloadEntries`. You'll take care of this in a moment. Your app now contains the 
basic infrastructure it needs to retrieve a guestbook's entries, but it still 
can't display them. This is addressed in the following section. 

## Displaying Entries

To display the entries in the UI, you need to create variables for holding the
entries and the adapter. First though, you need to rename the fragment into
which you'll put them. In `MainActivity`, scroll down until you find the nested
`PlaceholderFragment` class. Android Studio created this class when you created
the activity from the Navigation Drawer Activity template. You're expected to
use it to display the drawer selection (in this case, guestbook entries).
However, `PlaceholderFragment` isn't a very meaningful name. Right click
`PlaceholderFragment` in the class declaration and select *Refactor* &rarr;
*Rename* in the context menu. Type `EntriesFragment` and then hit *Enter*. Note
that the errors for `EntryFragment` in your `GetEntriesCallback` are now gone.

Now you need to change the class `EntriesFragment` extends from `Fragment` to 
`ListFragment`. Once you make this change, the class declaration should look 
like this: 

    public static class EntriesFragment extends ListFragment {

Make sure that you're importing the Android Support Library version of 
`ListFragment`. Check the file's import statements and make sure that it appears 
as follows: 

    import android.support.v4.app.ListFragment;

So what's so special about `ListFragment`? Why use it instead of `Fragment`? In 
short, `ListFragment` contains a default `ListView` layout that you can use 
instead of having to write your own. In fact, go ahead and delete 
`layout/fragment_main.xml`; you don't need it anymore. Android Studio now shows 
errors in the `onCreateView` method of `EntriesFragment`, indicating that it 
can't find `fragment_main.xml`. You'll address this in a moment.

First though, you need to add variables for the entries and the adapter. Add 
them to `EntriesFragment` as shown here:

    public List<EntryModel> _entries = new ArrayList<EntryModel>();
    private ArrayAdapter _adapter;

Now you're ready to tackle the errors in the `onCreateView` method by deleting 
it. Yes, you read that correctly. Delete the entire `onCreateView` method. Don't 
get too excited though. You're not being ushered into a magical new place where 
you can simply delete problematic code. Since you're using the default layout of 
`ListFragment`, you don't need to override `onCreateView` to inflate and return 
your own layout. But what about using `onCreateView` to create the adapter and 
set it to the layout, as you did to display guestbooks in the drawer? For 
entries, you need to wait until later in the [fragment lifecycle](http://developer.android.com/guide/components/fragments.html#Creating). 
Specifically, you need to wait until the default implementation of `onCreateView` 
creates the layout. Then you can create the adapter and attach it to the layout. 
Since `onActivityCreated` follows `onCreateView` in the fragment lifecycle, 
you can use it to create and attach the adapter. Add the following 
`onActivityCreated` method to `EntriesFragment`: 

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    
        _adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_2,
                android.R.id.text1, _entries) {
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
    }

Note that the adapter uses the layout `simple_list_item_2`. This is another 
default Android layout. Recall that you used the default layout 
`simple_list_item_1` for each guestbook in the drawer. That layout is sufficient 
for showing guestbook names because it displays a single string for each list 
item. However, each entry needs to display two strings: the message, and the 
name of the person that left it. This is where `simple_list_item_2` comes in; it 
lets you display two strings for each list item. 

The `getView` method is overridden so that you can tell `simple_list_item_2` 
what two strings to display. After using `findViewById` to retrieve the layout's 
two strings, `setText` is used to set their content to an entry's message and 
name. The `setTypeface` and `setTextSize` methods are then used to format the 
message in bold and shrink the name, respectively. The `onActivityCreated` 
method concludes by using `setListAdapter` to set the adapter to the default 
`ListView` layout of `EntriesFragment`.

Now that you've taken care of the `ListView` and adapter, you can create the 
`reloadEntries` method. Similar to `reloadGuestbooks` in this learning path's 
previous article, `reloadEntries` needs to replace any existing model objects 
with the updated set retrieved from the portlet. It then needs to notify the 
adapter of that change. Place this method in the `EntriesFragment` class: 

    public void reloadEntries(List<EntryModel> entries) {
        _entries.clear();
        _entries.addAll(entries);

        _adapter.notifyDataSetChanged();
    }

Next, you need a method for making the call to retrieve the entries from the 
portlet. Recall that you retrieved guestbooks with the `getGuestbooks` method. 
You'll retrieve the entries similarly with the `getEntries` method. Add it as 
follows to the `EntriesFragment` class: 

    protected void getEntries(long guestbookId) {
        Session session = new SessionImpl("http://10.0.2.2:8080", 
            new BasicAuthentication("joebloggs@liferay.com", "test"));
        GetEntriesCallback callback = new GetEntriesCallback(this);
        session.setCallback(callback);
        EntryService service = new EntryService(session);

        try {
            service.getEntries(SITE_ID, guestbookId);
        }
        catch (Exception e) {
            String message = "Couldn't get entries " + e.getMessage();

            Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

As with the `getGuestbooks` method, `getEntries` creates a session, sets the 
session's callback, creates a service object, and then makes the call to the 
portlet. The only difference is that `getEntries` takes a guestbook's ID as a 
parameter, which it then uses to request that guestbook's entries. 

Awesome! Now you're ready to put `getEntries` to work! As with `getGuestbooks`, 
this is done from the `MainActivity` class. First, get a reference to 
`EntriesFragment` by defining the following variable in `MainActivity`: 

    private EntriesFragment mEntriesFragment;

There are two situations in which your app needs to call `getEntries`: on app 
startup, and when a guestbook is manually selected from the drawer. On startup, 
your app defaults to show the first guestbook's entries. Therefore, `getEntries` 
needs to be called for the first guestbook retrieved from the portlet. This 
needs to be done in the `reloadGuestbooks` method. This method is also where you 
should set the action bar's title to this guestbook's name. Add the following 
code to the end of `reloadGuestbooks`: 

    GuestbookModel guestbook = _guestbooks.get(0);
    mTitle = guestbook.getGuestbookName();
    restoreActionBar();
    
    mEntriesFragment.getEntries(guestbook.getGuestbookId());
    
This code gets the first guestbook in the list and sets the action bar title 
variable `mTitle` to the guestbook's name. This change is then processed by 
`restoreActionBar()`. The `getEntries` method then makes the call to retrieve 
the guestbook's entries. 

When the user manually selects a guestbook from the drawer, the 
`onNavigationDrawerItemSelected` method is called. This method therefore needs 
to contain a `getEntries` call with the selected guestbook's ID. Replace the 
contents of `onNavigationDrawerItemSelected` with the following code: 

    FragmentManager fragmentManager = getSupportFragmentManager();
    mEntriesFragment = EntriesFragment.newInstance(position + 1);
    fragmentManager.beginTransaction().replace(R.id.container, mEntriesFragment).commit();
    
    if(_guestbooks.size() > 0) {
        GuestbookModel guestbook = _guestbooks.get(position);
        mEntriesFragment.getEntries(guestbook.getGuestbookId());
    }

This code first uses a fragment transaction to replace the existing 
`EntriesFragment` instance with a new one. For more detailed information on 
managing fragments, see [this section of Android's fragment documentation](http://developer.android.com/guide/components/fragments.html#Managing). 
The new `EntriesFragment` instance is then used with `getEntries` to retrieve 
the selected guestbook's entries, provided there are guestbooks in the list. 

Now there's just one more thing left to do! You need to get rid of the hardcoded 
`"Section *"` strings that are set to the action bar's title when a guestbook is 
selected in the drawer. This is done in the `onSectionAttached` method. Replace 
its contents with the following code: 

    if (_guestbooks.size() > 0) {
        mTitle = _guestbooks.get(number - 1).getGuestbookName();
    } else {
        mTitle = "";
    }

This code sets the action bar's title to the selected guestbook's name, provided 
there's a guestbook in the list. Otherwise, an empty string is used. That's it! 
Make sure your portal is running and then run the app. The entries now show up 
when a guestbook is selected in the drawer. 

![Figure 2: The entries for the selected guestbook now display in your app.](../../images/android-guestbook-entries.png)

Congratulations! Up to this point, you've successfully built the Liferay Mobile 
SDK and used it in an Android app to to retrieve and display a custom portlet's 
entities. That's all the app does though: display the entities. Mobile users 
typically want more in an app. They want to be able to do all or most of the 
things they can do in the portlet. In the next series of articles in this 
learning path, you'll add the functionality to let users add, update, and delete 
guestbooks and entries.
