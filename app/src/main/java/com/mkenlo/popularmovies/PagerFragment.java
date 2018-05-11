package com.mkenlo.popularmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PagerFragment extends Fragment {

    private static String ARG_SECTION_NUMBER = "section_number";
    private static String ARG_IMAGES;
    private RecyclerView mRvMovieList;

    public PagerFragment() {
        // Required empty public constructor
    }

    public static PagerFragment newInstance (int sectionNumber, ArrayList<String> images){
        PagerFragment fragment = new  PagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putStringArrayList(ARG_IMAGES, images);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        GridLayoutManager glManager = new GridLayoutManager(getContext(), 3);
        mRvMovieList = rootView.findViewById(R.id.rv_movies_list);
        mRvMovieList.setLayoutManager(glManager);

        MoviesAdapter adapter = new MoviesAdapter();
        adapter.setMoviesList(getArguments().getStringArrayList(ARG_IMAGES));
        mRvMovieList.setAdapter(adapter);

        return rootView;
    }

}
