package com.mkenlo.popularmovies;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import com.mkenlo.popularmovies.db.MovieDB;
import com.mkenlo.popularmovies.fragment.AboutMovieFragment;
import com.mkenlo.popularmovies.fragment.ReviewMovieFragment;
import com.mkenlo.popularmovies.fragment.TrailerMovieFragment;
import com.mkenlo.popularmovies.model.Movies;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int totalPages = 3;
    private static final String ARG_MOVIE = "movie";

    Movies movie;
    MovieDB mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        mDB = MovieDB.getInstance(this);

        movie = getIntent().getParcelableExtra(ARG_MOVIE);

        // Rename UI title with the movie Title
        getSupportActionBar().setTitle(movie.getTitle());

        ImageView iv_movie_bg = findViewById(R.id.iv_movie_backdrop);
        Picasso.get().load(movie.getBackdropPoster()).into(iv_movie_bg);


        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.favorite_message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                onFavoriteFabPressed();
            }
        });

    }

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
            Fragment frag = null;
            switch(position){
                case 0:
                    frag = AboutMovieFragment.newInstance(movie);
                    break;
                case 1:
                    frag = ReviewMovieFragment.newInstance(movie.getId());
                    break;
                case 2:
                    frag = TrailerMovieFragment.newInstance(movie.getId());

            }
            return frag;

        }

        @Override
        public int getCount() {
            return totalPages;
        }
    }

    public void onFavoriteFabPressed(){

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(mDB.movieDao().getOneMovie(movie.getId()) == null){
                    mDB.movieDao().insert(movie);
                }
            }
        });

    }
}
