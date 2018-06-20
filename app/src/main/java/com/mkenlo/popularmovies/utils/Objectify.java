package com.mkenlo.popularmovies.utils;

import android.graphics.Movie;

import com.mkenlo.popularmovies.model.MovieReview;
import com.mkenlo.popularmovies.model.MovieTrailer;
import com.mkenlo.popularmovies.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Objectify {

    /**
     *
     * This class parse a JSON string into a list of Objects (Movies, MovieReview etc...)
     *
     */

    private static final String API_IMG_BASE_URL = "https://image.tmdb.org/t/p/w300";
    private static final String API_IMG_BASE_URL_LARGE = "https://image.tmdb.org/t/p/w500";
    private static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v";


    public static ArrayList<Movies> getMovies(String json){

        ArrayList<Movies> list =  new ArrayList<>();
        try{
            JSONObject jsonMovies = new JSONObject(json);
            JSONArray res = jsonMovies.getJSONArray("results");
            for(int i= 0; i< res.length();i++){
                Movies movie = new Movies();
                JSONObject  film = res.getJSONObject(i);
                movie.setTitle(film.optString("original_title"));
                movie.setPoster(API_IMG_BASE_URL.concat(film.optString("poster_path")));
                movie.setBackdropPoster(API_IMG_BASE_URL.concat(film.optString("backdrop_path")));
                movie.setId(film.optInt("id"));
                movie.setRating(film.getString("vote_average"));
                movie.setStoryline(film.optString("overview"));
                movie.setReleased_date(film.optString("release_date"));
                list.add(movie);
            }
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }

        return list;
    }

    public static ArrayList<MovieReview> getMovieReviews(String json){

        ArrayList<MovieReview> list =  new ArrayList<>();
        try{
            JSONObject movieInfos = new JSONObject(json);
            JSONArray reviews= movieInfos.getJSONArray("results");
            for(int i= 0; i< reviews.length();i++){
                MovieReview review = new MovieReview();
                JSONObject  elt = reviews.getJSONObject(i);
                review.setMovieId(movieInfos.getInt("id"));
                review.setAuthor(elt.getString("author"));
                review.setContent(elt.getString("content"));

                list.add(review);
            }
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }

        return list;
    }
    public static ArrayList<MovieTrailer> getMovieTrailer(String json){
        ArrayList<MovieTrailer> list =  new ArrayList<>();
        try{
            JSONObject movieInfos = new JSONObject(json);
            JSONArray trailers= movieInfos.getJSONArray("results");
            for(int i= 0; i< trailers.length();i++){
                MovieTrailer video = new MovieTrailer();
                JSONObject  elt = trailers.getJSONObject(i);
                video.setMovieId(movieInfos.getInt("id"));
                video.setName(elt.getString("name"));
                video.setYoutubeKey(elt.getString("key"));

                list.add(video);
            }
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }

        return list;
    }

    public static Movies getOneMovie(String json){
        Movies movie = new Movies();
        try {
            JSONObject film = new JSONObject(json);
            movie.setTitle(film.optString("original_title"));
            movie.setPoster(API_IMG_BASE_URL.concat(film.optString("poster_path")));
            movie.setBackdropPoster(API_IMG_BASE_URL_LARGE.concat(film.optString("backdrop_path")));
            movie.setId(film.optInt("id"));
            movie.setRating(film.getString("vote_average"));
            movie.setStoryline(film.optString("overview"));
            movie.setReleased_date(film.optString("release_date"));
            JSONArray genre = film.getJSONArray("genres");

            for(int i=0; i< genre.length();i++){
                movie.setGenre(genre.getJSONObject(i).getString("name"));
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return movie;
    }
}
