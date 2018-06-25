package com.mkenlo.popularmovies.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mkenlo.popularmovies.model.Movies;

@Database(entities = {Movies.class}, version = 1, exportSchema = false)
public abstract class MovieDB extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "movies";
    private static MovieDB ourInstance;

    public static MovieDB getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (LOCK) {
                ourInstance = Room
                        .databaseBuilder(context.getApplicationContext(), MovieDB.class, DATABASE_NAME)
                        .build();
            }
        }
        return ourInstance;
    }


    public abstract MovieDao movieDao();


}
