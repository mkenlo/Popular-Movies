package com.mkenlo.popularmovies.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mkenlo.popularmovies.R;
import com.mkenlo.popularmovies.model.MovieTrailer;
import com.mkenlo.popularmovies.tasks.FetchMovieTask;
import com.mkenlo.popularmovies.utils.Objectify;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TrailerMovieFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{


    private static final String ARG_MOVIE_ID = "movie id";
    public int movieId;
    public ArrayList<MovieTrailer> trailers;
    public TrailerAdapter adapter;
    public RecyclerView recyclerView;

    public TrailerMovieFragment() {
        // Required empty public constructor
    }

     public static TrailerMovieFragment newInstance(int movieId) {

        Bundle args = new Bundle();
        args.putInt(ARG_MOVIE_ID, movieId);

        TrailerMovieFragment fragment = new TrailerMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieId = getArguments().getInt(ARG_MOVIE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trailer_movie, container, false);
        trailers = new ArrayList<>();
        adapter = new TrailerAdapter();
        recyclerView = rootView.findViewById(R.id.rv_videos_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        getLoaderManager().initLoader(3, null, this).forceLoad();
        return rootView;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        try {
            JSONObject params = new JSONObject(String.format("{'movie_id':%s, 'details':%s}", movieId, "videos"));
            return new FetchMovieTask(getActivity(), params);

        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        trailers = Objectify.getMovieTrailer(data);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public class TrailerAdapter extends RecyclerView.Adapter<TrailerVH>{
        @NonNull
        @Override
        public TrailerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
            return new TrailerVH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull TrailerVH holder, int position) {
            final MovieTrailer video = trailers.get(position);
            holder.trailerName.setText(video.getName());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * When clicked, the Youtube App will be launched
                     */

                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("vnd.youtube://"+video.getYoutubeKey())));
                }
            });
        }

        @Override
        public int getItemCount() {
            return trailers.size();
        }
    }

    public class TrailerVH extends RecyclerView.ViewHolder{
        TextView trailerName;
        LinearLayout linearLayout;
        public TrailerVH(View itemView) {
            super(itemView);

            trailerName = itemView.findViewById(R.id.tv_trailer_name);
            linearLayout = itemView.findViewById(R.id.ll_trailer);
        }
    }
}
