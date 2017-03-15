# Using Entry List Screenlet [](id=using-entry-list-screenlet)

You'll use Entry List Screenlet by following the same basic steps to use any 
Screenlet: insert the Screenlet's XML in an activity or fragment layout, and 
then implement the Screenlet's listener in the activity or fragment class. In 
this article, you'll follow these steps to insert Entry List Screenlet in 
`EntriesFragment`. 

First, you'll insert Entry List Screenlet's XML in `EntriesFragment`'s layout, 
`fragment_entries.xml`. 

## Inserting the Screenlet in the Layout [](id=inserting-the-screenlet-in-the-layout)

Inserting Entry List Screenlet's XML is very simple. Since all you want 
`fragment_entries.xml` to do is display the Screenlet, all it needs to contain 
is the Screenlet's XML. Replace the contents of `fragment_entries.xml` with the 
following: 

    <com.liferay.docs.entrylistscreenlet.EntryListScreenlet
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:liferay="http://schemas.android.com/apk/res-auto"
        android:id="@+id/entrylist_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        liferay:layoutId="@layout/list_entries"/>

As with Guestbook List Screenlet, you'll use the `android:id` value to get a 
Screenlet reference. Next, you must implement the Screenlet's listener. 

## Implementing the Screenlet's Listener [](id=implementing-the-screenlets-listener)

Recall that to use a Screenlet, you must implement its listener. The listener 
methods let the app developer respond to the Screenlet's behavior in the 
activity or fragment class that contains the Screenlet. Also recall that because 
Guestbook List Screenlet didn't need extra listener methods, you used it in 
`GuestbooksActivity` by implementing the 
[`BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/2.1.0/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java) 
with `GuestbookModel` as a type argument. Entry List Screenlet also doesn't need 
extra listener methods. Like Guestbook List Screenlet, you can use it by 
implementing `BaseListListener` with its model class as a type argument. 

Change `EntriesFragment`'s class declaration to implement 
`BaseListListener<EntryModel>`. The class declaration should now look like this: 

    public class EntriesFragment extends Fragment implements BaseListListener<EntryModel> {...

This requires that you add the following imports:

    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.screens.base.list.BaseListListener;

Now you must implement the listener's methods. Recall that this includes the 
[`BaseCacheListener` interface's](https://github.com/liferay/liferay-screens/blob/2.1.0/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java), 
`error` method, since `BaseListListener` extends `BaseCacheListener`. For a full 
explanation of the methods in both listeners, see 
[the article on using Guestbook List Screenlet](/develop/tutorials/-/knowledge_base/7-0/using-guestbook-list-screenlet). 
Note that in `EntriesFragment`, you don't need to take any action in these 
methods. There are no UI elements or other parts of the fragment that must be 
updated or processed in response to the Screenlet's behavior. All this Screenlet 
needs to do is display its content, which it does regardless of anything you do 
in the listener methods. The only thing you may want to add is a toast message 
in `onListPageFailed` to notify the user if the server call fails, but this 
isn't required. Implement these methods now: 

    @Override
    public void onListPageFailed(int startRow, Exception e) {
        Toast.makeText(getActivity(), "Page request failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListPageReceived(int startRow, int endRow, List<EntryModel> entries, int rowCount) {

    }

    @Override
    public void onListItemSelected(EntryModel entry, View view) {

    }

    @Override
    public void error(Exception e, String userAction) {

    }

This requires you to add the following imports:

    import android.widget.Toast;
    import java.util.List;

Now you're ready to register `EntriesFragment` as the Screenlet's listener. 
You'll do this the same way you registered `GuestbooksActivity` as Guestbook 
List Screenlet's listener: get a reference to the Screenlet and call its 
`setListener` method. After doing this, you'll use the Entry List Screenlet 
reference's `setGuestbookId` method to set its guestbook ID. This sets the 
guestbook the Screenlet retrieves entries from. You'll do these things in the 
`onCreateView` method. Replace the `onCreateView` method with this updated 
version: 

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entries, container, false);
        long guestbookId = getArguments().getLong("guestbookId");

        EntryListScreenlet screenlet = (EntryListScreenlet) view.findViewById(R.id.entrylist_screenlet);
        screenlet.setListener(this);
        screenlet.setGuestbookId(guestbookId);

        return view;
    }

This requires you to import 
`com.liferay.docs.entrylistscreenlet.EntryListScreenlet`. 

As you can see, `onCreateView` now registers `EntriesFragment` as the 
Screenlet's listener and sets the Screenlet's guestbook ID. The rest of 
`onCreateView` is unchanged. 

Now run the app in the emulator and log in with your credentials when prompted. 
The app then presents you with the first guestbook's entries. Open the 
navigation drawer by pressing the hamburger button, then select a different 
guestbook. The drawer then closes to show the selected guestbook's entries. Nice 
work! Your app now uses Guestbook List Screenlet and Entry List Screenlet to 
show the same guestbooks and entries as the Guestbook portlet. The following 
screenshots show these Screenlets in action. 

![Figure 1: Entry List Screenlet displays guestbook entries in your app.](../../../images/android-guestbooks-entries-screenlets.png)

![Figure 2: Guestbook List Screenlet displays guestbooks in the navigation drawer.](../../../images/android-guestbook-screenlet-drawer-01.png)

Although your Screenlets work, you may have noticed something odd about the 
navigation drawer; its header displays the text *Android Studio* and 
*android.studio@android.com*. You obviously don't want your users to see this. 
The two `TextView` elements in `nav_header_guestbooks.xml` use the 
`android:text` attribute to hardcode this text. To remove the text completely, 
you could delete these `TextView` elements. It's better though to show text 
relevant to your app. In the `TextView` elements, replace `"Android Studio"` 
with `"Liferay Guestbook"`, and `"android.studio@android.com"` with 
`"Welcome!"`. Run the app again, and open the drawer after signing in. The 
drawer header now shows your greeting. 

![Figure 3: The drawer displays your greeting after you change the text in `nav_header_guestbooks.xml`.](../../../images/android-guestbook-screenlet-drawer-02.png)

Congratulations! Now you know how to use Liferay Screens and create your own 
Screenlets. This opens up a world of possibilities for developing apps that 
leverage @product@. Although you learned a great deal in this Learning Path, 
there's still more. You can customize your Screenlet's appearance, package it 
for redistribution, and even configure it to receive push notifications. These 
topics, and more, are covered in the tutorials on Android apps with Liferay 
Screens. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/android-apps-with-liferay-screens) 
to see these tutorials. 
