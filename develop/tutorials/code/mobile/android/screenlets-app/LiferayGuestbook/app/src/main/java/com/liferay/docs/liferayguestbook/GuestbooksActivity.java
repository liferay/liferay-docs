package com.liferay.docs.liferayguestbook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.liferay.docs.guestbooklistscreenlet.GuestbookListScreenlet;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.list.BaseListListener;
import com.liferay.mobile.screens.base.list.BaseListScreenlet;

import java.util.List;

public class GuestbooksActivity extends AppCompatActivity implements BaseListListener<GuestbookModel> {

    private ActionBar _actionBar;
    private Toolbar _toolbar;
    private DrawerLayout _drawer;

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

        GuestbookListScreenlet screenlet = (GuestbookListScreenlet) findViewById(R.id.guestbooklist_screenlet);
        screenlet.setListener(this);
    }

    @Override
    public void loadingFromCache(boolean success) { }

    @Override
    public void retrievingOnline(boolean triedInCache, Exception e) { }

    @Override
    public void storingToCache(Object object) { }

    @Override
    public void onListPageFailed(BaseListScreenlet source, int startRow, int endRow, Exception e) {

        Toast.makeText(getApplicationContext(), "Page request failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListPageReceived(BaseListScreenlet source, int startRow, int endRow,
                                   List<GuestbookModel> guestbooks, int rowCount) {

        if (startRow == 0) {
            showEntries(guestbooks.get(0));
        }
    }

    @Override
    public void onListItemSelected(GuestbookModel guestbook, View view) {

        showEntries(guestbook);
        _drawer.closeDrawers();
    }

    public void showEntries(GuestbookModel guestbook) {
        _actionBar.setTitle(guestbook.getName());

        EntriesFragment entriesFragment = EntriesFragment.newInstance(guestbook.getGuestbookId());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, entriesFragment);
        transaction.commit();
    }

    private void initActionBar() {
        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        _actionBar = getSupportActionBar();
        _actionBar.setTitle("");
    }

    private void initDrawer() {
        // drawer initialization
        _drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, _drawer, _toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        _drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (_drawer.isDrawerOpen(GravityCompat.START)) {
            _drawer.closeDrawer(GravityCompat.START);
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
