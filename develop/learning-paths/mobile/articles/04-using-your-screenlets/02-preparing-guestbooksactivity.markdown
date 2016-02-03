# Preparing GuestbooksActivity for Get Guestbooks Screenlet [](id=preparing-guestbooksactivity-for-get-guestbooks-screenlet)

Recall that you want `GuestbooksActivity` to handle the display of both Get 
Guestbooks Screenlet and Get Entries Screenlet. Before using these Screenlets, 
however, you must do the following prep work in `GuestbooksActivity`:

1. Refactor the Action Bar's code so you can later set the Action Bar's title to 
   the selected guestbook's name. 

2. Refactor the existing navigation drawer code so that you can later close the 
   drawer when a guestbook is selected in the Get Guestbooks Screenlet. 

3. Delete the `NavigationView.OnNavigationItemSelectedListener` implementation. 
   Since Get Guestbooks Screenlet handles guestbook selections, you don't need 
   to use `NavigationView`. 

When you finish, you'll be ready to use Get Guestbooks Screenlet. Note that you 
won't always have to take steps like these before using Screenlets. You do so 
here just to fit this particular app's design. 

First, you'll refactor the Action Bar's code. 

## Refactoring the Action Bar [](id=refactoring-the-action-bar)

By default, the Action Bar displays the activity's name. When you use the Get 
Guestbooks Screenlet, you want the Action Bar to display the selected 
guestbook's name instead. You'll enable this by modifying the code that creates 
the activity's Action Bar. Android Studio created this code for you in the 
`GuestbooksActivity` class's `onCreate` method: 

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

First, remove these two lines. Although you could modify this code here, you'll 
instead do so in a separate method. 

Next, you need the `ActionBar` and `Toolbar` to be instance variables that you 
can refer to anywhere in the activity. Add these variables to the 
`GuestbooksActivity` class now: 

    private ActionBar actionBar;
    private Toolbar toolbar;

This requires that you import `android.support.v7.app.ActionBar`.

Now add the following `initActionBar()` method to `GuestbooksActivity`: 

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
below the `setContentView` call. The first few lines of the `onCreate` method
should now look like this: 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbooks);
        
        initActionBar();

        ...
    }

Next, you'll modify the code that controls the navigation drawer. 

## Refactoring the Navigation Drawer [](id=refactoring-the-navigation-drawer)

Currently, the navigation drawer initialization code is in the `onCreate` 
method. Android Studio created this code for you when you used the Navigation 
Drawer Activity template to create `GuestbooksActivity`. Delete this code from 
`onCreate`:

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

Instead, you'll initialize the navigation drawer in a separate method that 
you'll then call in `onCreate`. You'll also change the `drawer` variable to be 
an instance variable that you can refer to throughout the class. This lets you 
use this variable to close the drawer when a guestbook is selected in Get 
Guestbooks Screenlet. First, add this variable to `GuestbooksActivity`: 

    private DrawerLayout drawer;

Next, add the following `initDrawer` method:

    private void initDrawer() {
        // drawer initialization
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, 
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

This method's contents match the drawer initialization code you deleted in 
`onCreate`, except that `drawer` is now an instance variable. 

In the `onCreate` method, place the call to `initDrawer()` immediately below the 
`initActionBar` call. The first few lines of the `onCreate` method should now 
look like this: 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbooks);
        
        initActionBar();
        initDrawer();

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

Next, you'll delete the `NavigationView.OnNavigationItemSelectedListener` 
implementation. 

## Deleting the NavigationView.OnNavigationItemSelectedListener Implementation [](id=deleting-the-navigationview-onnavigationitemselectedlistener-implementation)

Since you'll handle navigation drawer item selections with the Get Guestbooks 
Screenlet, you don't need `GuestbooksActivity` to implement 
`NavigationView.OnNavigationItemSelectedListener`. Delete this implementation 
from the class declaration. The class declaration should now look like this: 

    public class GuestbooksActivity extends AppCompatActivity {...

Now you must remove the code in `GuestbooksActivity` that implements 
`NavigationView.OnNavigationItemSelectedListener`. To do this, first delete the 
following code at the end of the `onCreate` method:

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

The `onCreate` method should now look like this:

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

Next, delete the `onNavigationItemSelected` method, along with its `@Override` 
and `@SuppressWarnings("StatementWithEmptyBody")` statements. Lastly, remove the 
`android.support.design.widget.NavigationView` import from `GuestbooksActivity`.

Great job! Now you're ready to insert Get Guestbooks Screenlet in 
`GuestbooksActivity`. 
