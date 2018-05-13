package com.mkenlo.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mkenlo.popularmovies.fragment.FavoriteFragment;
import com.mkenlo.popularmovies.fragment.HomeFragment;
import com.mkenlo.popularmovies.fragment.SearchFragment;
import com.mkenlo.popularmovies.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment frag = selectFragment(item);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_fragment_container, frag);
            fragmentTransaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (!isDeviceConnectedToInternet()) {
            Snackbar snackbar = Snackbar.make(navigation, "Unable to connect to Internet", Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            snackbar.show();
        }

        Fragment frag = HomeFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment_container, frag);
        fragmentTransaction.commit();
    }

    public Fragment selectFragment(MenuItem item) {

        Fragment frag = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                frag = new HomeFragment();
                break;
            case R.id.navigation_favorite:
                frag = new FavoriteFragment();
                break;
            case R.id.navigation_search:
                frag = new SearchFragment();
                break;
            case R.id.navigation_settings:
                frag = new SettingsFragment();
        }
        return frag;
    }


    public boolean isDeviceConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null) ? true : false;
    }

}
