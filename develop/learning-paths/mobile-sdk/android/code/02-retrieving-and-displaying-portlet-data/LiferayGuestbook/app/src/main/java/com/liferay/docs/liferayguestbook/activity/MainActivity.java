package com.liferay.docs.liferayguestbook.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.liferayguestbook.callback.GetEntriesCallback;
import com.liferay.docs.liferayguestbook.callback.GetGuestbooksCallback;
import com.liferay.docs.liferayguestbook.model.EntryModel;
import com.liferay.docs.liferayguestbook.model.GuestbookModel;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v62.entry.EntryService;
import com.liferay.mobile.android.v62.guestbook.GuestbookService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private EntriesFragment mEntriesFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static final int SITE_ID = 10182;
    public List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();

    public void reloadGuestbooks(List<GuestbookModel> guestbooks) {
        _guestbooks.clear();
        _guestbooks.addAll(guestbooks);
        mNavigationDrawerFragment._adapter.notifyDataSetChanged();

        GuestbookModel guestbook = _guestbooks.get(0);
        mTitle = guestbook.getGuestbookName();
        restoreActionBar();

        mEntriesFragment.getEntries(guestbook.getGuestbookId());
    }

    protected void getGuestbooks() {
        Session session = new SessionImpl("http://10.0.2.2:8080",
                new BasicAuthentication("joebloggs@liferay.com", "test"));
        GetGuestbooksCallback callback = new GetGuestbooksCallback(this);
        session.setCallback(callback);
        GuestbookService service = new GuestbookService(session);

        try {
            service.getGuestbooks(SITE_ID);
        }
        catch (Exception e) {
            String message = "Couldn't get guestbooks " + e.getMessage();

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getGuestbooks();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
        public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        mEntriesFragment = EntriesFragment.newInstance(position + 1);
        fragmentManager.beginTransaction().replace(R.id.container, mEntriesFragment).commit();

        if(_guestbooks.size() > 0) {
            GuestbookModel guestbook = _guestbooks.get(position);
            mEntriesFragment.getEntries(guestbook.getGuestbookId());
        }
    }

    public void onSectionAttached(int number) {
        if (_guestbooks.size() > 0) {
            mTitle = _guestbooks.get(number - 1).getGuestbookName();
        } else {
            mTitle = "";
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class EntriesFragment extends ListFragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public List<EntryModel> _entries = new ArrayList<EntryModel>();
        private ArrayAdapter _adapter;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static EntriesFragment newInstance(int sectionNumber) {
            EntriesFragment fragment = new EntriesFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public EntriesFragment() {
        }

        public void reloadEntries(List<EntryModel> entries) {
            _entries.clear();
            _entries.addAll(entries);

            _adapter.notifyDataSetChanged();
        }

        protected void getEntries(long guestbookId) {

            Session session = new SessionImpl("http://10.0.2.2:8080",
                    new BasicAuthentication("joebloggs@liferay.com", "test"));
            GetEntriesCallback callback = new GetEntriesCallback(this);
            session.setCallback(callback);
            EntryService service = new EntryService(session);

            try {
                service.getEntries(SITE_ID, guestbookId);
            }
            catch (Exception e) {
                String message = "Couldn't get entries " + e.getMessage();

                Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            _adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_2,
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
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
