# Retrieve and Display Entries

If you completed the previous articles in this learning path, then you have an 
Android app that retrieves and displays guestbooks from the [MVC learning path's](/learning-paths/-/knowledge_base/6-2/beginning-liferay-development) 
Guestbook portlet. However, that's all your app does. Tapping a guestbook in its 
navigation drawer doesn't show the guestbook's entries. In fact, tapping a 
guestbook doesn't do anything besides close the drawer to show the action bar 
with an empty screen. Also, the action bar's title is still hardcoded with the 
`"Section *"` strings Android Studio created as placeholders. 

![Figure 1: Guestbook entries aren't showing yet in the app.](../../images/android-entries-empty.png)

This article walks you through the steps required to retrieve and display 
guestbook entries from the portal. As you did to display guestbooks, you'll use 
`ListView` to display entries in a list. The steps in this article follow the 
same basic pattern you used to retrieve and display guestbooks. For this reason, 
the concepts behind the steps aren't explained in detail here. Refer to this 
learning path's previous two articles for such an explanation. As you did when 
you wrote the code to retrive guestbooks from the portal, you'll begin here by 
encapsulating the objects being retrieved. 

## Encapsulating the Entries

As with guestbooks, entries retrieved from the portal should be encapsulated in 
your app. Create a new class in your app's `model` package by right clicking it 
and selecting *New* &rarr; *Java Class*. Name the new class *EntryModel*. 
Replace its contents with the following code: 

    package com.liferay.docs.mydrawers.model;
    
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
`GenericAsyncTaskCallback`. Create a new class called `GetEntriesCallback` in 
your app's `callback` package. Replace its contents with the following code: 

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
can't display them. This is addressed in the following sections. 

## Creating an Adapter

Remember a couple articles ago when you first displayed the guestbooks in the 
drawer? They appeared as literal strings of `GuestbookModel` objects. That was a 
bummer. Since you already know why that happened and how to fix it, there's no 
need to repeat the same mistake here. You need to create a custom adapter for 
the `EntryModel` objects so they display properly in your app, just as you did 
for the `GuestbookModel` objects. Remember that this adapter formats each item 
for display in the list.

Creating a layout file is the first step in creating this adapter. Right click 
the `res/layout` directory and select *New* &rarr; *Layout resource file*. In 
the dialog that appears, name the file `item_entry.xml`. Accept the defaults for 
the remaining fields and click *OK*. When the new file opens, click the *Text* 
tab to switch to its text view. Replace its contents with the following code: 

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:minHeight="?android:attr/listPreferredItemHeight"
        android:gravity="center_vertical" >
    
        <TextView
            android:id="@+id/entry_message"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textSize="20sp"
            android:textStyle="bold"
            android:text="This is the Entry message" />
    
        <TextView
            android:id="@+id/entry_name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_alignParentRight="true"
            android:textSize="12sp"
            android:text="This is the Entry name" />
    
    </LinearLayout>

This is a simple `LinearLayout` containing two `TextView` components. Recall 
that `LinearLayout` arranges its components linearly in the direction specified 
by the `android:orientation` attribute. Since that attribute's value here is 
`vertical`, the second `TextView` renders immediately below the first. So why 
two `TextView` components? The first `TextView` is for the guestbook message. 
The second is for the name of the person that left the message. Both can be fit 
into a single `ListView` item by adjusting the text size. At `12sp`, the name of 
the person who left the message is smaller than the message itself (`20sp`). 
This lets the message dominate while still showing who left it. Switch to the 
layout file's design view to see its preview with the placeholder text. 

![Figure 2: A preview of each entry's layout.](../../images/android-entry-preview.png)

Now that the layout is defined, you need to create an adapter class that extends 
Android's `ArrayAdapter` class. Your adapter class needs to make use of the 
`item_entry` layout to render each `EntryModel` object. Create a new class 
called `EntriesAdapter` in your app's `adapter` package. Replace its contents 
with the following code: 

    package com.liferay.docs.liferayguestbook.adapter;
    
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;
    
    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.liferayguestbook.model.EntryModel;
    
    import java.util.List;
    
    public class EntriesAdapter extends ArrayAdapter<EntryModel> {
    
      public EntriesAdapter(Context context, List<EntryModel> entries) {
        super(context, 0, entries);
      }
    
      @Override
      public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
    
        LayoutInflater inflater = LayoutInflater.from(getContext());
    
        if (view == null) {
            view = inflater.inflate(R.layout.item_entry, parent, false);
    
            holder = new ViewHolder();
    
            holder.entryMessage = (TextView)view.findViewById(R.id.entry_message);
            holder.entryName = (TextView)view.findViewById(R.id.entry_name);
    
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }
    
        EntryModel entry = getItem(position);
    
        holder.entryMessage.setText(entry.getMessage());
        holder.entryName.setText(entry.getName());
    
        return view;
      }
    
      class ViewHolder {
    
        TextView entryMessage, entryName;
      }
    }

This class is almost exactly the same as the `GuestbooksAdapter` class you wrote 
in this learning path's previous article. The only differences are that the 
`item_entry` layout and `EntryModel` class are used. Great! Now that you have an 
adapter to properly format entries for display, you're ready to add the logic 
that makes the portlet call and displays the entries in the UI. 

## Displaying Entries

To display the entries in the UI, you need to create variables for holding the 
entries and the adapter. First though, you need to rename the fragment you'll 
put them in. In `MainActivity`, scroll down until you find the nested 
`PlaceholderFragment` class. Android Studio created this class when you created 
the activity from the Navigation Drawer Activity template. You're expected to 
use it to display the drawer selection. Since each drawer selction in your app 
is a guestbook, you'll use `PlaceholderFragment` to display the selected 
guestbook's entries. However, `PlaceholderFragment` isn't a very meaningful 
name. To change its name, right click `PlaceholderFragment` in the class 
declaration and select *Refactor* &rarr; *Rename* in the context menu. Type 
`EntriesFragment` and then hit *Enter*. Note that the errors for `EntryFragment` 
in your `GetEntriesCallback` are now gone. Next, add the following variables in 
`EntriesFragment`:

    public List<EntryModel> _entries = new ArrayList<EntryModel>();
    private EntriesAdapter _adapter;

Now that you have variables for the guestbook entries and the adapter, you can 
create the `reloadEntries` method. Similar to `reloadGuestbooks` in the previous 
article of this learning path, `reloadEntries` needs to replace any existing 
model objects with the updated set retrieved from the portlet. It then needs to 
notify the adapter of that change. Place this method in the `EntriesFragment` 
class: 

    public void reloadEntries(List<EntryModel> entries) {
      _entries.clear();
      _entries.addAll(entries);
    
      _adapter.notifyDataSetChanged();
    }

Next, you need a method for making the call to retrive the entries from the 
portlet. Recall that you retrieved guestbooks with the `getGuestbooks` method. 
You'll retrieve the entries simliarly with the `getEntries` method. Add it as 
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

Before using `getEntries`, you need to make some changes to how the layout of 
`EntriesFragment` is processed and rendered. This layout is defined in 
`fragment_main.xml`. It's important to note that this layout is different than 
the one you defnied for the entries adapter. The entries adapter layout defines 
a single entry's appearance, while `fragment_main.xml` defines the appearance of 
all the entries on the screen. Since you want the entries to appear in a list, 
you need to change `fragment_main.xml` to use `ListView`. Begin by replacing the 
contents of `onCreateView` in `EntriesFragment` with the following code:

    ListView entriesListView = (ListView) inflater.inflate(R.layout.fragment_main, container, false);
    _adapter = new EntriesAdapter(this.getActivity(), _entries);
    entriesListView.setAdapter(_adapter);
    return entriesListView;

This casts the layout to a `ListView` upon inflation. An adapter is then created 
with the entries and set as the underlying data source for the `ListView`, which 
is then returned. Next, open `fragment_main.xml` and change `RelativeLayout` to 
`ListView`. You should also delete its `TextView`. 

Awesome! Now you're ready to put `getEntries` to work! As with `getGuestbooks`, 
this is done from the `MainActivity` class. First, get a reference to 
`EntriesFragment` by defining the following variable in `MainActivity`: 

    private EntriesFragment mEntriesFragment;

There are two situations in which your app needs to call `getEntries`: on 
startup, and when a guestbook is manually selected from the drawer. On startup, 
your app defaults to show the first guestbook's entries. Therefore, `getEntries` 
needs to be called with the first guestbook retrieved from the portlet. This 
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
managing fragments, see [that section of Android's fragment documentation](http://developer.android.com/guide/components/fragments.html#Managing). 
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

![Figure 3: The entries for the selected guestbook now display in your app.](../../images/android-guestbook-entries.png)

Congratulations! Up to this point, you've successfully built the Liferay Mobile 
SDK and used it in an Android app to to retrieve and display a custom portlet's 
entities. That's all the app does though: display the entities. Mobile users 
typically want more in an app. They want to be able to do all or most of the 
things they can do in the portlet. In the next series of articles in this 
learning path, you'll add the functionality to let users add, update, and delete 
guestbooks and entries.
