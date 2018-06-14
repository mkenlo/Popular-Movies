package com.mkenlo.popularmovies.tasks;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import com.mkenlo.popularmovies.utils.MovieRequests;
import org.json.JSONObject;


public class FetchMovieTask extends AsyncTaskLoader<String> {

    private JSONObject params = null;
    public FetchMovieTask(Context context, JSONObject params) {
        super(context);
        this.params = params;
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
    }

    @Override
    public String loadInBackground() {
        MovieRequests request = new MovieRequests(params);
        return request.fetch();
    }
}
