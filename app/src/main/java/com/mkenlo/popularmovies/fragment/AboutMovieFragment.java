package com.mkenlo.popularmovies.fragment;


import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mkenlo.popularmovies.R;
import com.mkenlo.popularmovies.model.Movies;
import com.mkenlo.popularmovies.tasks.FetchMovieTask;
import com.mkenlo.popularmovies.utils.Objectify;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutMovieFragment extends Fragment {

    private static final String ARG_MOVIE = "movie";
    //private int movieId;
    private Movies movie;
    ImageView iv_movie_poster ;
    TextView tv_movie_rating;
    TextView tv_movie_released ;
    TextView tv_movie_title ;
    TextView tv_movie_storyline;
    TextView tv_movie_genre;
    LinearLayout linearLayout;


    public AboutMovieFragment() {
        // Required empty public constructor
    }
    public static AboutMovieFragment newInstance(Movies movie) {
        AboutMovieFragment fragment = new AboutMovieFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie =  new Movies();
        if (getArguments() != null) {
            movie = getArguments().getParcelable(ARG_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_about_movie, container, false);

        iv_movie_poster = rootView.findViewById(R.id.iv_movie_poster);
        tv_movie_rating = rootView.findViewById(R.id.tv_movie_rating);
        tv_movie_released = rootView.findViewById(R.id.tv_movie_date_released);
        tv_movie_title = rootView.findViewById(R.id.tv_movie_title);
        tv_movie_storyline = rootView.findViewById(R.id.tv_movie_storyline);
        tv_movie_genre = rootView.findViewById(R.id.tv_movie_genre);

        linearLayout = rootView.findViewById(R.id.ll_rating);

        updateUI();

        return rootView;
    }


    public void updateUI(){

        Picasso.get().load(movie.getPoster()).into(iv_movie_poster);
        tv_movie_rating.setText(String.valueOf(movie.getRating()));
        tv_movie_released.setText(movie.getReleased_date());
        tv_movie_title.setText(movie.getTitle());
        tv_movie_storyline.setText(movie.getStoryline());
        tv_movie_genre.setText(movie.getGenre());

        linearLayout.removeAllViewsInLayout();
        populateRating(Double.valueOf(movie.getRating()));
    }

    public void populateRating(double rating){
        int n = (int) rating;
        int count=0;
        for(int i = 0; i< n ; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.ic_star_full);
            linearLayout.addView(imageView);
            count++;
        }
        if((rating-n)>0){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.ic_star_half);
            linearLayout.addView(imageView);
            count++;
        }
        for(int i = 0; i< (10-count) ; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.ic_star_border);
            linearLayout.addView(imageView);
        }
    }



}
