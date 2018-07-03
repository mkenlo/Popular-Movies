package com.mkenlo.popularmovies.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mkenlo.popularmovies.adapter.MoviesAdapter;
import com.mkenlo.popularmovies.tasks.FetchMovieTask;
import com.mkenlo.popularmovies.R;
import com.mkenlo.popularmovies.model.Movies;
import com.mkenlo.popularmovies.utils.Objectify;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    private RecyclerView mRvMovieList;
    private MoviesAdapter mAdapter;
    private TextView fragment_headline;
    private static final String KEY_SORT_MOVIE_BY = "list_sorting";
    private String sortPreference;
    public ArrayList<Movies> movies;

    private final String KEY_RECYCLER_STATE = "recycler_state";
    private Parcelable mRecyclerViewState;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        GridLayoutManager glManager = new GridLayoutManager(getContext(), 3);

        movies = new ArrayList<>();
        fragment_headline = rootView.findViewById(R.id.tv_home_title);
        fragment_headline.setText(R.string.home_fragment_title);
        mRvMovieList = rootView.findViewById(R.id.rv_movies_list);
        mRvMovieList.setLayoutManager(glManager);
        mRvMovieList.setHasFixedSize(true);
        mAdapter = new MoviesAdapter();
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(getContext());
        sortPreference = sharedPref.getString(
                KEY_SORT_MOVIE_BY,
                getResources().getString(R.string.pref_default_sorting));
        getLoaderManager().initLoader(0, null, this).forceLoad();
        mRvMovieList.setAdapter(mAdapter);
        return rootView;
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        try {
            JSONObject params = new JSONObject(String.format("{'sort_by':%s}", sortPreference));
            return new FetchMovieTask(getActivity(), params);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        movies = Objectify.getMovies(data);
        mAdapter.setMovies(movies);
        mRvMovieList.setAdapter(mAdapter);
        updateUIHeadline(sortPreference);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    private void updateUIHeadline(String sortPref) {
        if (sortPref.equalsIgnoreCase("1"))
            fragment_headline.setText(R.string.home_fragment_title);
        else
            fragment_headline.setText(R.string.home_fragment_title_2);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_RECYCLER_STATE, mRvMovieList.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null){
            mRecyclerViewState = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // restore RecyclerView state
        if (mRecyclerViewState != null) {
            mRvMovieList.getLayoutManager().onRestoreInstanceState(mRecyclerViewState);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
