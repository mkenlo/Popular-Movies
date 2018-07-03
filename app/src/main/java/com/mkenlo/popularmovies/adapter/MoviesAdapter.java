package com.mkenlo.popularmovies.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mkenlo.popularmovies.DetailsActivity;
import com.mkenlo.popularmovies.R;
import com.mkenlo.popularmovies.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    List<Movies> movies;

    public MoviesAdapter() {
        super();
        movies = new ArrayList<>();
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public List<Movies> getMovies() {
        return movies;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MoviesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        final Movies movie = movies.get(position);

        Picasso.get()
                .load(movie.getPoster())
                .into(holder.movieImage);

        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailActivity = new Intent(v.getContext(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movie);
                detailActivity.putExtras(bundle);
                v.getContext().startActivity(detailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView movieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.iv_movie_poster);
        }
    }
}
