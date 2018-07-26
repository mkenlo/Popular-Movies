package com.mkenlo.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.mkenlo.popularmovies.fragment.FavoriteFragment;
import com.mkenlo.popularmovies.fragment.HomeFragment;
import com.mkenlo.popularmovies.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SAVE_FRAGMENT = "fragment_item";
    private static String FRAGMENT_TAG = "home";
    BottomNavigationView mNavigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment frag = selectFragment(item.getItemId());
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_fragment_container,  frag, FRAGMENT_TAG);
            fragmentTransaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigation = findViewById(R.id.navigation);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (!isDeviceConnectedToInternet()) {
            Snackbar snackbar = Snackbar.make(mNavigation, "Unable to connect to Internet", Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            snackbar.show();
            FrameLayout fl = findViewById(R.id.frame_error_container);
            fl.setVisibility(View.VISIBLE);
        }else{
            FrameLayout fl = findViewById(R.id.frame_error_container);
            fl.setVisibility(View.GONE);
        }
        Fragment frag;
        if(savedInstanceState!=null){
            frag = selectFragment(savedInstanceState.getInt(KEY_SAVE_FRAGMENT));
        }else{
         frag = HomeFragment.newInstance();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment_container, frag, FRAGMENT_TAG);
        fragmentTransaction.commit();

    }

    private Fragment selectFragment(int itemId) {

        Fragment frag = null;
        Fragment restoreFrag = null;
        switch (itemId) {
            case R.id.navigation_home:

                frag = new HomeFragment();
                FRAGMENT_TAG = "home";
                restoreFrag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                if(restoreFrag!=null) frag = restoreFrag;
                break;
            case R.id.navigation_favorite:
                frag = new FavoriteFragment();
                FRAGMENT_TAG = "favorites";
                restoreFrag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                if(restoreFrag!=null) frag = restoreFrag;
                break;
            case R.id.navigation_search:
                frag = new SearchFragment();
                FRAGMENT_TAG = "search";
                restoreFrag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                if(restoreFrag!=null) frag = restoreFrag;
        }
        return frag;
    }


    private boolean isDeviceConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SAVE_FRAGMENT, mNavigation.getSelectedItemId());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mNavigation.setSelectedItemId(savedInstanceState.getInt(KEY_SAVE_FRAGMENT));

    }


}
