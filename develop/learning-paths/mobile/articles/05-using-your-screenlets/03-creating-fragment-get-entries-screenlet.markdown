# Creating a Fragment for Get Entries Screenlet

Using a fragment for Get Entries Screenlet lets you swap out part of 
`GuestbookActivity`'s contents without recreating the entire activity from 
scratch each time a guestbook is selected. Because Get Entries Screenlet 
requires a guestbook ID, this fragment must also contain a variable for a 
guestbook ID. Now you'll create this fragment. Right click the 
`com.liferay.docs.liferayguestbook` package and select *New* &rarr; *Fragment* 
&rarr; *Fragment (Blank)*. In the wizard, check only the box for *Create layout 
XML?*, name the fragment `EntriesFragment`, and then click *Finish*. The 
following screenshot illustrates this: 

![Figure 1: Create a new blank fragment for the entries.](../../images/android-create-fragment.png)

This creates the `EntriesFragment` class and its layout file 
`fragment_entries.xml`. Replace the class's contents with the following code: 

    package com.liferay.docs.liferayguestbook;

    import android.os.Bundle;
    import android.support.v4.app.Fragment;

    public class EntriesFragment extends Fragment {

        private long _guestbookId;

        public EntriesFragment() {
            // Required empty public constructor
        }

        public static EntriesFragment newInstance(long guestbookId) {
            EntriesFragment entriesFragment = new EntriesFragment();
            Bundle args = new Bundle();
            args.putLong("guestbookId", guestbookId);
            entriesFragment.setArguments(args);

            return entriesFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            _guestbookId = getArguments().getLong("guestbookId");
        }
    }

If you have experience with Android fragments, then you're probably familiar 
with the empty constructor and `newInstance` method. When the screen orientation 
changes or the user switches apps, Android must restore the fragment. Instead of 
recreating the fragment from scratch, the `newInstance` method lets Android 
restore it with the proper `guestbookId`. The `onCreate` method sets the 
fragment's `_guestbookId` by using the bundle arguments set in `newInstance`. 
See 
[this blog post](http://www.androiddesignpatterns.com/2012/05/using-newinstance-to-instantiate.html) 
for more information on using a `newInstance` method to manage fragments. 

Fantastic! Now you have somewhere to put Get Entries Screenlet. You'll do this 
next. 
