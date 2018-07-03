package com.mkenlo.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mkenlo.popularmovies.db.MovieDB;
import com.mkenlo.popularmovies.model.Movies;

public class AddFavoriteViewModel extends ViewModel {

    LiveData<Movies> movie;

    public AddFavoriteViewModel(MovieDB database, int movieId) {
        movie = database.movieDao().getOneMovie(movieId);
    }

    public LiveData<Movies> getMovie() {
        return movie;
    }
}
