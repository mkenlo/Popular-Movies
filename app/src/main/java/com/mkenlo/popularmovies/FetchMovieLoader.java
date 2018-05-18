package com.mkenlo.popularmovies;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.mkenlo.popularmovies.model.Movies;
import com.mkenlo.popularmovies.utils.MoviesUtils;

import java.util.ArrayList;

public class FetchMovieLoader extends AsyncTaskLoader<ArrayList<Movies>> {

    Boolean sortBy;
    public FetchMovieLoader(Context context, Boolean sortBy) {
        super(context);
        this.sortBy = sortBy;
    }

    @Override
    public void deliverResult(ArrayList<Movies> data) {
        super.deliverResult(data);
    }

    @Override
    public ArrayList<Movies> loadInBackground() {
        MoviesUtils utils = new MoviesUtils(sortBy);
        String json = utils.fetchMoviesRequest();
        return utils.parseJsonMovie(json);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }
}
