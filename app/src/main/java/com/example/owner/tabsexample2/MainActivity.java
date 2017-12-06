package com.example.owner.tabsexample2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.tabsexample2.R;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private TabLayout tabLayout;
    private PopupWindow popupWindow;
    private Context mContext;
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get intent
        Intent intent = getIntent();

        // get the student record that was retrieved from the database
        // upon logging in
        StudentRecord record = (StudentRecord) intent.getSerializableExtra("StudentRecord");
        setTitle(record.getStudentName()); // set the Title of the main (outermost) layout to the student name

        mContext = getApplicationContext();
        mLinearLayout = (LinearLayout) findViewById(R.id.all_main_LinLayout);

        // set up the toolbar for the main layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // allow for all three views to be stored in memory for fast run time
        mViewPager.setOffscreenPageLimit(3);

        // main (outermost) layout
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.getForeground().setAlpha(0); // set background to normal

        final NestedScrollView myScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        // set up the tabs for the main layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // floating button to be implemented
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myScrollView.smoothScrollTo(0, 0);
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    // log off toast
    private void toast() {Toast.makeText(this, "Logging Off...", Toast.LENGTH_SHORT).show();}

    // log of action
    private void log_off() {
        Intent intent = new Intent(this, Log_In.class);
        startActivity(intent);
    }

//    private void startAdviser() {
//        Intent intent = new Intent(this, Adviser.class);
//        startActivity(intent);
//    }

    // create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super .onCreateOptionsMenu(menu);
    }


    // handles the selection of an options item (ex: log off)
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case R.id.signout:
                log_off();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // handles the swapping through views
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //Returning the current tabs
            switch (position) {
                case 0:
                    TabAll tabA = new TabAll();
                    return tabA;
                case 1:
                    TabMajor tabM = new TabMajor();
                    return tabM;
                case 2:
                    TabWhatIf tabW = new TabWhatIf();
                    return tabW;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "All";
                case 1:
                    return "Major";
                case 2:
                    return "What If";
            }
            return null;
        }
    }

    // override the back button action to not do anything
    @Override
    public void onBackPressed() {
    }

}
