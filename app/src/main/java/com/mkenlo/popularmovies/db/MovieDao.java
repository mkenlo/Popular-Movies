package com.mkenlo.popularmovies.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mkenlo.popularmovies.model.Movies;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM favorite_movies")
    LiveData<List<Movies>> getAllMovies();

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    LiveData<Movies> getOneMovie(int id);

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    Movies getMovieById(int id);

    @Insert
    void insert(Movies movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Movies movie);

    @Delete
    void delete(Movies movie);
}
