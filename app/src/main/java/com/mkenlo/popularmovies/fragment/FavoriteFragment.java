package com.mkenlo.popularmovies.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mkenlo.popularmovies.FavoriteViewModel;
import com.mkenlo.popularmovies.R;
import com.mkenlo.popularmovies.adapter.MoviesAdapter;
import com.mkenlo.popularmovies.model.Movies;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    RecyclerView mRvMovieList;
    MoviesAdapter mAdapter;
    static final String ARG_KEY_MOVIE = "movies";
    static final String KEY_SAVE_STATE = "save_state";
    List<Movies> moviesList;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        mRvMovieList = rootView.findViewById(R.id.rv_movies_list);
        mRvMovieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        moviesList = new ArrayList<>();
        mAdapter = new MoviesAdapter();
        mRvMovieList.setAdapter(mAdapter);

        setupViewModel();
        return rootView;
    }

    private void setupViewModel() {
        FavoriteViewModel viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        viewModel.getFavoriteMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable List<Movies> movies) {
                moviesList = movies;
                mAdapter.setMovies(movies);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Movies> movies = new ArrayList<>(moviesList);
        outState.putParcelableArrayList(ARG_KEY_MOVIE, movies);
       outState.putParcelable(KEY_SAVE_STATE, mRvMovieList.getLayoutManager().onSaveInstanceState());


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            moviesList = savedInstanceState.getParcelableArrayList(ARG_KEY_MOVIE);
            mAdapter.setMovies(moviesList);
            mRvMovieList.getLayoutManager().onRestoreInstanceState(
                    savedInstanceState.getParcelable(KEY_SAVE_STATE));

        }

    }



}
