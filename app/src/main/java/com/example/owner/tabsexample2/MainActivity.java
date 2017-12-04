package com.example.owner.tabsexample2;

<<<<<<< HEAD
import android.app.ActionBar;
=======
import android.app.AlertDialog;
>>>>>>> origin/master
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
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
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.tabsexample2.R;
import com.example.owner.tabsexample2.TabAll;
import com.example.owner.tabsexample2.TabCore;
import com.example.owner.tabsexample2.TabMajor;

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

    private StudentRecord record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (savedInstanceState == null) {
            Bundle extras = intent.getExtras();
            if(extras != null) {
                record = new StudentRecord(extras.getString("stu_id"), extras.getString("majorName"), extras.getString("fname"), extras.getString("lname"));
            }
        } else {
            record = new StudentRecord((String) savedInstanceState.getSerializable("stu_id"), (String) savedInstanceState.getSerializable("majorName"), (String) savedInstanceState.getSerializable("fname"), (String) savedInstanceState.getSerializable("lname"));
        }

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        StudentRecord record = (StudentRecord) intent.getSerializableExtra("StudentRecord");
        setTitle(record.getStudentName());

        Intent allIntent = new Intent(this, TabAll.class);
        allIntent.putExtra("StudentRecord", record);

        mContext = getApplicationContext();
        mLinearLayout = (LinearLayout) findViewById(R.id.all_main_LinLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the four
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawer.getForeground().setAlpha(0);

        final NestedScrollView myScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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

<<<<<<< HEAD
    }

=======
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        View headerView = nvDrawer.getHeaderView(0);
        TextView tv = (TextView)headerView.findViewById(R.id.userName);
        tv.setText(record.getStudentName());
        setupDrawerContent(nvDrawer);

    }

    private void alertMsg(String title, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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
                break;
            case R.id.nav_whatif:
                startWhatIf();
                break;
            //case R.id.nav_adviser:
                //startAdviser();
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
        } else {


            // Set action bar title
            setTitle(menuItem.getTitle());
            // Close the navigation drawer
            mDrawer.closeDrawers();
        }


    }
>>>>>>> origin/master

    private void toast() {Toast.makeText(this, "Logging Off...", Toast.LENGTH_SHORT).show();}

    private void log_off() {
        Intent intent = new Intent(this, Log_In.class);
        startActivity(intent);
    }

//    private void startAdviser() {
//        Intent intent = new Intent(this, Adviser.class);
//        startActivity(intent);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu_drawer, menu);
        return super .onCreateOptionsMenu(menu);
    }



        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case R.id.nav_signout:
                log_off();
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

//this is where the popup is
    public void onClick(View v) {
//        startActivity(new Intent(MainActivity.this,Pop.class));

        DrawerLayout mainLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popupwindow, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show in the center of the screen
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

        // greys the layout that the pop up window is on top of
        mDrawer.getForeground().setAlpha(220);

        // handles when the pop up window is closed via touch outside of window or
        // via back button
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mDrawer.getForeground().setAlpha(0);
                popupWindow.dismiss();
            }
        });

    }

    public CardView createCard(int i) {

        CardView card = new CardView(mContext);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        card.setLayoutParams(params);
        card.setRadius(20);
        card.setContentPadding(10, 10 , 10 , 10 );
        card.setMaxCardElevation(20);
        card.setMaxCardElevation(20);

        TextView tv = new TextView(mContext);
        tv.setLayoutParams(params);
        tv.setText("This is card number " + i);
        tv.setTextColor(Color.RED);

        card.addView(tv);

        // create linear layout
        // create TableLayout
        // add default header row 1
        // create the remaining rows based on the respective data

        return card;
    }

}
