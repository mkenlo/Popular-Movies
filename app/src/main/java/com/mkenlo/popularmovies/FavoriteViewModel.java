package com.mkenlo.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mkenlo.popularmovies.db.MovieDB;
import com.mkenlo.popularmovies.model.Movies;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private LiveData<List<Movies>> favoriteMovies;
    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        MovieDB mDb = MovieDB.getInstance(this.getApplication());
        favoriteMovies = mDb.movieDao().getAllMovies();

    }

    public LiveData<List<Movies>> getFavoriteMovies() {
        return favoriteMovies;
    }



}
