package com.mkenlo.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mkenlo.popularmovies.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>  {


    ArrayList<Movies> moviesList;

    public MoviesAdapter() {
        moviesList = new ArrayList<>();

    }
    public void setMoviesList(ArrayList<Movies> list){
        moviesList = list;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        Movies movie = moviesList.get(position);

        //String movie =  moviesList.get(position);
        Picasso.get()
                .load(movie.getPoster())
                .into(holder.mImageMovie);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageMovie;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageMovie = itemView.findViewById(R.id.iv_movie);
        }
    }
}
