package com.mkenlo.popularmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkenlo.popularmovies.model.MovieReview;
import com.mkenlo.popularmovies.model.MovieTrailer;
import com.mkenlo.popularmovies.model.Movies;
import com.mkenlo.popularmovies.tasks.FetchMovieTask;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private Movies movie;
    private MovieReview movieReview;
    private MovieTrailer movieTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You just added this Movie as your Favorite", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        movie = getIntent().getParcelableExtra("movie");
        ImageView iv_movie_poster = findViewById(R.id.iv_movie_poster);
        TextView tv_movie_rating = findViewById(R.id.tv_movie_rating);
        TextView tv_movie_released = findViewById(R.id.tv_movie_date_released);
        TextView tv_movie_title = findViewById(R.id.tv_movie_title);
        TextView tv_movie_storyline = findViewById(R.id.tv_movie_storyline);

        Picasso.get().load(movie.getPoster()).into(iv_movie_poster);
        tv_movie_rating.setText(String.valueOf(movie.getRating()));
        tv_movie_released.setText(movie.getReleased_date());
        tv_movie_title.setText(movie.getTitle());
        tv_movie_storyline.setText(movie.getStoryline());

        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        try {
            JSONObject params = new JSONObject(String.format("{'movie_id':%s}", movie.getId()));
            return new FetchMovieTask(this, params);

        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
