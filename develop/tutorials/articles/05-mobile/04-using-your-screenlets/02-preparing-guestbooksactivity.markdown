# Preparing GuestbooksActivity for Guestbook List Screenlet [](id=preparing-guestbooksactivity-for-guestbook-list-screenlet)

Recall that you want `GuestbooksActivity` to display Guestbook List Screenlet 
and Entry List Screenlet. Before using these Screenlets, however, you must 
prepare `GuestbooksActivity` as follows: 

1. Refactor the action bar so you can later set its title to the selected 
   guestbook's name. 

2. Refactor the navigation drawer so you can later close it when a guestbook is 
   selected in Guestbook List Screenlet. 

3. Delete the `NavigationView.OnNavigationItemSelectedListener` implementation. 
   Since Guestbook List Screenlet handles guestbook selections, you don't need 
   `NavigationView`. 

When you finish, you'll be ready to use Guestbook List Screenlet. Note that you 
won't always have to take steps like these before using Screenlets. You only do 
so here to fit this particular app's design. 

First, you'll refactor the action bar. 

## Refactoring the Action Bar [](id=refactoring-the-action-bar)

By default, the action bar displays the activity's name. When you use Guestbook 
List Screenlet, you want the action bar to display the selected guestbook's name 
instead. You'll enable this by modifying the code that creates the action bar. 
Android Studio created this code for you in the `GuestbooksActivity` class's 
`onCreate` method: 

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

1.  Remove this code. Although you could edit it, you'll instead create a
    separate method that creates the action bar. Note that you don't need to
    worry about the now missing `toolbar` variable in `onCreate`; you'll fix it
    shortly. 

2.  Create `ActionBar` and `Toolbar` instance variables. This lets you refer to
    them anywhere in the activity. Add these variables to the
    `GuestbooksActivity` class: 

        private ActionBar actionBar;
        private Toolbar toolbar;

    This requires that you import `android.support.v7.app.ActionBar`. 

3.  Add the following `initActionBar()` method to `GuestbooksActivity`: 

        private void initActionBar() {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            actionBar = getSupportActionBar();
            actionBar.setTitle("");
        }

    Like the code you removed from `onCreate`, this method also creates a 
    `Toolbar` and sets it as the action bar. It also sets the action bar's title 
    to an empty string. This prevents the activity's title from showing in the 
    action bar before the app can retrieve guestbooks from the portlet. 

4.  Call `initActionBar()` in `onCreate`. Place the call immediately below the 
    `setContentView` call. The first few lines of `onCreate` should now look 
    like this: 

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guestbooks);

            initActionBar();

            ...
        }

Next, you'll modify the code that controls the navigation drawer. 

## Refactoring the Navigation Drawer [](id=refactoring-the-navigation-drawer)

Before you can use Guestbook List Screenlet in the navigation drawer, you must 
refactor the drawer's existing code. Do so now by following these steps: 

1.  Currently, the navigation drawer initialization code is in the `onCreate`
    method. Android Studio created this code for you when you used the
    Navigation Drawer Activity template to create `GuestbooksActivity`. Delete
    this code from `onCreate`: 

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    Instead, you'll initialize the navigation drawer in a separate method that 
    you'll call in `onCreate`. You'll create this method shortly. 

2.  You'll also change the `drawer` variable to be an instance variable that you 
    can refer to throughout the class. This lets you use this variable to close 
    the drawer when a guestbook is selected in Guestbook List Screenlet. Add 
    this variable to `GuestbooksActivity`: 

        private DrawerLayout drawer;

3.  Add the following `initDrawer` method. This method's contents match the
    drawer initialization code you deleted in `onCreate`, except that `drawer`
    is now an instance variable: 

        private void initDrawer() {
            // drawer initialization
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, 
                    R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        }

4.  In the `onCreate` method, place the call to `initDrawer()` immediately below
    the `initActionBar` call. The first few lines of `onCreate` should now look
    like this: 

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guestbooks);

            initActionBar();
            initDrawer();

            ...
        }

5.  Also, because you want to use the same `DrawerLayout` instance throughout
    the class, delete the line of code that creates a new `DrawerLayout` in the
    `onBackPressed` method. Your `onBackPressed` method should now look like
    this: 

        @Override
        public void onBackPressed() {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

Now you're ready to delete the `NavigationView.OnNavigationItemSelectedListener` 
implementation. The next section walks you through this. 

## Deleting the NavigationView.OnNavigationItemSelectedListener Implementation [](id=deleting-the-navigationview-onnavigationitemselectedlistener-implementation)

Since Guestbook List Screenlet handles navigation drawer item selections, you 
don't need to implement `NavigationView.OnNavigationItemSelectedListener` in 
`GuestbooksActivity`. Follow these steps to remove this implementation: 

1.  Delete the implementation from the class declaration. The class declaration
    should now look like this: 

        public class GuestbooksActivity extends AppCompatActivity {...

2.  Remove the code in `GuestbooksActivity` that implements
    `NavigationView.OnNavigationItemSelectedListener`. To do this, first delete
    the following code at the end of the `onCreate` method: 

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    Your `onCreate` method should now look like this: 

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guestbooks);

            initActionBar();
            initDrawer();

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

3.  Delete the `onNavigationItemSelected` method, along with its `@Override` and
    `@SuppressWarnings("StatementWithEmptyBody")` statements. 

4.  Finally, remove the `android.support.design.widget.NavigationView` import. 

Great job! Now you're ready to insert Guestbook List Screenlet in
`GuestbooksActivity`. 
