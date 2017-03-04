# Understanding GuestbooksActivity's UI [](id=understanding-guestbooksactivitys-ui)

Recall that you used Android Studio's Navigation Drawer Activity template to 
create `GuestbooksActivity`. Any activity created by this template contains a 
navigation drawer and all the components the activity needs. This includes 
layout files that display content. Currently, these files contain simple 
placeholder content. You'll replace this content shortly with content from your 
Liferay instance. Before doing so, however, you should know where the 
placeholder content exists in the project's structure and how the app displays 
it. 

The app's UI is defined by three layout files that combine to display the app's 
content: 

1. `activity_guestbooks.xml`: The activity's main layout file. This layout 
   defines the navigation drawer and includes the `app_bar_guestbooks` layout. 
   The latter appears when the navigation drawer is closed. 

2. `app_bar_guestbooks.xml`: Defines the action bar (app bar) and includes the 
   `content_guestbooks` layout. 

3. `content_guestbooks.xml`: Defines the activity's main content. This is the 
   content that appears below the action bar. 

First, you'll learn how `activity_guestbooks.xml` works. 

## Understanding the Activity's Main Layout File [](id=understanding-the-activitys-main-layout-file)

First, open `GuestbooksActivity`'s main layout file, `activity_guestbooks.xml`. 
This file should look similar to this one: 

    <?xml version="1.0" encoding="utf-8"?>
    <android.support.v4.widget.DrawerLayout 
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools" 
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent" 
        android:layout_height="match_parent"
        android:fitsSystemWindows="true" 
        tools:openDrawer="start">

        <include layout="@layout/app_bar_guestbooks" 
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

        <android.support.design.widget.NavigationView 
            android:id="@+id/nav_view"
            android:layout_width="wrap_content" 
            android:layout_height="match_parent"
            android:layout_gravity="start" 
            android:fitsSystemWindows="true"
            app:headerLayout="@layout/nav_header_guestbooks" 
            app:menu="@menu/activity_guestbooks_drawer" />

    </android.support.v4.widget.DrawerLayout>

This isn't much code considering everything that's in `GuestbooksActivity`. The 
`NavigationView` and its parent `DrawerLayout` define the navigation drawer. Two 
attributes in `NavigationView` define the drawer's contents: `app:headerLayout` 
and `app:menu`. The `app:headerLayout` value `"@layout/nav_header_guestbooks"` 
specifies that the layout defined in `res/layout/nav_header_guestbooks.xml` is 
used to render the drawer's header section. The `app:menu` value 
`"@menu/activity_guestbooks_drawer"` specifies that the menu defined in 
`res/menu/activity_guestbooks_drawer.xml` is used to create the drawer's items. 
Above the `NavigationView`, the `include` statement adds the layout defined in 
`app_bar_guestbooks.xml` as the content shown when the navigation drawer is 
closed. The following diagram illustrates how `activity_guestbooks.xml` maps to 
the UI. 

![Figure 1: The `activity_guestbooks.xml` layout defines the app's main UI components.](../../../images/android-activity-guestbooks.png)

The activity's main content also contains a toolbar (the action bar), some text, 
and a 
[floating action button](https://www.google.com/design/spec/components/buttons-floating-action-button.html). 
Next, you'll see how these are defined. 

## Understanding the app_bar_guestbooks and content_guestbooks Layouts [](id=understanding-the-appbarguestbooks-and-contentguestbooks-layouts)

Open `app_bar_guestbooks.xml`. It should look similar to this: 

    <?xml version="1.0" encoding="utf-8"?>
    <android.support.design.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools" 
        android:layout_width="match_parent"
        android:layout_height="match_parent" 
        android:fitsSystemWindows="true"
        tools:context="com.liferay.docs.liferayguestbook.GuestbooksActivity">

        <android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/AppTheme.AppBarOverlay">

            <android.support.v7.widget.Toolbar 
                android:id="@+id/toolbar"
                android:layout_width="match_parent" 
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary" 
                app:popupTheme="@style/AppTheme.PopupOverlay" />

        </android.support.design.widget.AppBarLayout>

        <include layout="@layout/content_guestbooks" />

        <android.support.design.widget.FloatingActionButton 
            android:id="@+id/fab"
            android:layout_width="wrap_content" 
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|end" 
            android:layout_margin="@dimen/fab_margin"
            android:src="@android:drawable/ic_dialog_email" />

    </android.support.design.widget.CoordinatorLayout>

The `AppBarLayout` and `Toolbar` elements define the toolbar that appears at the 
top of the activity. Following the toolbar definition, the `include` statement 
adds the `content_guestbooks` layout to the `app_bar_guestbooks` layout. The 
`content_guestbooks` layout defines the content displayed in the activity's body 
(below the toolbar). Right now, this layout only contains an empty 
`RelativeLayout` element. Now open `content_guestbooks.xml`. Its contents should 
look like this: 

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/content_guestbooks"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        tools:context="com.liferay.docs.liferayguestbook.GuestbooksActivity"
        tools:showIn="@layout/app_bar_guestbooks">

    </RelativeLayout>

Anything you define in this layout becomes the activity's main body content. 
Later, you'll return to `content_guestbooks.xml` to display the guestbook 
entries retrieved from the Guestbook portlet. 

Now return to `app_bar_guestbooks.xml`. This layout concludes by using 
`FloatingActionButton` to define the floating action button. Pressing this 
button in the app slides a 
[snackbar](https://www.google.com/design/spec/components/snackbars-toasts.html) 
containing placeholder content up from the bottom of the screen. Although you 
won't do anything with the floating action button in this Learning Path, you'll 
leave it in place. When you finish the app, you may wish to test your Liferay 
mobile development chops by adding functionality to this button. 

The following figure illustrates how the layout defined in 
`app_bar_guestbooks.xml` maps to the activity's UI. On the left, this figure 
lists each UI component defined in `app_bar_guestbooks.xml`. An arrow drawn from 
each points to its rendering on the right. 

![Figure 2: The layout `app_bar_guestbooks.xml` defines the activity's main content.](../../../images/android-app-bar-guestbooks.png)

Awesome! Now you know which layout files in the project define the app's UI. You 
also know the exact UI components defined in these files. Next, you'll prepare 
`GuestbooksActivity` for Guestbook List Screenlet. 
