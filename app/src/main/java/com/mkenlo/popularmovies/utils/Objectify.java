package com.mkenlo.popularmovies.utils;

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
        return null;
    }
    public static ArrayList<MovieTrailer> getMovieTrailer(String json){
        return null;
    }
}
