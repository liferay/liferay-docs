# Retrieve and Display Entries

If you've completed the previous articles in this learning path, then you have 
an Android app that retrieves and displays guestbooks from a Guestbook portlet 
in Liferay. However, that's all it does. Tapping a guestbook in the navigation 
drawer doesn't show any of the guestbook's entries. In fact, tapping a guestbook 
doesn't do anything besides close the drawer to show an empty screen with the 
action bar. The action bar title is also still hardcoded with the `"Section *"` 
strings created as placeholders by Android Studio. 

![Figure 1: The guestbook entries aren't showing yet in the app.](../../images/android-entries-empty.png)

In this article you'll add the functionality that retrieves a guestbook's 
entries from the portal. Although the details differ slightly, you'll follow the 
same basic pattern you did when retrieving guestbooks from the portal: 
encapsulate the portlet entity and then create a callback class. It's time to 
get started by encapsulating the entries!

## Encapsulating the Entries

As with guestbooks, entries retrieved from the portlet should be encapsulated in 
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
handled by a callback class. Create a new class called `GetEntriesCallback` in 
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

Don't worry about the errors for `MainActivity.EntriesFragment` and 
`reloadEntries`. You'll take care of this in a moment. Your app now contains the 
basic infrastructure it needs to retrieve a guestbook's entries, but it still 
can't display them. This is addressed in the sections that follow. 

## Creating an Adapter

Remember when you first displayed the guestbooks in the drawer and they appeared 
as literal strings of `GuestbookModel` objects? That was a bummer. Since you 
already know why that happened, there's no need to repeat the same mistake here. 
You need to create a custom adapter for the `EntryModel` objects, just as you 
did for the `GuestbookModel` objects, so they display properly in your app. 
You'll begin by creating a layout file for them. Right click the `res/layout` 
directory and select *New* &rarr; *Layout resource file*. In the dialog that 
appears, name the file `item_entry.xml` and accept the defaults for the rest of 
the fields by clicking *OK*. When the new file opens, switch to the text view by 
clicking the *Text* tab at the bottom of the file. Replace its contents with the 
following code:

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
by the `android:orientation` attribute's value. Since that attribute's value 
here is `vertical`, the second `TextView` appears immediately below the first 
when the layout renders. The first `TextView` is for the guestbook message. The 
second `TextView` is for the name of the person that left that message. Note 
that the message text is set larger (`20sp` vs `12sp`). Since screen space is 
limited, this lets the message dominate while still showing who left it. Switch 
to design view to see the layout's preview with the placeholder text.

![Figure 2: A preview of each entry's layout.](../../images/android-entry-preview.png)

Now you need to create an adapter class. The adapter class uses the layout to 
render each `EntryModel` object retrieved from a guestbook in the portlet. 
Create a new class called `EntriesAdapter` in your app's `adapter` package. 
Replace its contents with the following code: 

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
`item_entry` layout and `EntryModel` class are used. Now that you have an 
adapter to properly format entries for display, you're ready to add the logic 
that makes the call to the portlet and displays the entries in the app.

## Displaying Entries

To display the entries in the UI, you first need to create variables for holding 
the entries and the adapter. First though, you should understand where you'll 
put them. In `MainActivity`, scroll down until you find the nested 
`PlaceholderFragment` class. Android Studio created this class when you created 
the activity from the Navigation Drawer Activity template. You're expected to 
use `PlaceholderFragment` to display the drawer selection. Each drawer selction 
in your app is a guestbook, so you'll use `PlaceholderFragment` to display the 
selected guestbook's entries. First though, you should change its name. Right 
click `PlaceholderFragment` in the class declaration and select 
*Refactor* &rarr; *Rename* in the context menu. Type `EntriesFragment` and then 
hit *Enter*. Add the following variables in `EntriesFragment`:

    public List<EntryModel> _entries = new ArrayList<EntryModel>();
    private EntriesAdapter _adapter;

Now that you have variables for the guestbook entries and the adapter, you can 
create the `reloadEntries` method. Place this method in the `EntriesFragment` 
class: 

    public void reloadEntries(List<EntryModel> entries) {
      _entries.clear();
      _entries.addAll(entries);
    
      _adapter.notifyDataSetChanged();
    }
    
Next, add the `getEntries` method in the `EntriesFragment` class: 

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

Now replace the contents of `onCreateView` in `EntriesFragment` with the 
following code:

    ListView entriesListView = (ListView) inflater.inflate(R.layout.fragment_main, container, false);
    _adapter = new EntriesAdapter(this.getActivity(), _entries);
    entriesListView.setAdapter(_adapter);
    return entriesListView;

You also need to make some changes to `fragment_main.xml`. Change 
`RelativeLayout` to `ListView`, and delete the `TextView` inside of it. Great! 
Now you're ready to make the calls to retrieve guestbook entries. You'll do this 
in the `MainActivity` class. First, you need a reference to `EntriesFragment`. 
Add the following variable to `MainActivity`:

    private EntriesFragment mEntriesFragment;

Next, add the following code to the end of the `reloadGuestbooks` method: 

    GuestbookModel guestbook = _guestbooks.get(0);
    mTitle = guestbook.getGuestbookName();
    restoreActionBar();
    
    mEntriesFragment.getEntries(guestbook.getGuestbookId());
    
This gets the entries for the first guestbook in the list when the app first 
starts up. It begins by setting the guestbook's name as the action bar's title 
using the `restoreActionBar()` method. The `getEntries` method then makes the 
call to retrieve the entries for that guestbook. 

Next, replace the contents of the `onNavigationDrawerItemSelected` method with 
the following code:

    FragmentManager fragmentManager = getSupportFragmentManager();
    mEntriesFragment = EntriesFragment.newInstance(position + 1);
    fragmentManager.beginTransaction().replace(R.id.container, mEntriesFragment).commit();
    
    if(_guestbooks.size() > 0) {
      GuestbookModel guestbook = _guestbooks.get(position);
      mEntriesFragment.getEntries(guestbook.getGuestbookId());
    }

This code is called whenever a guestbook is selected in the drawer. The 
`FragmentManager` transaction replaces the existing `EntriesFragment` with a new 
one that corresponds with the selected guestbook. The `getEntries` method then 
retrieves the selected guestbook's entries.

Now you need to get rid of the hardcoded `"Section *"` strings that are set to 
the action bar title. This is done in the `onSectionAttached` method. Replace 
its contents with the following code: 

    if (_guestbooks.size() > 0) {
      mTitle = _guestbooks.get(number - 1).getGuestbookName();
    } else {
      mTitle = "";
    }

As long as there is a guestbook in the list, this code sets the action bar's 
title to its name. Otherwise, an empty string is used. That's it! Make sure your 
portal is running and then run the app. The entries now show up when a guestbook 
is selected in the drawer.

![Figure 3: The entries for the selected guestbook now display in your app.](../../images/android-guestbook-entries.png)

Congratulations! Up to this point, you've successfully built the Liferay Mobile 
SDK and used it in an Android app to to retrieve and display a custom portlet's 
entities. That's all the app does though: display the entities. Mobile users 
want more in an app though. They want to be able to do all or most of the things 
they can do in the portlet. In the next series of articles in this learning 
path, you'll add the functionality to let users add, update, and delete 
guestbooks and entries.
