# Displaying Guestbook Entries [](id=displaying-guestbook-entries)

To display entries, you need to perform two steps: 

1. Create an adapter for displaying the entries in `EntriesFragment`'s 
   `ListView`. Recall that because `EntriesFragment` extends `ListFragment`, it 
   contains an implicit `ListView` object. You'll use this `ListView` to display 
   the entries. You must, however, create an adapter to use with the `ListView`. 

2. Add `EntriesFragment` to `GuestbooksActivity`. You'll do this via a fragment 
   container and a fragment transaction.

You'll get started by creating the adapter. 

## Creating the Adapter [](id=creating-the-adapter)

First, you must create an instance variable for the adapter. Add it to 
`EntriesFragment`: 

    private ArrayAdapter _adapter;

This requires you to import `android.widget.ArrayAdapter`.

Now you need to create the adapter. Do so now by placing the following code in 
the `onActivityCreated` method, above the `getEntries` call:

    _adapter = new ArrayAdapter<EntryModel>(
        getContext(), 
        android.R.layout.simple_list_item_2,
        android.R.id.text1, 
        _entries) {
        
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(_entries.get(position).getMessage());
                text2.setText(_entries.get(position).getName());

                text1.setTypeface(Typeface.DEFAULT_BOLD);
                text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                return view;
            }
        };
 
    setListAdapter(_adapter);

This requires you to add the following imports:

    import android.util.TypedValue;
    import android.view.View;
    import android.view.ViewGroup;
    import android.graphics.Typeface;
    import android.widget.TextView;

This adapter is identical to 
[the one you used when creating Get Entries Screenlet's View class](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-get-entries-screenlets-ui#creating-the-view-class). 
Recall that it displays two lines of text: one for the entry, and one for the 
name of the person who left it. The only difference is that you set the adapter 
here with `setListAdapter` instead of `setAdapter`. Using `setListAdapter` is 
required to set adapters in fragments that extend 
[`ListFragment`](http://developer.android.com/reference/android/app/ListFragment.html). 

Like the guestbooks adapter, you must also refresh the entries adapter's 
contents following a successful server call. To do so, add the following code at 
the end of the `reloadEntries` method: 

    _adapter.notifyDataSetChanged();

Great! Now you're done with the `EntriesFragment` class. Before moving on, check 
to make sure it looks like this:

    public class EntriesFragment extends ListFragment {

        private long _guestbookId;
        private List<EntryModel> _entries = new ArrayList<EntryModel>();
        private ArrayAdapter _adapter;


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

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            _adapter = new ArrayAdapter<EntryModel>(getActivity(), android.R.layout.simple_list_item_2,
                    android.R.id.text1, _entries) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(_entries.get(position).getMessage());
                    text2.setText(_entries.get(position).getName());

                    text1.setTypeface(Typeface.DEFAULT_BOLD);
                    text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                    return view;
                }
            };
            setListAdapter(_adapter);

            getEntries(_guestbookId);
        }

        public void reloadEntries(List<EntryModel> entries) {
            _entries.clear();
            _entries.addAll(entries);

            _adapter.notifyDataSetChanged();
        }

        protected void getEntries(long guestbookId) {
            Session session = SessionContext.createSessionFromCurrentSession();
            GetEntriesCallback callback = new GetEntriesCallback(this);
            session.setCallback(callback);

            EntryService service = new EntryService(session);
            try {
                service.getEntries(LiferayServerContext.getGroupId(), guestbookId);
            }
            catch (Exception e) {
                String message = "Couldn't get entries " + e.getMessage();
                Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

Next, you'll display the entries by adding `EntriesFragment` to 
`GuestbooksActivity`. 

## Adding EntriesFragment to GuestbooksActivity [](id=adding-entriesfragment-to-guestbooksactivity)

Now that `EntriesFragment` can put entries in its `ListView`, you're ready to 
add the fragment to `GuestbooksActivity`. When you used the Screenlets, you did 
this prior to using Get Entries Screenlet. You'll use the same steps to add the 
fragment to `GuestbooksActivity`: 

- [Adding the Fragment to `GuestbooksActivity`](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-a-fragment-for-get-entries-screenlet#adding-the-fragment-to-guestbooksactivity)

The only difference is that 
`GuestbooksActivity` now uses the `onItemClick` method instead of the Screenlet 
listener method `onItemClicked`. You should therefore perform the fragment 
transaction in `onItemClick`, immediately above `drawer.closeDrawers()`. When 
you finish, your `onItemClick` method in `GuestbooksActivity` should look like 
this: 

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        drawerListView.setItemChecked(position, true);
        GuestbookModel guestbook = _guestbooks.get(position);
        actionBar.setTitle(guestbook.getName());

        EntriesFragment entriesFragment = EntriesFragment.newInstance(guestbook.getGuestbookId());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, entriesFragment);
        transaction.commit();

        drawer.closeDrawers();
    }

This requires you to import `android.support.v4.app.FragmentTransaction` in 
`GuestbooksActivity`. 

That's it! Make sure your portal is running and then run the app. The app now 
displays the selected guestbook's entries. 

![Figure 1: The entries for the selected guestbook now appear in your app.](../../../images/android-guestbook-entries.png)

Congratulations! You've successfully used the Guestbook Mobile SDK to develop an 
Android app that displays the Guestbook portlet's entities. Remember, however, 
that creating Screenlets is best practice when creating an Android app that 
interacts with your custom portlet. 
