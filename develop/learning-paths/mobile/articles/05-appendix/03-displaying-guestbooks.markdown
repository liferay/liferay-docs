# Displaying Guestbooks [](id=displaying-guestbooks)

Recall that the `GuestbooksActivity` you created earlier contains a navigation 
drawer that slides out from the left side of the screen. Now that you've made 
the server call, you're ready to display the guestbooks in the navigation 
drawer. Currently, the drawer contains some simple placeholder content. The 
following screenshot shows this. 

![Figure 1: The navigation drawer currently contains placeholder content that Android Studio generated for you when you created the activity.](../../images/android-guestbook-initial-drawer.png)

When you used Get Guestbooks Screenlet, you replaced this content with the 
Screenlet. This was possible because Screenlets have their own UIs. Now, though, 
you're making the Mobile SDK call by itself. You must therefore create the UI 
independent of any Screenlet. You'll do this in `GuestbooksActivity` and its 
layout file `activity_guestbooks.xml`.

You'll replace the drawer's placeholder content with the list of guestbooks your 
app retrieves from the portal. You'll also make sure that the drawer closes when 
a guestbook is selected in it, and that the activity's Action Bar displays that 
guestbook's name. Also, the first guestbook should be selected automatically 
when the activity first launches. You'll implement this functionality by using 
the following steps:

1. Refactor the Action Bar. This step is identical to when you 
   [refactored the Action Bar before using Get Guestbooks Screenlet](/develop/learning-paths/mobile/-/knowledge_base/6-2/preparing-guestbooksactivity-for-get-guestbooks-screenlet#refactoring-the-action-bar).

2. Put guestbooks in the navigation drawer.

3. Enable guestbook selection in the navigation drawer.

4. Customize the navigation drawer.

Once you've refactored the Action Bar according to the instructions linked to in 
the first step, return here to complete the remaining steps. 

## Putting Guestbooks in the Navigation Drawer [](id=putting-guestbooks-in-the-navigation-drawer)

Recall that when you created Get Guestbooks Screenlet's UI, 
[you created its View class to extend `ListView`](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-the-get-guestbook-screenlets-ui#creating-the-view-class). 
This gave the View class an implicit 
[`ListView`](http://developer.android.com/reference/android/widget/ListView.html) 
object that you used to display the guestbooks in a list. Since you're now using 
the Mobile SDK by itself, you must manually insert a `ListView` in the 
navigation drawer. In `activity_guestbooks.xml`, replace the existing 
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
don't need to use `app:menu`. Delete the now unused menu resource file 
`res/menu/activity_guestbooks_drawer.xml`. 

Now that you've defined the drawer's layout, you must write the code that 
controls the drawer. You'll do this in `GuestbooksActivity`. As you'll see, the 
code you'll write here is similar to 
[the navigation drawer code you refactored](/develop/learning-paths/mobile/-/knowledge_base/6-2/preparing-guestbooksactivity-for-get-guestbooks-screenlet#refactoring-the-navigation-drawer) 
when you prepared `GuestbooksActivity` for Get Guestbooks Screenlet. You still 
need an instance variable for the `DrawerLayout`, but you also need instance 
variables for the `ListView` and its 
[adapter](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews). 
This is because the activity must handle the `ListView` in the absence of a 
Screenlet that does the same. 

First, add the following variables to `GuestbooksActivity`: 

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
`initDrawer` initializes the drawer's `ListView` and creates an adapter for it. 
Note that `_guestbooks` is the last argument to the adapter's constructor. This 
sets the list of guestbooks as the adapter's data source. The `setAdapter` 
method then sets the adapter to the `ListView`. 

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

To enable guestbook selection in the drawer, `GuestbooksActivity` must implement 
`AdapterView.OnItemClickListener`. Recall that Get Guestbooks Screenlet handled 
guestbook selection the same way, 
[but did so in its View class](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-the-get-guestbook-screenlets-ui#creating-the-view-class). 
Currently, `GuestbooksActivity` implements 
`NavigationView.OnNavigationItemSelectedListener`. You must first remove this 
implementation. Recall that you also removed this implementation when 
[preparing `GuestbooksActivity` for Get Guestbooks Screenlet](/develop/learning-paths/mobile/-/knowledge_base/6-2/preparing-guestbooksactivity-for-get-guestbooks-screenlet#deleting-the-navigationview-onnavigationitemselectedlistener-implementation). 

First, change the `GuestbooksActivity` class to implement 
`AdapterView.OnItemClickListener` instead of 
`NavigationView.OnNavigationItemSelectedListener`. The class declaration should 
now look like this: 

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
the drawer. Now you'll set the activity to select the first guestbook by 
default. Recall that the callback class calls `reloadGuestbooks` when the 
service call succeeds. The existing code in this method replaces the current 
guestbook list with those retrieved from the portal. You can also use 
`reloadGuestbooks` to select the first guestbook in the list automatically. Add 
the following code to the end of the `reloadGuestbooks` method: 

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

Before basking in the glory of your accomplishment, you need to customize the 
navigation drawer's appearance. Open the layout file 
`nav_header_guestbooks.xml`. The parent `LinearLayout`'s height is `160dp`. This 
value corresponds to `@dimen/nav_header_height`. To prevent the drawer's header 
from overlapping with the guestbook list, you must pad the top of the drawer's 
`ListView` by this value. Add the following code to the `ListView` in 
`activity_guestbooks.xml`, below the `android:layout_height` setting:

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
