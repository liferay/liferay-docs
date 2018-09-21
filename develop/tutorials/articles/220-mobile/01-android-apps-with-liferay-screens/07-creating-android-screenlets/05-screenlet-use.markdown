# Using Your Screenlet [](id=using-your-screenlet)

To use any Screenlet, you must follow these 
[general steps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps): 

1. Insert the Screenlet's XML in the activity or fragment layout you want the 
   Screenlet to appear in. You can fine-tune the Screenlet's behavior by setting 
   the Screenlet XML's attributes. 

2. Implement the Screenlet's listener in the activity or fragment class. 

As an example of this, the Liferay Screens 
[Test App](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app) 
uses Add Bookmark Screenlet. You can find the following Add Bookmark Screenlet 
XML in the 
[Test App's `add_bookmark.xml` layout](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/add_bookmark.xml): 

    <com.liferay.mobile.screens.bookmark.AddBookmarkScreenlet
        android:id="@+id/bookmark_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:folderId="@string/bookmark_folder"
        app:layoutId="@layout/bookmark_default" />

Note that the layout specified by `app:layoutId` (`bookmark_default`) 
matches the layout file of the Screenlet's View (`bookmark_default.xml`). This 
is how you specify the View that displays your Screenlet. For example, if Add 
Bookmark Screenlet had another View defined in a layout file named 
`bookmark_awesome.xml`, you could use that layout by specifying 
`@layout/bookmark_awesome` as the `app:layoutId` attribute's value. 

Also note that the `app:folderId` attribute specifies 
`@string/bookmark_folder` as the bookmark folder's ID. This is an alternative 
way of specifying an attribute's value. Instead of specifying the value 
directly, the Test App specifies the value in its 
[`server_context.xml` file](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/values/server_context.xml#L23-L24): 

    ...
	<string name="bookmark_folder">20622</string>
	...

This `name` attribute's value, `bookmark_folder` is then used in the Screenlet 
XML to set the `app:folderId` attribute to `20622`. 
