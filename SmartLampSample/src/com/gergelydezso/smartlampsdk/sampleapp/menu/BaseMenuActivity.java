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
import com.gergelydezso.smartlampsdk.sampleapp.alarmclock.AlarmClockFragment;
import com.gergelydezso.smartlampsdk.sampleapp.colorcontrol.ColorControlImageFragment;
import com.gergelydezso.smartlampsdk.sampleapp.motioncontrol.MotionFragment;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.MusicVisualizationFragment;
import com.gergelydezso.smartlampsdk.sampleapp.notifications.NotificationsFragment;

public class BaseMenuActivity extends FragmentActivity {

  private DrawerLayout mDrawerLayout;
  private ListView mDrawerList;
  private ActionBarDrawerToggle mDrawerToggle;

  private CharSequence mDrawerTitle;
  private CharSequence mTitle;
  private String[] mTitles;
  private static final String TAG = "BaseMenuActivity";
  private FragmentManager fragmentManager;
  private Fragment fragmentMotion;
  private static final String MOTION_FRAGMENT_TAG = "motionFragment";
  private static final String MUSIC_FRAGMENT_TAG = "musicFragment";

  private MenuItem mMotionFragmentSave;
  private String[] menuItems;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigate);

    fragmentManager = getSupportFragmentManager();

    mTitle = mDrawerTitle = getTitle();
    mTitles = getResources().getStringArray(R.array.menu_items);
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//    mDrawerList = (ListView) findViewById(R.id.left_drawer);

    // set a custom shadow that overlays the main content when the drawer opens
    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    // set up the drawer's list view with items and click listener
//    mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mTitles));
//    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

      initMenu();

    // enable ActionBar app icon to behave as action to toggle nav drawer
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);

    // ActionBarDrawerToggle ties together the the proper interactions
    // between the sliding drawer and the action bar app icon
    mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
        mDrawerLayout, /* DrawerLayout object */
        R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
        R.string.drawer_open, /* "open drawer" description for accessibility */
        R.string.drawer_close /* "close drawer" description for accessibility */
    ) {
      public void onDrawerClosed(View view) {
        getActionBar().setTitle(mTitle);
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }

      public void onDrawerOpened(View drawerView) {
        getActionBar().setTitle(mDrawerTitle);
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }
    };
    mDrawerLayout.setDrawerListener(mDrawerToggle);

    if (savedInstanceState == null) {
      selectItem(1);
    }
  }


    private void initMenu(){

        NsMenuAdapter mAdapter = new NsMenuAdapter(this);

        // Add Header
        mAdapter.addHeader(R.string.ns_menu_main_header);

        // Add first block

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

            NsMenuItemModel mItem = new NsMenuItemModel(id_title, id_icon);
//			if (res==1) mItem.counter=12; //it is just an example...
            if (res==2) mAdapter.addHeader(R.string.ns_menu_main_header2); //it is just an example...
//            if (res==3) mItem.counter=3; //it is just an example...
            mAdapter.addItem(mItem);
            res++;
        }

//		mAdapter.addHeader(R.string.ns_menu_main_header2);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        if (mDrawerList != null)
            mDrawerList.setAdapter(mAdapter);

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
    // If the nav drawer is open, hide action items related to the content view
    boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//    menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
    menu.findItem(R.id.menu_save).setVisible(!drawerOpen);
    menu.findItem(R.id.play).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // The action bar home/up action should open or close the drawer.
    // ActionBarDrawerToggle will take care of this.
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
      case R.id.play:
        motionF.play();
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

  private void costumizeActionBarMenuItems(){

    mMotionFragmentSave.setVisible(false);
    invalidateOptionsMenu();

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

    switch (position) {
//      case 4:
//        Fragment fragmentAlarmClock = new AlarmClockFragment();
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentAlarmClock).commit();
//        costumizeActionBarMenuItems();
//        break;
      case 1:

        Fragment fragmentColorControl = new ColorControlImageFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentColorControl).commit();

        break;
      case 2:

        fragmentMotion = new MotionFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentMotion, MOTION_FRAGMENT_TAG).commit();

        break;
      case 3:

        Fragment fragmentMusicV = new MusicVisualizationFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentMusicV, MUSIC_FRAGMENT_TAG).commit();
        break;
      case 4:

        Fragment fragmentNotifications = new NotificationsFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentNotifications).commit();
        break;
    }

    // update selected item and title, then close the drawer
    mDrawerList.setItemChecked(position, true);
    setTitle(mTitles[position - 1]);
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

}