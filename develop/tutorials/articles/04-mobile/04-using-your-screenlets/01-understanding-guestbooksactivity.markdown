# Understanding GuestbooksActivity's UI [](id=understanding-guestbooksactivitys-ui)

You used Android Studio's Navigation Drawer Activity template to create 
`GuestbooksActivity`. Any activity created by this template contains a 
navigation drawer and all the components the activity needs. This includes 
layout files that display content. Currently, these files contain simple 
placeholder content. You'll replace this content shortly with dynamic portal 
content. Before doing so, you should know where the placeholder content exists 
in the project's structure and how the app displays it. 

The app's UI is defined by three layout files: `activity_guestbooks.xml`, 
`app_bar_guestbooks.xml`, `content_guestbooks.xml`. These layouts combine to
display the app's content. 

## Understanding the Activity's Layout File [](id=understanding-the-activitys-layout-file)

First, open `GuestbooksActivity`'s layout file, `activity_guestbooks.xml`. This 
file should look similar to this one:

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

This is a small amount of code considering everything that's in 
`GuestbooksActivity`. The `NavigationView` and parent `DrawerLayout` define the 
navigation drawer. Two attributes in `NavigationView` define the drawer's 
contents: `app:headerLayout` and `app:menu`. The `app:headerLayout` setting 
`"@layout/nav_header_guestbooks"` specifies that the layout defined in 
`res/layout/nav_header_guestbooks.xml` is used to render the drawer's header 
section. The `app:menu` setting `"@menu/activity_guestbooks_drawer"` specifies 
that the menu defined in `res/menu/activity_guestbooks_drawer.xml` is used to 
create the drawer's items. Above the `NavigationView`, the `include` statement 
adds the layout defined in `app_bar_guestbooks.xml` as the activity's main 
content (the content shown when the navigation drawer is closed). The following 
diagram illustrates how `activity_guestbooks.xml` maps to the UI. 

![Figure 1: The layout `activity_guestbooks.xml` defines the app's main UI components.](../../../images/android-activity-guestbooks.png)

The activity's main content also contains a toolbar, some text, and a 
[floating action button](https://www.google.com/design/spec/components/buttons-floating-action-button.html). 
Next, you'll see how these are defined. 

## Understanding the Activity's Main Content Layout [](id=understanding-the-activitys-main-content-layout)

Open `app_bar_guestbooks.xml` so you can see how it defines the activity's main 
content. This file should look similar to this: 

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
            android:layout_height="wrap_content"
            android:layout_width="match_parent" 
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

The `AppBarLayout` and `Toolbar` elements define the Toolbar that appears at the 
top of the activity. Following the Toolbar definition, the `include` statement 
adds the layout `content_guestbooks.xml` to the `app_bar_guestbooks` layout. The 
`content_guestbooks` layout defines the content displayed in the activity's body 
(below the Toolbar). Right now, this layout only contains an empty 
`RelativeLayout` element. Now open `content_guestbooks.xml`. Its contents should 
look like this: 

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout 
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto" 
        android:layout_width="match_parent"
        android:layout_height="match_parent" 
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        android:paddingBottom="@dimen/activity_vertical_margin"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" 
        tools:showIn="@layout/app_bar_guestbooks"
        tools:context="com.liferay.docs.liferayguestbook.GuestbooksActivity">
            
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

Awesome! Now you know which layout files in the project define 
`GuestbookActivity`'s UI. You also know the exact UI components defined in these 
files. Next, you'll prepare `GuestbookActivity` for Get Guestbooks Screenlet. 
