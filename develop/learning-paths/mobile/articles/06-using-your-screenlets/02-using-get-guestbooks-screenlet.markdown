# Using Get Guestbooks Screenlet [](id=using-get-guestbooks-screenlet)

The basic steps for using Get Guestbooks Screenlet are the same as those for 
using any other Screenlet. Recall that you use a Screenlet by placing it in the 
activity or fragment where you want it to appear. You'll use Get Guestbooks 
Screenlet by inserting it in `GuestbooksActivity`. The following steps 
illustrate this.

## Inserting the Screenlet in the Layout [](id=inserting-the-screenlet-in-the-layout)

When you used the Guestbook Mobile SDK directly, you displayed the guestbooks by 
placing a `ListView` inside the `NavigationView` of `activity_guestbooks.xml`. 
Since your Screenlet's View already contains a `ListView`, you no longer need 
the one in `activity_guestbooks.xml`. Replace the `ListView` in 
`activity_guestbooks.xml` with the following code:

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

To use a Screenlet, you must implement its listener methods. Note, however, that 
what you do in these methods depends on how you want the Screenlet to function 
in your app. First, change the class declaration in `GuestbooksActivity` to 
implement `GetGuestbooksListener`. The class declaration should now look like 
this: 

    public class GuestbooksActivity extends AppCompatActivity implements 
        GetGuestbooksListener {...

Android Studio marks this as an error because you haven't yet implemented the 
listener's methods. Do so now by adding these methods:

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
the selected guestbook's name, and then close the drawer. Also, recall that the 
Screenlet calls `onGetGuestbooksSuccess` when guestbooks successfully return 
from the server. Here, you use this method to call `onItemClicked` to 
programmatically select the first guestbook. Because the drawer is initially 
hidden from view, this ensures that the app displays content on first launch. If 
the Screenlet fails to retrieve guestbooks from the server, it calls 
`onGetGuestbooksFailure`. You therefore use this method to display the error 
message to the user. 

Now that you've implemented the listener's methods, you need to set 
`GuestbooksActivity` as the listener. This is where the id 
`getguestbooks_screenlet` that you set in the layout XML comes in handy. Add the 
following code to the end of the activity's `onCreate` method: 

    GetGuestbooksScreenlet getGuestbooksScreenlet = (GetGuestbooksScreenlet) 
        findViewById(R.id.getguestbooks_screenlet);
    getGuestbooksScreenlet.setListener(this);

This code first gets a reference to `GetGuestbooksScreenlet`, and then sets the 
current `GuestbooksActivity` instance as the Screenlet's listener. 

Great! That's it! Now you're ready to use Get Entries Screenlet. The next 
article shows you how to do this.
