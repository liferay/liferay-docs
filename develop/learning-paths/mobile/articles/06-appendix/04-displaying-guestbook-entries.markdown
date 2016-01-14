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

First, you must create a variable for the adapter. Add it to `EntriesFragment` 
as follows: 

    private ArrayAdapter _adapter;

Now you need to create the adapter. Do so now by placing the following code in 
the `onActivityCreated` method, above the `getEntries` call:

    _adapter = new ArrayAdapter<EntryModel>(
        getActivity(), 
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

This adapter has significantly more code than the guestbooks adapter. This is 
because the entries adapter uses the default layout `simple_list_item_2`. Recall 
that the guestbooks adapter uses the default layout `simple_list_item_1`. This 
layout is sufficient for showing guestbook names because it displays only a 
single string for each list item. Each entry, however, should display two 
strings: the entry's message and the name of the person who left it. The 
`simple_list_item_2` layout lets you display these strings, because it can show 
two strings for each list item. You need to tell it what two strings to display, 
though. This is why you override the `getView` method. After using 
`findViewById` to retrieve the `simple_list_item_2` layout's two strings, you 
use `setText` to set the strings' content to an entry's message and name. You 
then use the `setTypeface` and `setTextSize` methods, respectively, to format 
the entry's message in bold and shrink the name. You finish by using 
`setListAdapter` to set the adapter to `EntriesFragment`'s implicit `ListView`. 

Like the guestbooks adapter, you also need to refresh the entries adapter's 
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
add the fragment to `GuestbooksActivity`. First, you need to put a fragment 
container in the layout where you want the fragment to appear. In short, a
fragment container is a layout used to hold a fragment. For more information,
see [Android's documentation on adding fragments at runtime](http://developer.android.com/training/basics/fragments/fragment-ui.html#AddAtRuntime). 
Since you want the entries to appear in `GuestbooksActivity`, your first thought 
might be to put the fragment container directly in `activity_guestbooks.xml`. 
Don't do this. Recall that the Navigation Drawer Activity template in Android 
Studio created the layout `content_guestbooks.xml` to hold the activity's main 
body content. Open `content_guestbooks.xml` and place the following fragment 
container inside the `RelativeLayout`: 

    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

Next, you need to write the code that handles `EntriesFragment` in 
`GuestbooksActivity`. Specifically, you need to display the fragment when a 
guestbook is selected. Recall that the `onItemClick` method in 
`GuestbooksActivity` selects a guestbook. Add the following code to this method, 
immediately above `drawer.closeDrawers()`: 

    EntriesFragment entriesFragment = EntriesFragment.newInstance(selectedGuestbook.getGuestbookId());
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container, entriesFragment);
    transaction.commit();

You use `newInstance` to create a new `EntriesFragment` instance that contains 
the selected guestbook's ID. A 
[fragment transaction](http://developer.android.com/guide/components/fragments.html#Transactions) 
then adds this fragment to the fragment container.

That's it! Make sure your portal is running and then run the app. The app now 
displays the selected guestbook's entries. 

![Figure 3: The entries for the selected guestbook now appear in your app.](../../images/android-guestbook-entries.png)

Congratulations! You've successfully built the Guestbook Mobile SDK and used it 
in conjunction with Liferay Screens to develop an Android app that displays the 
Guestbook portlet's entities. But there's another way, a better way, to develop 
the same functionality: you can use Liferay Screens to create your own 
Screenlets. The following sections in this Learning Path show you how to do 
this. 
