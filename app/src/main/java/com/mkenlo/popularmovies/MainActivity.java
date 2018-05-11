package com.mkenlo.popularmovies;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.mkenlo.popularmovies.model.Movies;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movies>> {


    private RecyclerView mRvMovieList;
    private MoviesAdapter mAdapter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.section_label);
        mAdapter = new MoviesAdapter();
        mRvMovieList = findViewById(R.id.rv_movies_list);

        LinearLayoutManager glManager = new LinearLayoutManager(this);
        mRvMovieList.setLayoutManager(glManager);

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    //    mRvMovieList.setAdapter(mAdapter);
    }



    @NonNull
    @Override
    public Loader<ArrayList<Movies>> onCreateLoader(int id, @Nullable Bundle args) {
        return new FetchMovieLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movies>> loader, ArrayList<Movies> data) {
        mAdapter.setMoviesList(data);
        mAdapter.notifyDataSetChanged();
        mRvMovieList.setAdapter(mAdapter);
        textView.setText("On finished loader");

        Log.d("ONLOAD FINISHED", "my Loader has returned");

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movies>> loader) {

    }
}
