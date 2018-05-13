package com.mkenlo.popularmovies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mkenlo.popularmovies.model.Movies;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You just added this Movie as your Favorite", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Movies movie = (Movies) getIntent().getSerializableExtra("movie");
        ImageView iv_movie_poster = findViewById(R.id.iv_movie_poster);
        TextView tv_movie_rating = findViewById(R.id.tv_movie_rating);
        TextView tv_movie_title = findViewById(R.id.tv_movie_title);
        TextView tv_movie_storyline = findViewById(R.id.tv_movie_storyline);

        Picasso.get().load(movie.getPoster()).into(iv_movie_poster);
        tv_movie_rating.setText(String.valueOf(movie.getRating()).concat("/10"));
        tv_movie_title.setText(movie.getTitle());
        tv_movie_storyline.setText(movie.getStoryline());

    }

}
