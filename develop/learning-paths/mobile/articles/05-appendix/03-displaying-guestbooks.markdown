# Displaying Guestbooks [](id=displaying-guestbooks)

Recall that the `GuestbooksActivity` you created earlier contains a navigation 
drawer that slides out from the left side of the screen. Now that you've made 
the server call, you're ready to display the guestbooks in the navigation 
drawer. Currently, the drawer contains some simple placeholder content. The 
following screenshot shows this.

![Figure 1: The placeholders currently in the navigation drawer.](../../images/android-guestbook-initial-drawer.png)

You'll replace this content with the list of guestbooks your app retrieves from 
the portal. You'll also make sure that the drawer closes when a guestbook is 
selected in it, and that the activity's Action Bar displays that guestbook's 
name. Also, the first guestbook should be selected automatically when the 
activity first launches. You'll implement this functionality by using the 
following steps:

1. Initialize the Action Bar.

2. Put guestbooks in the navigation drawer.

3. Enable guestbook selection in the navigation drawer.

4. Customize the navigation drawer.

This is conceptually simple, but a bit more complex in practice. Not to worry! 
You'll be guided though each step in the process. 

You'll begin by initializing the Action Bar. 

## Initializing the Action Bar [](id=initializing-the-action-bar)

By default, the Action Bar displays the activity's name. You'll change it to 
display the selected guestbook's name by modifying the code that creates the 
activity's Action Bar. Android Studio created this code for you in the 
`GuestbooksActivity` class's `onCreate` method: 

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

First, remove these two lines. You need the `ActionBar` and `Toolbar` to be
instance variables that you can refer to anywhere in the activity. Add these
variables to the `GuestbooksActivity` class now: 

    private ActionBar actionBar;
    private Toolbar toolbar;

Although you can modify the Action Bar initialization code in `onCreate`, you'll 
write it here in a separate method. Add the following `initActionBar()` method 
to `GuestbooksActivity`: 

    private void initActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

Like the code in `onCreate`, this method also creates a `Toolbar` and sets it as 
the Action Bar. This code also sets the Action Bar's title to an empty string. 
This prevents the activity's title from showing in the Action Bar before the app
can retrieve guestbooks from the portal. 

Now you need to call `initActionBar()` in `onCreate`. Place the call immediately
above the `getGuestbooks()` call. The first few lines of the `onCreate` method
should now look like this: 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbooks);
        
        initActionBar();
        getGuestbooks();

        ...
    }

Now you're ready to write the code that puts the guestbooks in the navigation 
drawer.

## Putting Guestbooks in the Navigation Drawer [](id=putting-guestbooks-in-the-navigation-drawer)

To put the guestbooks in the navigation drawer, you must first put a 
[`ListView`](http://developer.android.com/guide/topics/ui/layout/listview.html) 
in the drawer's layout. You'll use this `ListView` to display the guestbooks. In 
`activity_guestbooks.xml`, replace the existing 
[`NavigationView`](https://developer.android.com/reference/android/support/design/widget/NavigationView.html) 
with this one: 

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_guestbooks">

        <ListView
            android:id="@+id/drawer_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:choiceMode="singleChoice" />

    </android.support.design.widget.NavigationView>

When compared to the `NavigationView` it replaced, this new `NavigationView` 
contains the `ListView`, and lacks the `app:menu` attribute. This attribute 
points to the menu resource file used to create the drawer's items. Since you'll 
instead use the `ListView` to display the drawer's items (the guestbooks), you 
don't need to use `app:menu`. 

Now that you've defined the drawer's layout, you need to write the code that 
controls the drawer. You'll do this in `GuestbooksActivity`. First, add the 
following variables to this class: 

    private DrawerLayout drawer;
    private ListView drawerListView;
    private ArrayAdapter _adapter;

As with the Action Bar, you'll initialize the drawer in its own method. Add 
this method now: 

    private void initDrawer() {
        // drawer initialization
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, 
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        
        // ListView initialization
        drawerListView = (ListView) findViewById(R.id.drawer_list);
        _adapter = new ArrayAdapter<GuestbookModel>(
                this,
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                _guestbooks);
        drawerListView.setAdapter(_adapter);
    }

The `initDrawer` method's first few lines match the drawer initialization code 
in `onCreate`. You should therefore delete this code in `onCreate`. You don't 
need it there since you call it in `initDrawer` instead. The last part of 
`initDrawer` initializes the drawer's `ListView` and creates an 
[adapter](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
for it. Note that `_guestbooks` is the last argument to the adapter's 
constructor. This sets the list of guestbooks as the adapter's data source. The 
`setAdapter` method then sets the adapter to the `ListView`. 

In the `onCreate` method, place the call to `initDrawer()` immediately below the 
`initActionBar` call. The first few lines of the `onCreate` method should now 
look like this: 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbooks);
        
        initActionBar();
        initDrawer()
        getGuestbooks();

        ...
    }

Also, because you want to use the same `DrawerLayout` instance throughout the 
class, delete the line of code that creates a new `DrawerLayout` in the 
`onBackPressed` method. The `onBackPressed` method should now look like this: 

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

Great! Your app can now display guestbooks in the drawer. Now you're ready to 
set what happens when a guestbook is selected in the drawer. 

## Enabling Guestbook Selection in the Navigation Drawer [](id=enabling-guestbook-selection-in-the-navigation-drawer)

To enable guestbook selection in the drawer, first change the 
`GuestbooksActivity` class to implement `AdapterView.OnItemClickListener` 
instead of `NavigationView.OnNavigationItemSelectedListener`. The class 
declaration should now look like this: 

    public class GuestbooksActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {...

Now you need to delete the `NavigationView.OnNavigationItemSelectedListener` 
code in `GuestbooksActivity`'s body. To do this, delete the 
`onNavigationItemSelected` method, and the `NavigationView` code at the end of 
the `onCreate` method. Next, implement `AdapterView.OnItemClickListener` by 
adding the following `onItemClick` method: 

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, 
        final int position, final long id) {
        
        drawerListView.setItemChecked(position, true);
        GuestbookModel selectedGuestbook = _guestbooks.get(position);
        actionBar.setTitle(selectedGuestbook.getName());
        drawer.closeDrawers();
    }

Android calls `onItemClick` when a guestbook is selected in the drawer. This 
method first marks the selected item in the UI. It then retrieves the selected 
guestbook and displays that guestbook's name in the Action Bar. The method 
finishes by closing the drawer. 

To enable `onItemClick`, you need to set the drawer's `ListView` to listen for 
item clicks. Do this now by adding the following code to the end of the 
`initDrawer` method: 

    drawerListView.setOnItemClickListener(this);

Great! Your app can now respond appropriately when a guestbook is selected in 
the drawer. When `GuestbooksActivity` first loads, however, the drawer is closed 
until the user opens it. Recall that in `initActionBar()` you set the Action Bar 
to display an empty string by default. The activity therefore opens with no 
content and an empty Action Bar. This isn't very user friendly. You'll change 
this so that the activity selects the first guestbook by default. Recall that 
the callback class calls `reloadGuestbooks` when the service call succeeds. The 
existing code in this method replaces the current guestbook list with those
retrieved from the portal. You can also use `reloadGuestbooks` to select the
first guestbook in the list automatically. Add the following code to the end of
the `reloadGuestbooks` method: 

    _adapter.notifyDataSetChanged();
    drawerListView.performItemClick(drawerListView, 0, drawerListView.getItemIdAtPosition(0));

This code notifies the `ListView`'s adapter of any changed data and then 
performs an item click on the first guestbook. This item click triggers the 
`onItemClick` method. 

Awesome! You're done with the `GuestbooksActivity` class! Before moving on, you 
should check your work. Here's what the `GuestbooksActivity` class should look 
like: 

    public class GuestbooksActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

        private List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();
        private ActionBar actionBar;
        private Toolbar toolbar;
        private DrawerLayout drawer;
        private ListView drawerListView;
        private ArrayAdapter _adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guestbooks);

            initActionBar();
            initDrawer();
            getGuestbooks();

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            });
        }

        public void reloadGuestbooks(List<GuestbookModel> guestbooks) {
            _guestbooks.clear();
            _guestbooks.addAll(guestbooks);
            _adapter.notifyDataSetChanged();

            drawerListView.performItemClick(drawerListView, 0, drawerListView.getItemIdAtPosition(0));
        }

        protected void getGuestbooks() {
            Session session = SessionContext.createSessionFromCurrentSession();
            GetGuestbooksCallback callback = new GetGuestbooksCallback(this);
            session.setCallback(callback);

            GuestbookService service = new GuestbookService(session);
            try {
                service.getGuestbooks(LiferayServerContext.getGroupId());
            }
            catch (Exception e) {
                String message = "Couldn't get guestbooks " + e.getMessage();
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

        private void initActionBar() {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            actionBar = getSupportActionBar();
            actionBar.setTitle("");
        }

        private void initDrawer() {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            drawerListView = (ListView) findViewById(R.id.drawer_list);
            _adapter = new ArrayAdapter<GuestbookModel>(
                this,
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                _guestbooks);
            drawerListView.setAdapter(_adapter);
            drawerListView.setOnItemClickListener(this);
        }

        @Override
        public void onBackPressed() {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.guestbooks, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
            drawerListView.setItemChecked(position, true);
            GuestbookModel selectedGuestbook = _guestbooks.get(position);
            actionBar.setTitle(selectedGuestbook.getName());
            drawer.closeDrawers();
        }
    }

You're almost ready to run the app. Next, you'll tie up some loose ends in the 
navigation drawer's UI. 

## Customizing the Navigation Drawer [](id=customizing-the-navigation-drawer)

Before basking in the glory of your accomplishment, you need to do some cleanup 
and customize the navigation drawer's appearance. Because you're no longer using 
the menu resource file that defined the drawer's placeholder items, you can 
delete it. Delete the file `res/menu/activity_guestbooks_drawer.xml`. 

There's one more thing you need to take care of before launching the app. Open 
the layout file `nav_header_guestbooks.xml`. The parent `LinearLayout`'s height 
is `160dp`. This value corresponds to `@dimen/nav_header_height`. To prevent the 
drawer's header from overlapping with the guestbook list, you must pad the top 
of the drawer's `ListView` by this value. Add the following code to the 
`ListView` in `activity_guestbooks.xml`, below the `android:layout_height` 
setting:

    android:paddingTop="@dimen/nav_header_height"

That's it! Run the app. After you log in, the app retrieves the guestbooks from 
the portal and displays the first guestbook's name in the Action Bar.

![Figure 2: The app now displays the first guestbook's name in the Action Bar.](../../images/android-first-guestbook.png)

Open the drawer to reveal the list of guestbooks. Select a different guestbook 
from the drawer. The drawer then closes and the Action Bar displays the selected 
guestbook's name. Awesome! It works! There's one small issue, however, with the 
drawer (besides being hideous). Its header displays the text *Android Studio* 
and *android.studio@android.com*. You obviously don't want your users to see 
this. 

![Figure 3: The header displays generic text by default.](../../images/android-guestbook-drawer-01.png)

The two `TextView` elements in `nav_header_guestbooks.xml` use the 
`android:text` attribute to hardcode this text. To remove the text completely, 
you could delete the `TextView` elements. It's better though to show text that's 
relevant to your app. In the `TextView` elements, replace `"Android Studio"` 
with `"Liferay Guestbook"`, and `"android.studio@android.com"` with 
`"Welcome!"`.

Run the app again, and open the drawer after signing in. Although the drawer is 
still ugly, it's at least polite now. 

![Figure 4: The navigation drawer now contains the app's name and a greeting.](../../images/android-guestbook-drawer-02.png)

Great! Now you're ready to retrieve guestbook entries. 
