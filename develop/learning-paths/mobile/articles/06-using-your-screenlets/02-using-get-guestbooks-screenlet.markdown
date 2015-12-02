# Using Get Guestbooks Screenlet [](id=using-get-guestbooks-screenlet)

The basic steps for using Get Guestbooks Screenlet are the same as those for 
using any other Screenlet: 

1. Insert the Screenlet's XML in the activity or fragment layout you want it to 
   appear in. 

2. Implement the Screenlet's listener in the activity or fragment class. 

Recall that you used these steps when you inserted Login Screenlet in 
`MainActivity`. Likewise, you'll follow these steps to insert Get Guestbooks 
Screenlet in `GuestbooksActivity`. The following steps illustrate this. 

## Inserting the Screenlet in the Layout [](id=inserting-the-screenlet-in-the-layout)

When you used the Guestbook Mobile SDK directly in `GuestbooksActivity`, you 
displayed the guestbooks by placing a `ListView` inside the `NavigationView` of 
`activity_guestbooks.xml`. Since your Screenlet's View already contains a 
`ListView`, you no longer need the one in `activity_guestbooks.xml`. Replace the 
`ListView` in `activity_guestbooks.xml` with the following code: 

    <com.liferay.docs.getguestbooksscreenlet.GetGuestbooksScreenlet
        android:id="@+id/getguestbooks_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingTop="@dimen/nav_header_height"
        liferay:layoutId="@layout/guestbooks_default"/>

This is the Get Guestbook Screenlet's layout XML. Like the `ListView` it 
replaced, it also includes the `android:paddingTop` attribute that prevents the 
guestbooks from overlapping with the drawer header. Also, note the value for the 
`android:id` attribute. You'll use this value in a moment to get a reference to 
the Screenlet.

Next, you need to implement the Screenlet's `GetGuestbooksListener` interface in 
`GuestbooksActivity`. 

## Implementing the Screenlet's Listener [](id=implementing-the-screenlets-listener)

To use a Screenlet, you must implement its listener methods. You defined these 
methods for the Get Guestbooks Screenlet when you created 
`GetGuestbooksListener`. What you do in these methods when you implement them in 
an activity or fragment, however, depends on how you want the Screenlet to 
function in your app. For example, recall that when you used Login Screenlet you 
implemented `LoginListener` in `MainActivity`. You implemented `LoginListener`'s 
`onLoginSuccess` and `onLoginFailure` methods to display a message to the user. 
You then changed the `onLoginSuccess` implementation to navigate from 
`MainActivity` to `GuestbooksActivity`. Since these methods are `void`, however, 
you could have left them empty. Obviously this wouldn't have made for a very 
useful app, but it highlights an important point: Screenlet listener methods let 
the app developer choose how to respond to the Screenlet's events. By 
implementing a Screenlet's listener methods, you can therefore control the how 
the Screenlet functions with your app. 

Now it's time to implement `GetGuestbooksListener` in `GuestbooksActivity`. 
First, change `GuestbooksActivity`'s class declaration to implement 
`GetGuestbooksListener`. The class declaration should now look like this: 

    public class GuestbooksActivity extends AppCompatActivity implements 
        GetGuestbooksListener {...

Android Studio marks this as an error because you haven't yet implemented the 
listener's methods. Add these method implementations to `GuestbooksActivity` as 
follows: 

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

Recall that the Screenlet calls `onItemClicked` when a guestbook is selected 
from the list. You therefore use this method to set the Action Bar's title to 
the selected guestbook's name, and then close the navigation drawer. Also, 
recall that the Screenlet calls `onGetGuestbooksSuccess` when guestbooks 
successfully return from the server. In `GuestbooksActivity`, you use this 
method to call `onItemClicked` to programmatically select the first guestbook. 
Because the navigation drawer is initially hidden from view, this ensures that 
the app displays content on first launch. The final listener method implemented 
is `onGetGuestbooksFailure`. The Screenlet calls this method if it fails to 
retrieve guestbooks from the server. You therefore use this method to display 
the resulting error message to the user. 

Now that you've implemented the listener's methods, you need to set 
`GuestbooksActivity` as the listener. This is where the ID 
`getguestbooks_screenlet` that you set in the layout XML comes in handy. Add the 
following code to the end of the activity's `onCreate` method: 

    GetGuestbooksScreenlet getGuestbooksScreenlet = (GetGuestbooksScreenlet) 
        findViewById(R.id.getguestbooks_screenlet);
    getGuestbooksScreenlet.setListener(this);

This code first uses the ID `getguestbooks_screenlet` to get a reference to 
`GetGuestbooksScreenlet`. It then sets the current `GuestbooksActivity` instance 
as the Screenlet's listener. 

Great! That's it! Now you're ready to use Get Entries Screenlet. The next 
article shows you how to do this.
