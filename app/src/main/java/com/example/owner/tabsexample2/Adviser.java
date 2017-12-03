package com.example.owner.tabsexample2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.TestLooperManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by j_spu on 11/6/2017.
 */

public class Adviser extends AppCompatActivity {

//    private String adviserName = "John Doe";
//    private String adviserEmail = "jdoe@uttyler.edu";
//    private String adviserNumber = "999-999-9999";

    private TextView advName;
    private TextView advEmail;
    private TextView advNumber;

    private ViewPager mViewPager;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adviser);

        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.adv_toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        setupDrawerContent(nvDrawer);


        advName = (TextView) findViewById(R.id.adviserName);
        advEmail = (TextView) findViewById(R.id.adviserEmail);
        advNumber = (TextView) findViewById(R.id.adviserNumber);

//        advName.setText(adviserName);
//        advEmail.setText(adviserEmail);
//        advNumber.setText(adviserNumber);



    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
//        Fragment fragment = null;
//        Class fragmentClass;
        Handler mHandler = new Handler();

        switch(menuItem.getItemId()) {
            case R.id.nav_current:
                startCurrent();
                break;
            case R.id.nav_whatif:
                startWhatIf();
                break;
            //case R.id.nav_adviser:
                //break;
            case R.id.nav_signout:
                toast();
                break;
        }

        if (menuItem.getItemId() == R.id.nav_signout) {
            mDrawer.closeDrawers();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    log_off();
                }
            }, 1000);
        }

        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private void toast() {Toast.makeText(this, "Logging Off...", Toast.LENGTH_SHORT).show();}

    private void log_off() {
        Intent intent = new Intent(this, Log_In.class);
        startActivity(intent);
    }

    private void switchToAdv() {
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
}
