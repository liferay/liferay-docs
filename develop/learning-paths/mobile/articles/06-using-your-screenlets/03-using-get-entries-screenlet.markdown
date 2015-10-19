# Using Get Entries Screenlet

You'll use Get Entries Screenlet by following the same basic steps as you did in 
the previous article to use Get Guestbooks Screenlet: insert the Screenlet XML 
in an activity or fragment layout, and then configure the activity or fragment 
class to listen for the Screenlet's events. The following steps show you how to 
use Get Entries Screenlet in `EntriesFragment`.

## Inserting the Screenlet in the Layout

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

Next, you need to implement the Screenlet's `GetEntriesListener` interface in 
`EntriesFragment`.

## Implementing the Screenlet's Listener

