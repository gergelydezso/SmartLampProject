package com.gergelydezso.smartlampsdk.sampleapp.menu;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.colorcontrol.ColorControlImageFragment;
import com.gergelydezso.smartlampsdk.sampleapp.motioncontrol.MotionFragment;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.MusicVisualizationFragment;
import com.gergelydezso.smartlampsdk.sampleapp.notifications.NotificationsFragment;

public class BaseMenuActivity extends FragmentActivity {


    private static final String TAG = "BaseMenuActivity";
    private static final String MOTION_FRAGMENT_TAG = "motionFragment";
    private static final String MUSIC_FRAGMENT_TAG = "musicFragment";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private FragmentManager fragmentManager;
    private Fragment fragmentMotion;
    private MenuItem mMotionFragmentSave;
    private String[] menuItems;

    private boolean menu_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

        fragmentManager = getSupportFragmentManager();

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        initListMenu();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(1);
        }
    }

    private void initListMenu() {

        ListMenuAdapter mAdapter = new ListMenuAdapter(this);

        mAdapter.addHeader(R.string.ns_menu_main_header);

        menuItems = getResources().getStringArray(
                R.array.ns_menu_items);
        String[] menuItemsIcon = getResources().getStringArray(
                R.array.ns_menu_items_icon);

        int res = 0;

        for (String item : menuItems) {
            int id_title = getResources().getIdentifier(item, "string",
                    this.getPackageName());
            int id_icon = getResources().getIdentifier(menuItemsIcon[res],
                    "drawable", this.getPackageName());

            ListMenuItemModel mItem = new ListMenuItemModel(id_title, id_icon);
            if (res == 2) {
                mAdapter.addHeader(R.string.ns_menu_main_header2);
            }
            mAdapter.addItem(mItem);
            res++;

        }
        if (mDrawerList != null) {
            mDrawerList.setAdapter(mAdapter);
        }
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        mMotionFragmentSave = menu.findItem(R.id.menu_save);
        return true;
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        Log.d("BaseMenu", "boolean: " + drawerOpen);

        if (drawerOpen) {
            menu_save = false;
        }

        menu.findItem(R.id.menu_save).setVisible(menu_save);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        MotionFragment motionF = (MotionFragment) getSupportFragmentManager()
                .findFragmentByTag(MOTION_FRAGMENT_TAG);

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.menu_save:
                motionF.addListElement();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void costumizeActionBarMenuItems() {

        mMotionFragmentSave.setVisible(false);
        invalidateOptionsMenu();

    }

    private void hideABMenuItems() {
        menu_save = false;
    }

    private void selectItem(int position) {

        Log.d(TAG, "position: " + position);

        // update the main content by replacing fragments
        // Fragment fragment = new PlanetFragment();
        // Bundle args = new Bundle();
        // args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        // fragment.setArguments(args);
        //
        // FragmentManager fragmentManager = getFragmentManager();
        // fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        ListMenuItemModel item = (ListMenuItemModel) mDrawerList.getItemAtPosition(position);

        switch (item.title) {

            case R.string.ns_menu_snippet1:

                Fragment fragmentColorControl = new ColorControlImageFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentColorControl).commit();
                hideABMenuItems();

                break;
            case R.string.ns_menu_snippet2:

                fragmentMotion = new MotionFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentMotion, MOTION_FRAGMENT_TAG).commit();

                menu_save = true;

                break;
            case R.string.ns_menu_snippet3:

                Fragment fragmentMusicV = new MusicVisualizationFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentMusicV, MUSIC_FRAGMENT_TAG).commit();
                hideABMenuItems();
                break;
            case R.string.ns_menu_snippet4:

                Fragment fragmentNotifications = new NotificationsFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentNotifications).commit();
                hideABMenuItems();
                break;
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);

        String title = getResources().getString(item.title);
        setTitle(title);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}