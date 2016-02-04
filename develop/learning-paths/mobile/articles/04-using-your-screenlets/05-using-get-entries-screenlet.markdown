# Using Get Entries Screenlet [](id=using-get-entries-screenlet)

You'll use Get Entries Screenlet by following the same basic steps to use any 
Screenlet: insert the Screenlet XML in an activity or fragment layout, and then 
implement the Screenlet's listener in the activity or fragment class. In this 
article, you'll follow these steps to insert Get Entries Screenlet in 
`EntriesFragment`. 

First, you'll insert Get Entries Screenlet's XML in `fragment_entries.xml`. 

## Inserting the Screenlet in the Layout [](id=inserting-the-screenlet-in-the-layout)

Inserting Get Entries Screenlet's XML is very simple. Since all you want 
`fragment_entries.xml` to do is display the Screenlet, all it needs to contain 
is the Screenlet XML. Replace the contents of `fragment_entries.xml` with the 
following code:

    <com.liferay.docs.getentriesscreenlet.GetEntriesScreenlet
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:liferay="http://schemas.android.com/apk/res-auto"
        android:id="@+id/getentries_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        liferay:layoutId="@layout/entries_default"/>

As with Get Guestbooks Screenlet, you'll use the `android:id` value to create a 
reference to the Screenlet. Next, you must implement the Screenlet's listener. 

## Implementing the Screenlet's Listener [](id=implementing-the-screenlets-listener)

Recall that to use a Screenlet, you must implement its listener. You'll do this 
for Get Entries Screenlet by implementing `GetEntriesListener` in 
`EntriesFragment`. First, change `EntriesFragment`'s class declaration to 
implement the `GetEntriesListener` interface. The class declaration should now 
look like this: 

    public class EntriesFragment extends Fragment implements GetEntriesListener {...

You must also import `com.liferay.docs.getentriesscreenlet.GetEntriesListener`. 
Android Studio marks the class declaration as an error because you haven't 
implemented the listener's methods yet. Recall that `GetEntriesListener` defines 
the methods `onGetEntriesSuccess`, `onGetEntriesFailure`, and `onItemClicked`. 
As with Get Guestbooks Screenlet's listener, you need these methods to respond 
to the Screenlets actions. Implement these methods now: 

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

This requires that you add the following imports:

    import com.liferay.docs.model.EntryModel;
    import android.widget.Toast;
    import java.util.List;

In the listener method implementations, note that the `onGetEntriesSuccess` and 
`onItemClicked` methods are empty. You don't need to do anything in 
`onGetEntriesSuccess` because the Screenlet's View already displays the entries 
without any other action required from the app developer. You could put a Toast 
or log message in this method to indicate success, but it isn't required. The 
`onItemClicked` implementation exists to let the app respond when the user 
selects an entry in the UI. For example, you may want to navigate to an activity 
or fragment that displays additional detail about the selected entry. The app 
doesn't yet, however, contain such an activity or fragment. For now, leave 
`onItemClicked` empty. The `onGetEntriesFailure` method is the only listener 
implementation that does something: it displays the error message to the user 
when the Screenlet fails to retrieve entries. 

Now you're ready to register `EntriesFragment` as the Screenlet's listener. 
You'll do this the same way you registered `GuestbooksActivity` as the Get 
Guestbooks Screenlet's listener in the previous article. After doing this, 
however, there's one additional step you need to take. You need to set Get 
Entries Screenlet's guestbook ID. The Screenlet needs this so it knows what 
guestbook to retrieve entries from. To register `EntriesFragment` as the 
Screenlet's listener, and set the Screenlet's guestbook ID, you need to get a 
reference to the Screenlet. Once you have this reference, you can call the 
Screenlet's `setListener` and `setGuestbookId` methods. Do this now by replacing 
the `onCreateView` method with the following updated version:

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entries, container, false);
        _guestbookId = getArguments().getLong("guestbookId");

        GetEntriesScreenlet getEntriesScreenlet = 
            (GetEntriesScreenlet) view.findViewById(R.id.getentries_screenlet);
        getEntriesScreenlet.setListener(this);
        getEntriesScreenlet.setGuestbookId(_guestbookId);

        return view;
    }

This requires you to import 
`com.liferay.docs.getentriesscreenlet.GetEntriesScreenlet`. 

Awesome! Run the app in the emulator and log in with your credentials. The app 
presents you with the first guestbook's entries. Open the navigation drawer by 
pressing the hamburger button, and then select a different guestbook. The drawer 
then closes to show the selected guestbook's entries. Nice work! Your app now 
uses the Get Guestbooks and Get Entries Screenlets to show the same guestbooks 
and entries as the Guestbook portlet. The following screenshots show both 
Screenlets in action. 

![Figure 1: Get Entries Screenlet displays guestbook entries in your app.](../../images/android-guestbooks-entries-screenlets.png)

![Figure 2: Get Guestbooks Screenlet displays guestbooks in the navigation drawer.](../../images/android-guestbook-screenlet-drawer-01.png)

Although your Screenlets work, you may have noticed something odd about the 
navigation drawer: its header displays the text *Android Studio* and 
*android.studio@android.com*. You obviously don't want your users to see this. 
The two `TextView` elements in `nav_header_guestbooks.xml` use the 
`android:text` attribute to hardcode this text. To remove the text completely, 
you could delete the `TextView` elements. It's better though to show text that's 
relevant to your app. In the `TextView` elements, replace `"Android Studio"` 
with `"Liferay Guestbook"`, and `"android.studio@android.com"` with 
`"Welcome!"`. Run the app again, and open the drawer after signing in. The 
drawer header now shows your greeting. 

![Figure 3: The drawer header now displays your greeting.](../../images/android-guestbook-screenlet-drawer-02.png)

Congratulations! Now you know how to use Liferay Screens and create your own 
Screenlets. This opens up a world of possibilities for developing your own apps. 
Although you learned a great deal in this Learning Path, there's still more. You 
can customize your Screenlet's appearance, package it for redistribution, and 
even configure it to receive push notifications. These topics, and more, are 
covered in 
[the tutorials on Android apps with Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/android-apps-with-liferay-screens). 

Even though creating your own Screenlets is best practice for developing Android 
apps that work with your custom portlets, it's not mandatory. You could instead 
call the Mobile SDK directly in your app's activity or fragment code, and skip 
writing all the Screenlet components. The appendix that follows shows you how to 
do this. 
