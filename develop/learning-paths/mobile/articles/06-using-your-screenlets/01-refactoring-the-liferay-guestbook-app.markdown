# Refactoring the Liferay Guestbook App

Before you can use your shiny new Screenlets, you need to remove the code you 
wrote earlier with the Guestbook Mobile SDK. Fortunately, this is a 
straightforward task. 

First, open `GuestbooksActivity` and replace its contents with the following 
code:

    package com.liferay.docs.liferayguestbook;

    import android.os.Bundle;
    import android.support.design.widget.FloatingActionButton;
    import android.support.design.widget.Snackbar;
    import android.support.v7.app.ActionBar;
    import android.view.View;
    import android.support.v4.view.GravityCompat;
    import android.support.v4.widget.DrawerLayout;
    import android.support.v7.app.ActionBarDrawerToggle;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.view.Menu;
    import android.view.MenuItem;

    public class GuestbooksActivity extends AppCompatActivity {

        private ActionBar actionBar;
        private Toolbar toolbar;
        private DrawerLayout drawer;

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
    }

Next, open `EntriesFragment` and replace its contents with the following code:

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

Next, you need to delete the remaining Guestbook Mobile SDK code. Delete the 
`callback` and `model` packages inside the `liferayguestbook` package. In 
Android Studio's project view, your app should now look like this:

![Figure 1: After refactoring, your app's project should look like this.](../../images/android-guestbook-screenlet-refactor.png)

Sweet! Now you're ready for the fun part: using your Screenlets.
