package com.mkenlo.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import com.mkenlo.popularmovies.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MoviesUtils {


    private static final String API_BASEURL = "https://api.themoviedb.org/3/discover/";
    private static final String API_KEY = "64a3190eb83b3b72783d41a185754482";
    private static final String API_IMG_BASEURL = "https://image.tmdb.org/t/p/w500";
    private String[] discover_type = {"movie", "tv"};


    public static String fetchMoviesRequest(){

        String jsonResponse = "";

        HttpURLConnection request = null;

        try {

            Uri.Builder built = Uri.parse(API_BASEURL).buildUpon();
            built.appendPath("movie");
            built.appendQueryParameter("api_key", API_KEY);
            built.appendQueryParameter("language", "en-US");
            built.appendQueryParameter("sort_by", "popularity.desc");
            built.appendQueryParameter("include_adult", "false");
            built.appendQueryParameter("page", "1");
            Uri builtUri = built.build();

            URL url = new URL(builtUri.toString());
            request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(request.getInputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(in, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            StringBuilder buffer = new StringBuilder();

            while (line != null) {

                buffer.append(line);
                line = reader.readLine();
            }

            in.close();
            jsonResponse = buffer.toString();

        } catch (IOException ex) {

            ex.printStackTrace();

        } finally {
            if (request != null)
                request.disconnect();
        }

        return jsonResponse;

    }

    public static ArrayList<Movies> parseJsonMovie(String json){

        ArrayList<Movies> list =  new ArrayList<>();
        try{
            JSONObject jsonMovies = new JSONObject(json);
            JSONArray res = jsonMovies.getJSONArray("results");
            for(int i= 0; i< res.length();i++){
                Movies movie = new Movies();
                JSONObject  film = res.getJSONObject(i);
                movie.setTitle(film.optString("original_title"));
                movie.setPoster(API_IMG_BASEURL.concat(film.optString("poster_path")));
                movie.setId(film.optInt("id"));
                movie.setRating(film.getInt("vote_average"));
                movie.setStoryline(film.optString("overview"));
                list.add(movie);
            }
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }

        return list;
    }

}
