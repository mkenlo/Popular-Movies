package com.mkenlo.popularmovies.utils;

import android.net.Uri;

import com.mkenlo.popularmovies.BuildConfig;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MoviesUtils {


    private static final String API_BASE_URL = "https://api.themoviedb.org/3/movie";
    private static String API_KEY = "DUMMY API KEY. PLEASE CHANGE ME"; // Add your API KEY here
    private static final String API_IMG_BASE_URL = "https://image.tmdb.org/t/p/w300";
    private String[] sortPreference = {"popular", "top_rated"};

    private URL requestURL;

    public MoviesUtils(String params) {

        setApiKey();
        setRequestURL(params);

    }

    private void setRequestURL(String params) {

        try{
            Uri.Builder built = Uri.parse(API_BASE_URL).buildUpon();
            built.appendPath(sortPreference[Integer.valueOf(params)-1]);
            built.appendQueryParameter("api_key", API_KEY);
            built.appendQueryParameter("language", "en-US");
            built.appendQueryParameter("page", "1");
            Uri builtUri = built.build();

            this.requestURL = new URL(builtUri.toString());
        }
        catch(MalformedURLException ex){
            ex.printStackTrace();
        }

    }

    public String fetchMoviesRequest(){

        String jsonResponse = "";

        HttpURLConnection request = null;

        try {

            request = (HttpURLConnection) requestURL.openConnection();
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

    public ArrayList<Movies> parseJsonMovie(String json){

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

    public static void setApiKey() {
        if (API_KEY.equalsIgnoreCase("DUMMY API KEY. PLEASE CHANGE ME")) {
            API_KEY = BuildConfig.TMdbApiKey;
        }
    }
}
