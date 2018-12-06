# Using the List Screenlet [](id=using-the-list-screenlet)

You can now use your new list Screenlet 
[the same way you use any other Screenlet](/develop/tutorials/-/knowledge_base/7-1/using-screenlets-in-android-apps): 

1. Insert the Screenlet's XML in the layout of the activity or fragment you want 
   to use the Screenlet in. For example, here's Bookmark List Screenlet's XML: 

        <com.liferay.mobile.screens.listbookmark.BookmarkListScreenlet
            android:id="@+id/bookmarklist_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:comparator="FULLY_QUALIFIED_COMPARATOR_CLASS"
            app:folderId="YOUR_FOLDER_ID"
            app:groupId="YOUR_GROUP_ID"
            app:layoutId="@layout/list_bookmarks"/>

    Note that to set a comparator, you must use its fully qualified class name. 
    For example, to use 
    [the Bookmarks portlet's `EntryURLComparator`](https://github.com/liferay/liferay-portal/blob/7.0.6-ga7/modules/apps/collaboration/bookmarks/bookmarks-api/src/main/java/com/liferay/bookmarks/util/comparator/EntryURLComparator.java), 
    set `app:comparator` in the Screenlet XML as follows: 

        app:comparator="com.liferay.bookmarks.util.comparator.EntryURLComparator"

2. Implement the Screenlet's listener in the activity or fragment class. If your 
   list Screenlet doesn't have a custom listener, then you can do this by 
   implementing `BaseListListener` parameterized with your model class. For 
   example:

        public class YourListActivity extends AppCompatActivity 
            implements BaseListListener<YourModelClass> {...

    If you created a custom listener for your list Screenlet, however, then your 
    activity or fragment must implement it instead. For example, recall that the 
    example Bookmark List Screenlet's listener is `BookmarkListListener`. To use 
    this Screenlet, you must therefore implement this listener in the class of 
    the activity or fragment that you want to use the Screenlet in. For example: 

        public class ListBookmarksActivity extends AppCompatActivity 
            implements BookmarkListListener {...

    See the full example of this 
    [here in GitHub](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/ListBookmarksActivity.java). 

Well done! Now you know how to create list Screenlets. 

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-1/using-screenlets-in-android-apps)
