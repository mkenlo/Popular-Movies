package com.mkenlo.popularmovies;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.mkenlo.popularmovies.model.Movies;
import com.mkenlo.popularmovies.utils.MoviesUtils;

import java.util.ArrayList;

public class FetchMovieLoader extends AsyncTaskLoader<ArrayList<Movies>> {

    public FetchMovieLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(ArrayList<Movies> data) {
        super.deliverResult(data);
    }

    @Override
    public ArrayList<Movies> loadInBackground() {

        String json = MoviesUtils.fetchMoviesRequest();
        return MoviesUtils.parseJsonMovie(json);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }
}
