# Using Guestbook List Screenlet

The basic steps for using Guestbook List Screenlet are the same as those for 
using any other Screenlet: 

1. Insert the Screenlet's XML in the activity or fragment layout you want the 
   Screenlet to appear in. 

2. Implement the Screenlet's listener in the activity or fragment class. 

Recall that you used these steps to insert Login Screenlet in `MainActivity`. 
You'll also follow these steps to insert Guestbook List Screenlet in 
`GuestbooksActivity`. 

## Inserting the Screenlet in the Layout

Recall that `activity_guestbooks.xml` defines `GuestbooksActivity`'s UI. Also 
recall that the `NavigationView` in `activity_guestbooks.xml` defines the 
navigation drawer. To put Guestbook List Screenlet in the drawer, you must 
insert the Screenlet's XML in the `NavigationView`. You must also remove the 
placeholder content from the `NavigationView`. To do these things, replace the 
`NavigationView` with the following: 

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_guestbooks">

        <com.liferay.docs.guestbooklistscreenlet.GuestbookListScreenlet
            android:id="@+id/guestbooklist_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingTop="@dimen/nav_header_height"
            app:layoutId="@layout/list_guestbooks"/>

    </android.support.design.widget.NavigationView>

Compared to the `NavigationView` it replaced, this `NavigationView` contains 
Guestbook List Screenlet's XML, and lacks the `app:menu` attribute. Recall that 
this attribute pointed to the menu resource file used to create the drawer's 
items. Since you're using the Screenlet to display the drawer's items (the 
guestbooks), you don't need `app:menu`. Delete the menu resource file 
`res/menu/activity_guestbooks_drawer.xml`. 

Note that Guestbook List Screenlet's XML strongly resembles Login Screenlet's 
XML. Both contain an `android:id` value that you can use in the activity to get 
a reference to the Screenlet. Both also use a `layoutId` attribute to specify 
the Screenlet's View. Guestbook List Screenlet's XML, however, differs by using 
the `android:paddingTop` attribute. This attribute's value, 
`@dimen/nav_header_height`, pads the top of the Screenlet by the height of the 
navigation drawer's header section. This prevents the Screenlet and drawer 
header from overlapping. 

Great! You must now implement the Screenlet's listener interface in 
`GuestbooksActivity`. 

## Implementing the Screenlet's Listener

To use a Screenlet, you must implement its listener methods in the class of the 
activity or fragment you want the Screenlet to appear in. How you implement 
these methods depends on how you want the Screenlet to function in your app. For 
example, when you used Login Screenlet you implemented `LoginListener` in 
`MainActivity`. You implemented this listener's `onLoginSuccess` and 
`onLoginFailure` methods to display a message to the user. You then changed the 
`onLoginSuccess` implementation to navigate from `MainActivity` to 
`GuestbooksActivity`. Since these methods are `void`, however, you could have 
left them empty. Obviously this wouldn't have made for a very useful app, but it 
highlights an important point: Screenlet listener methods let the app developer 
choose how to respond to the Screenlet's events. By implementing these methods, 
app developers can therefore control the how the Screenlet functions with their 
app. 

Before implementing Guestbook List Screenlet's listener, however, you should add 
a method that the listener methods can use to help display a guestbook and its 
entries. You might now be thinking, "Hey! I thought you said the Screenlets 
contain their own UIs? Why does the activity need special methods for displaying 
the Screenlets' entities? You're a phony!" Although a list Screenlet's UI 
displays the list of entities, the rest of the app's UI must still account for 
it. For example, consider the action bar. List Screenlets don't include an 
action bar, but you should still change the action bar's contents to reflect 
what's on the screen. When a guestbook is selected in Guestbook List Screenlet, 
the action bar's title should display that guestbook's name. You can accomplish 
this by calling a method that takes a `GuestbookModel` and sets the guestbook's 
name as the action bar's title. Add this method now to `GuestbooksActivity`: 

    public void showEntries(GuestbookModel guestbook) {

        _actionBar.setTitle(guestbook.getName());
    }

This method is called `showEntries` because you'll also use it to display the 
guestbook's entries via Entry List Screenlet (you'll add this code later). 
You'll call this method in the listener methods you'll implement to process a 
guestbook selection. 

Recall that Guestbook List Screenlet doesn't need any special listener methods: 
you'll use those defined in the list Screenlet framework's 
[`BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java). 
To do this, change `GuestbooksActivity`'s class declaration to implement 
`BaseListListener<GuestbookModel>`. The class declaration should now look like 
this: 

    public class GuestbooksActivity extends AppCompatActivity implements 
        BaseListListener<GuestbookModel> {...

To implement `BaseListListener`, you must implement the following methods:

- `onListPageFailed(BaseListScreenlet source, int startRow, int endRow, Exception e)`:
  Called when the server call to retrieve a page of items fails. This method's 
  arguments include the `Exception` generated when the server call failed. 
  You'll implement this method to show the user a 
  [toast](https://developer.android.com/guide/topics/ui/notifiers/toasts.html) 
  with an error message. 

- `onListPageReceived(BaseListScreenlet source, int startRow, int endRow, List<E> entries, int rowCount)`:
  Called when the server call to retrieve a page of items succeeds. Note that 
  this method's arguments include the list of objects retrieved from the server 
  (`entries`), and the page's start row (`startRow`) and end row (`endRow`). 
  Recall that by default, you want the activity to display the first guestbook's 
  entries. You'll use this method to do so because it receives the guestbooks 
  from the server. Note that because `startRow` and `endRow` change for each 
  page, a `startRow` of `0` corresponds to the first guestbook on the first 
  page. You'll use an `if` statement to select this guestbook, and then call 
  `showEntries`. 

- `onListItemSelected(E element, View view)`: Called when the user selects an 
  item in the list. This method's arguments include the selected list item 
  (`element`). To process the guestbook's selection, you'll call `showEntries` 
  in this method. You'll also close the navigation drawer following the 
  `showEntries` call. 

Add these method implementations now to your `GuestbooksActivity` class: 

    @Override
    public void onListPageFailed(BaseListScreenlet source, int startRow, int endRow, Exception e) {

        Toast.makeText(getApplicationContext(), "Page request failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListPageReceived(BaseListScreenlet source, int startRow, int endRow, 
        List<GuestbookModel> guestbooks, int rowCount) {
        
        if (startRow == 0) {
            showEntries(guestbooks.get(0));
        }
    }

    @Override
    public void onListItemSelected(GuestbookModel guestbook, View view) {

        showEntries(guestbook);
        _drawer.closeDrawers();
    }

Because `BaseListListener` extends the 
[`CacheListener` interface](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/cache/CacheListener.java), 
the activity must also implement `CacheListener`. `CacheListener` defines the 
methods your activity needs to work with 
[offline mode in Screens](/develop/tutorials/-/knowledge_base/6-2/architecture-of-offline-mode-in-liferay-screens). 
This requires you to implement the following methods: 

- `loadingFromCache`: Called when the Screenlet loads data from the cache. 

- `retrievingOnline`: Called when the Screenlet retrieves data from the server. 

- `storingToCache`: Called when the Screenlet stores data to the cache. 

Note that offline mode still functions properly regardless of how you implement 
these methods. They just give you an opportunity to respond to offline mode's 
actions should you need to. In this app you don't, so your implementations can 
be empty. Add these methods to your `GuestbooksActivity` class: 

    @Override
    public void loadingFromCache(boolean success) { }

    @Override
    public void retrievingOnline(boolean triedInCache, Exception e) { }

    @Override
    public void storingToCache(Object object) { }

Now that you've implemented the listener methods, you must set 
`GuestbooksActivity` as the listener. This is where the ID 
`guestbooklist_screenlet` that you set in the Screenlet's XML comes in handy. 
Add the following code to the end of the activity's `onCreate` method: 

    GuestbookListScreenlet screenlet = 
        (GuestbookListScreenlet) findViewById(R.id.guestbooklist_screenlet);
    screenlet.setListener(this);

This code first uses the ID `guestbooklist_screenlet` to get a reference to 
`GuestbookListScreenlet`. It then sets this `GuestbooksActivity` instance as the 
Screenlet's listener. 

Great! That's it! Your app's `GuestbooksActivity` now contains Guestbook List 
Screenlet. Now you're almost ready to use Entry List Screenlet. Before you do 
so, however, you must create a fragment to put it in. You'll do this next. 
