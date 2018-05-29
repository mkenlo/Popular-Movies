package com.mkenlo.popularmovies.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mkenlo.popularmovies.FetchMovieLoader;
import com.mkenlo.popularmovies.MoviesAdapter;
import com.mkenlo.popularmovies.R;
import com.mkenlo.popularmovies.model.Movies;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Movies>> {

    private RecyclerView mRvMovieList;
    private MoviesAdapter mAdapter;
    private TextView fragment_headline;
    private static final String KEY_SORT_MOVIE_BY = "list_sorting";
    private String sortPreference;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        GridLayoutManager glManager = new GridLayoutManager(getContext(), 3);


        fragment_headline = rootView.findViewById(R.id.tv_home_title);
        fragment_headline.setText(R.string.home_fragment_title);
        mRvMovieList = rootView.findViewById(R.id.rv_movies_list);
        mRvMovieList.setLayoutManager(glManager);
        mRvMovieList.setHasFixedSize(true);
        mAdapter = new MoviesAdapter();
        mAdapter.setMoviesList(new ArrayList<Movies>());
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(getContext());
        sortPreference = sharedPref.getString(KEY_SORT_MOVIE_BY, getResources().getString(R.string.pref_default_sorting));
        getLoaderManager().initLoader(0, null, this).forceLoad();
        mRvMovieList.setAdapter(mAdapter);
        return rootView;
    }


    @NonNull
    @Override
    public Loader<ArrayList<Movies>> onCreateLoader(int id, @Nullable Bundle args) {
        return new FetchMovieLoader(getActivity(), sortPreference);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movies>> loader, ArrayList<Movies> data) {
        mAdapter.setMoviesList(data);
        mAdapter.notifyDataSetChanged();
        mRvMovieList.setAdapter(mAdapter);
        updateUIHeadline(sortPreference);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movies>> loader) {

    }

    private void updateUIHeadline(String sortPref){
        if(sortPref.equalsIgnoreCase("1"))
            fragment_headline.setText(R.string.home_fragment_title);
        else
            fragment_headline.setText(R.string.home_fragment_title_2);
    }

}
