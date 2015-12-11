# Using Get Entries Screenlet [](id=using-get-entries-screenlet)

You can use Get Entries Screenlet by following the same basic steps as the 
previous article: insert the Screenlet XML in an activity or fragment layout, 
and then implement the Screenlet's listener in the activity or fragment class. 
In this article, you'll follow these steps to insert Get Entries Screenlet in 
`EntriesFragment`. You must also use a 
[fragment transaction](http://developer.android.com/guide/components/fragments.html#Transactions) 
to display `EntriesFragment` in `GuestbooksActivity`. 

First, though, you need to create a layout file for `EntriesFragment`.

## Inserting the Screenlet in the Layout [](id=inserting-the-screenlet-in-the-layout)

When you used the Guestbook Mobile SDK directly, you didn't need to create a 
layout file for `EntriesFragment`. This was because `EntriesFragment` extended 
`ListFragment` and therefore contained an implicit `ListView` object. You used 
that `ListView` instead of having to define one in a layout file. The 
`EntriesFragment` class, however, no longer extends `ListFragment`. The 
`ListView` the Screenlet uses to display entries is part of the Screenlet's View 
class. To use Get Entries Screenlet with `EntriesFragment`, you need to create a 
layout file for the fragment and then put the Screenlet's XML in it. In your 
project's `res/layout` directory, create `fragment_entries.xml` and replace its 
contents with the following code:

    <com.liferay.docs.getentriesscreenlet.GetEntriesScreenlet
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:liferay="http://schemas.android.com/apk/res-auto"
        android:id="@+id/getentries_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        liferay:layoutId="@layout/entries_default"/>

As with Get Guestbooks Screenlet, you'll use the `android:id` value to create a 
reference to the Screenlet. First, though, you need to configure 
`EntriesFragment` to use this layout. Do so now by replacing the `onCreate` 
method in `EntriesFragment` with the following `onCreateView` method:

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entries, container, false);
        _guestbookId = getArguments().getLong("guestbookId");

        return view;
    }

This method inflates the `fragment_entries.xml` layout, sets `_guestbookId` to 
the `"guestbookId"` value from the bundle arguments, and returns the Android 
`View` object. 

Next, you need to implement the Screenlet's listener. 

## Implementing the Screenlet's Listener [](id=implementing-the-screenlets-listener)

Recall that to use a Screenlet, you must implement its listener. You'll do this 
for Get Entries Screenlet by implementing `GetEntriesListener` in 
`EntriesFragment`. First, change `EntriesFragment`'s class declaration to 
implement the `GetEntriesListener` interface. The class declaration should now 
look like this: 

    public class EntriesFragment extends Fragment implements GetEntriesListener {...

Android Studio marks this class declaration as an error because you haven't 
implemented the listener's methods yet. Do so now by adding the following 
methods to the class: 

    @Override
    public void onGetEntriesSuccess(List<EntryModel> entries) {
    }

    @Override
    public void onGetEntriesFailure(Exception e) {
        Toast.makeText(getActivity(), "Couldn't get entries " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(final EntryModel entry) {
    }

Note the `onGetEntriesSuccess` and `onItemClicked` methods are empty. You don't 
need to do anything in `onGetEntriesSuccess` because the Screenlet's View 
already displays the entries without any other action required from the app 
developer. You could put a Toast or log message in this method to indicate 
success, but it isn't required. The `onItemClicked` implementation exists to let 
the app respond when the user selects an entry in the UI. For example, you may 
want to navigate to an activity or fragment that displays additional detail 
about the selected entry. The app doesn't yet, however, contain such an activity 
or fragment. For now, you can leave `onItemClicked` empty. The 
`onGetEntriesFailure` method is the only listener implementation that does 
something: it displays the error message to the user when the Screenlet fails to 
retrieve entries. 

Now you're ready to register `EntriesFragment` as the Screenlet's listener. 
You'll do this the same way you registered `GuestbooksActivity` as the Get 
Guestbooks Screenlet's listener in the previous article. After doing this, 
however, there's one additional step you need to take. You need to set Get 
Entries Screenlet's guestbook ID. The Screenlet needs this so it knows what 
guestbook to retrieve entries from. To register `EntriesFragment` as the 
Screenlet's listener, and set the Screenlet's guestbook ID, you need to get a 
reference to the Screenlet. Once you have this reference, you can call the 
Screenlet's `setListener` and `setGuestbookId` methods. Do this now by inserting 
the following code immediately above the `return` in the `onCreateView` method: 

    GetEntriesScreenlet getEntriesScreenlet = 
        (GetEntriesScreenlet) view.findViewById(R.id.getentries_screenlet);
    getEntriesScreenlet.setListener(this);
    getEntriesScreenlet.setGuestbookId(_guestbookId);

Awesome! Now you're ready to put this fragment to use.

## Adding the Fragment to the Activity [](id=adding-the-fragment-to-the-activity)

When you used the Guestbook Mobile SDK directly in the fragment, you displayed 
the entries in the activity by using a 
[fragment transaction](http://developer.android.com/guide/components/fragments.html#Transactions). 
You'll do the same thing here. Since you need to display the selected 
guestbook's entries, you must perform the fragment transaction in the 
`onItemClicked` method of `GuestbooksActivity`. Currently, `onItemClicked` only 
changes the Action Bar's title to the selected guestbook's name and then closes 
the navigation drawer. Now you'll add `EntriesFragment` to this method via a 
fragment transaction. Replace the `onItemClicked` method in `GuestbooksActivity` 
with the following code: 

    @Override
    public void onItemClicked(final GuestbookModel guestbook) {
        actionBar.setTitle(guestbook.getName());

        EntriesFragment entriesFragment = EntriesFragment.newInstance(guestbook.getGuestbookId());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, entriesFragment);
        transaction.commit();

        drawer.closeDrawers();
    }

In addition to the same `setTitle` and `closeDrawers` calls, `onItemClicked` now 
contains the fragment transaction. Note that this fragment transaction is 
identical to the one you used earlier in this Learning Path. You even use the 
same fragment container. 

Great job! You're finished! Run the app in the emulator and log in with your 
credentials. Your app now uses the Get Guestbooks and Get Entries Screenlets to 
show the same guestbooks and entries as the Guestbook portlet. 

![Figure 1: Get Entries Screenlet displays guestbook entries in your app.](../../images/android-guestbooks-entries-screenlets.png)

Congratulations! Now you know how to use the Mobile SDK and Liferay Screens. You 
even know how to display content from a custom portlet by writing your own 
Screenlets. This opens up a world of possibilities for developing your own apps. 
Although you learned a great deal in this Learning Path, there's still more. You 
can customize your Screenlet's appearance, package it for redistribution, and 
even configure it to receive push notifications. These topics, and more, are 
covered in 
[the tutorials on Android apps with Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/android-apps-with-liferay-screens). 
