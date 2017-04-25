# Creating a Fragment for Entry List Screenlet [](id=creating-a-fragment-for-entry-list-screenlet)

Using a fragment for Entry List Screenlet lets you swap out part of 
`GuestbookActivity`'s contents without recreating the entire activity from 
scratch each time a guestbook is selected. Your app doesn't currently have any 
fragments, though. In this article, you'll create a fragment and then add it to 
`GuestbooksActivity`. When you finish, you'll be ready to use Entry List 
Screenlet in this fragment. 

## Creating the Fragment [](id=creating-the-fragment)

To create the fragment, right click the `com.liferay.docs.liferayguestbook` 
package and select *New* &rarr; *Fragment* &rarr; *Fragment (Blank)*. In the 
wizard, check only the box to create the layout XML, name the fragment 
`EntriesFragment`, and then click *Finish*. The following screenshot illustrates 
this: 

![Figure 1: Create a new blank fragment for the entries.](../../../images/android-create-fragment.png)

This creates the `EntriesFragment` class and its layout file 
`fragment_entries.xml`. Replace the class's contents with this code: 

    package com.liferay.docs.liferayguestbook;

    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    public class EntriesFragment extends Fragment {

        public static EntriesFragment newInstance(long guestbookId) {
            EntriesFragment entriesFragment = new EntriesFragment();
            Bundle args = new Bundle();
            args.putLong("guestbookId", guestbookId);
            entriesFragment.setArguments(args);

            return entriesFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_entries, container, false);
            long guestbookId = getArguments().getLong("guestbookId");

            return view;
        }

    }

If you have experience with Android fragments, then you're likely familiar with 
the static `newInstance` method. In short, using such a method instead of an 
empty constructor lets you create the fragment and initialize its data in one 
step. This makes it easier to create and restore the fragment. 
[Click here](http://www.androiddesignpatterns.com/2012/05/using-newinstance-to-instantiate.html) 
for more information. 

Since this fragment will contain Entry List Screenlet, its data must include the 
ID of the guestbook the Screenlet retrieves entries from (`guestbookId`). Also, 
the `onCreateView` method uses the bundle arguments set in `newInstance` to 
retrieve the `guestbookId`. For now, you don't have to do anything with the 
`guestbookId` in `onCreateView`. You'll use this variable when you add the 
Screenlet to the fragment. 

Next, you'll add this fragment to `GuestbooksActivity`. 

## Adding the Fragment to GuestbooksActivity [](id=adding-the-fragment-to-guestbooksactivity)

Now that `EntriesFragment` exists, you can add it to `GuestbooksActivity`. To do 
this, you must put an Android fragment container in the layout you want the 
fragment to appear in. For more information, see 
[Android's documentation on adding fragments at runtime](http://developer.android.com/training/basics/fragments/fragment-ui.html#AddAtRuntime). 
Since you want Entry List Screenlet to appear in `GuestbooksActivity`, your 
first thought might be to put the fragment container directly in 
`activity_guestbooks.xml`. Don't do this. Recall that Android Studio's 
Navigation Drawer Activity template created the layout `content_guestbooks.xml` 
to hold the activity's main body content. Open `content_guestbooks.xml` and 
place the following fragment container inside the `ConstraintLayout`. This 
fragment container should be the only other element inside the 
`ConstraintLayout`: 

    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

Next, you must write the `GuestbooksActivity` code that displays the fragment 
when a guestbook is selected in Guestbook List Screenlet. You'll do this with an 
Android fragment transaction. 
[Click here](http://developer.android.com/guide/components/fragments.html#Transactions) 
to see Android's documentation on fragment transactions. Recall that you created 
`GuestbooksActivity`'s `showEntries` method to process a list item selection in 
Guestbook List Screenlet. All `showEntries` does right now is set the action 
bar's title to the selected guestbook's name. You'll add the fragment 
transaction to `showEntries`, so a guestbook selection also shows that 
guestbook's entries. Replace the `showEntries` method in `GuestbooksActivity` 
with this code: 

    public void showEntries(GuestbookModel guestbook) {
        actionBar.setTitle(guestbook.getName());

        EntriesFragment entriesFragment = EntriesFragment.newInstance(guestbook.getGuestbookId());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, entriesFragment);
        transaction.commit();
    }

This requires that you import `android.support.v4.app.FragmentTransaction`. 

This method's `actionBar.setTitle` call is the same as before. Only the fragment 
code is new. In it, you first use `newInstance` to create a new 
`EntriesFragment` instance with the selected guestbook's ID. A fragment 
transaction then adds this fragment to the fragment container. 

Fantastic! Now you have a fragment to put Entry List Screenlet in, and the code 
that displays this fragment in `GuestbooksActivity`. Next, you'll put Entry List 
Screenlet in the fragment. 
