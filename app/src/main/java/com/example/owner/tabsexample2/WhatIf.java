package com.example.owner.tabsexample2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by j_spu on 11/7/2017.
 */

public class WhatIf extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private TabLayout tabLayout;
    private Spinner spMajor;

    //Possible Majors
    String majors[] = {"Art", "Accounting", "Chemistry", "Finance",
            "History", "Math", "Physics", "Speech Communication"};

    ArrayAdapter<String> adapterMajor;
    String sMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_if);

        Intent intent = getIntent();

        setTitle(getTitle() + "...");

        spMajor = (Spinner) findViewById(R.id.whatIf_Spinner);

        adapterMajor = new ArrayAdapter<String>(this, R.layout.spinner_item, majors);

        adapterMajor.setDropDownViewResource(R.layout.spinner_dropdown_items);

        spMajor.setAdapter(adapterMajor);

        spMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                sMajor = adapter.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Selected Major : " + sMajor, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter = new WhatIf.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container_whatif);
//        mViewPager.setAdapter(mSectionsPagerAdapter);

//        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

//        mDrawer.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NestedScrollView myScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

//        tabLayout = (TabLayout) findViewById(R.id.tabs_whatif);
//        tabLayout.setupWithViewPager(mViewPager);

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

        nvDrawer = (NavigationView) findViewById(R.id.nvView);

//        setupDrawerContent(nvDrawer);

    }

//    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        selectDrawerItem(menuItem);
//                        return true;
//                    }
//                });
//    }

//    public void selectDrawerItem(MenuItem menuItem) {
//        // Create a new fragment and specify the fragment to show based on nav item clicked
////        Fragment fragment = null;
////        Class fragmentClass;
//        Handler mHandler = new Handler();
//
//        switch(menuItem.getItemId()) {
//            case R.id.nav_current:
//                startCurrent();
//                break;
//            case R.id.nav_whatif:
//                break;
//            case R.id.nav_adviser:
//                startAdviser();
//                break;
//            case R.id.nav_signout:
//                toast();
//                break;
//        }
//
//        if (menuItem.getItemId() == R.id.nav_signout) {
//            mDrawer.closeDrawers();
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    log_off();
//                }
//            }, 1000);
//        }
//
//            // Set action bar title
//            setTitle(menuItem.getTitle());
//            // Close the navigation drawer
//            mDrawer.closeDrawers();
//    }

    private void toast() {
        Toast.makeText(this, "Logging Off...", Toast.LENGTH_SHORT).show();}

    private void log_off() {
        Intent intent = new Intent(this, Log_In.class);
        startActivity(intent);
    }

    private void startAdviser() {
        Intent intent = new Intent(this, Adviser.class);
        startActivity(intent);
    }

    private void startWhatIf() {
        Intent intent = new Intent(this, WhatIf.class);
        startActivity(intent);
    }

    private void startCurrent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Deleted PlaceholderFragment from here

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
                    TabCore tabC = new TabCore();
                    return tabC;
                case 1:
                    TabMajor tabM = new TabMajor();
                    return tabM;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Core";
                case 1:
                    return "Major";
            }
            return null;
        }
    }

}
