package com.example.pritam.salseapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.pritam.fragments.AddNewMap;
import com.example.pritam.fragments.Home;
import com.example.pritam.fragments.Record;
import com.example.pritam.model.BottomNavigationBehavior;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private static String TAG = MainActivity.class.getSimpleName ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        // load the store fragment by default
        toolbar = getSupportActionBar ();
        BottomNavigationView navigation = (BottomNavigationView) findViewById (R.id.navigation);
        navigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams ();
        layoutParams.setBehavior (new BottomNavigationBehavior ());

        // toolbar.setTitle ("Home");
        loadFragment (new Home ());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener () {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId ()) {
                case R.id.navigation_home:

                    fragment = new Home ();
                    loadFragment (fragment);
                    return true;
                case R.id.navigation_addNew:

                    fragment = new AddNewMap ();
                    loadFragment (fragment);
                    return true;
                case R.id.navigation_records:

                    fragment = new Record ();
                    loadFragment (fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager ().beginTransaction ();
        transaction.replace (R.id.frame_container, fragment);
        transaction.addToBackStack (null);
        transaction.commit ();
    }
}