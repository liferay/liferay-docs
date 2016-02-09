# Using Get Guestbooks Screenlet [](id=using-get-guestbooks-screenlet)

The basic steps for using Get Guestbooks Screenlet are the same as those for 
using any other Screenlet: 

1. Insert the Screenlet's XML in the activity or fragment layout you want the 
   Screenlet to appear in. 

2. Implement the Screenlet's listener in the activity or fragment class. 

Recall that you used these steps when you inserted Login Screenlet in 
`MainActivity`. You'll also follow these steps to insert Get Guestbooks 
Screenlet in `GuestbooksActivity`. 

## Inserting the Screenlet in the Layout [](id=inserting-the-screenlet-in-the-layout)

Recall that `activity_guestbooks.xml` defines `GuestbooksActivity`'s UI. Also 
recall that the `NavigationView` in `activity_guestbooks.xml` defines the 
navigation drawer. To put Get Guestbooks Screenlet in the drawer, you must 
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

        <com.liferay.docs.getguestbooksscreenlet.GetGuestbooksScreenlet
            android:id="@+id/getguestbooks_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingTop="@dimen/nav_header_height"
            liferay:layoutId="@layout/guestbooks_default"/>

    </android.support.design.widget.NavigationView>

This code requires you to add the XML namespace 
`xmlns:liferay="http://schemas.android.com/apk/res-auto"` alongside the other 
XML namespaces at the top of the file. 

When compared to the `NavigationView` it replaced, this `NavigationView` 
contains Get Guestbooks Screenlet's XML, and lacks the `app:menu` attribute. 
This attribute points to the menu resource file used to create the drawer's 
items. Since you're using the Screenlet to display the drawer's items (the 
guestbooks), you don't need to use `app:menu`. Delete the menu resource file 
`res/menu/activity_guestbooks_drawer.xml`. 

Get Guestbooks Screenlet's XML strongly resembles Login Screenlet's XML. Both 
contain an `android:id` value that you can use in the activity to get a 
reference to the Screenlet. Both also use the `liferay:layoutId` attribute to 
specify the Screenlet's View. Get Guestbooks Screenlet's XML, however, differs 
by using the `android:paddingTop` attribute. This attribute's value, 
`@dimen/nav_header_height`, pads the top of the Screenlet by the height of the 
navigation drawer's header section. This prevents the Screenlet and drawer 
header from overlapping. 

Great! You must now implement the Screenlet's listener interface, 
`GetGuestbooksListener`, in `GuestbooksActivity`. 

## Implementing the Screenlet's Listener [](id=implementing-the-screenlets-listener)

To use a Screenlet, you must implement its listener methods. You defined Get 
Guestbooks Screenlet's listener methods in `GetGuestbooksListener`. How you 
implement these methods depends on how you want the Screenlet to function in 
your app. For example, recall that when you used Login Screenlet you implemented 
`LoginListener` in `MainActivity`. You implemented this listener's 
`onLoginSuccess` and `onLoginFailure` methods to display a message to the user. 
You then changed the `onLoginSuccess` implementation to navigate from 
`MainActivity` to `GuestbooksActivity`. Since these methods are `void`, however, 
you could have left them empty. Obviously this wouldn't have made for a very 
useful app, but it highlights an important point: Screenlet listener methods let 
the app developer choose how to respond to the Screenlet's events. By 
implementing these methods, you can therefore control the how the Screenlet 
functions with your app. 

Now it's time to implement `GetGuestbooksListener` in `GuestbooksActivity`. 
First, change `GuestbooksActivity`'s class declaration to implement 
`GetGuestbooksListener`. The class declaration should now look like this: 

    public class GuestbooksActivity extends AppCompatActivity implements 
        GetGuestbooksListener {...

This also requires you to import 
`com.liferay.docs.getguestbooksscreenlet.GetGuestbooksListener`. 

Android Studio marks the class declaration as an error because you haven't yet 
implemented the listener's methods. Recall that `GetGuestbooksListener` defines 
the methods `onItemClicked`, `onGetGuestbooksSuccess`, and 
`onGetGuestbooksFailure`. You'll use these methods to respond to the Screenlet's 
actions. Add these method implementations to `GuestbooksActivity`: 

    @Override
    public void onItemClicked(final GuestbookModel guestbook) {
        actionBar.setTitle(guestbook.getName());
        drawer.closeDrawers();
    }

    @Override
    public void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks) {
        onItemClicked(guestbooks.get(0));
    }

    @Override
    public void onGetGuestbooksFailure(Exception e) {
        Toast.makeText(this, "Couldn't get guestbooks " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

You must also add the following imports:

    import android.widget.Toast;
    import java.util.List;
    import com.liferay.docs.model.GuestbookModel;

Recall that the Screenlet calls `onItemClicked` when a guestbook is selected in 
the list. You therefore use this method to set the Action Bar's title to the 
selected guestbook's name, and then close the navigation drawer. Also, recall 
that the Screenlet calls `onGetGuestbooksSuccess` when guestbooks successfully 
return from the server. You therefore call `onItemClicked` in this method to 
programmatically select the first guestbook. Because the navigation drawer is 
initially hidden from view, this ensures that the app displays content on first 
launch. The final listener method implemented is `onGetGuestbooksFailure`. The 
Screenlet calls this method if it fails to retrieve guestbooks from the server. 
You therefore use this method to display the resulting error message to the 
user. 

Now that you've implemented the listener's methods, you need to set 
`GuestbooksActivity` as the listener. This is where the ID 
`getguestbooks_screenlet` that you set in the Screenlet's XML comes in handy. 
Add the following code to the end of the activity's `onCreate` method: 

    GetGuestbooksScreenlet getGuestbooksScreenlet = (GetGuestbooksScreenlet) 
        findViewById(R.id.getguestbooks_screenlet);
    getGuestbooksScreenlet.setListener(this);

You must also import 
`com.liferay.docs.getguestbooksscreenlet.GetGuestbooksScreenlet`. 

This code first uses the ID `getguestbooks_screenlet` to get a reference to 
`GetGuestbooksScreenlet`. It then sets this `GuestbooksActivity` instance as the 
Screenlet's listener. 

Great! That's it! Your app's `GuestbooksActivity` now contains Get Guestbooks 
Screenlet. Before you can use Get Entries Screenlet, however, you must create a 
fragment to put it in. You'll do this next. 
