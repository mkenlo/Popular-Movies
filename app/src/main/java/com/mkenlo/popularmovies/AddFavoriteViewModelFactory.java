package com.mkenlo.popularmovies;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.mkenlo.popularmovies.db.MovieDB;

public class AddFavoriteViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private int movieId;
    private MovieDB mDatabase;

    public AddFavoriteViewModelFactory(MovieDB database, int movieId) {
        this.mDatabase = database;
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddFavoriteViewModel(mDatabase, movieId);
    }
}
