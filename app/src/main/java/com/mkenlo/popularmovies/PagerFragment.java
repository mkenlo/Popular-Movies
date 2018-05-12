package com.mkenlo.popularmovies;


import android.os.Bundle;
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

import com.mkenlo.popularmovies.model.Movies;

import java.util.ArrayList;

public class PagerFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Movies>> {

    private static String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRvMovieList;
    private MoviesAdapter mAdapter;

    public PagerFragment() {
        // Required empty public constructor
    }

    public static PagerFragment newInstance (int sectionNumber){
        PagerFragment fragment = new  PagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        GridLayoutManager glManager = new GridLayoutManager(getContext(), 2);

        mRvMovieList = rootView.findViewById(R.id.rv_movies_list);
        mRvMovieList.setLayoutManager(glManager);

        mAdapter = new MoviesAdapter();

        getLoaderManager().initLoader(0, null, this).forceLoad();
        mRvMovieList.setAdapter(mAdapter);
        return rootView;
    }


    @NonNull
    @Override
    public Loader<ArrayList<Movies>> onCreateLoader(int id, @Nullable Bundle args) {
        return new FetchMovieLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movies>> loader, ArrayList<Movies> data) {
            mAdapter.setMoviesList(data);
            mAdapter.notifyDataSetChanged();
            mRvMovieList.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movies>> loader) {

    }


}
