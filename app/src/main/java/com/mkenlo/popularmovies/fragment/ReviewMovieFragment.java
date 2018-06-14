package com.mkenlo.popularmovies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mkenlo.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewMovieFragment extends Fragment {


    public ReviewMovieFragment() {
        // Required empty public constructor
    }

    public static ReviewMovieFragment newInstance() {
        ReviewMovieFragment fragment = new ReviewMovieFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_movie, container, false);
    }

}
