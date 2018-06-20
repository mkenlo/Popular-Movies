package com.mkenlo.popularmovies.fragment;


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
import android.widget.TextView;

import com.mkenlo.popularmovies.R;
import com.mkenlo.popularmovies.model.MovieReview;
import com.mkenlo.popularmovies.tasks.FetchMovieTask;
import com.mkenlo.popularmovies.utils.Objectify;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewMovieFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{

    private static final String ARG_MOVIE_ID = "movie id";
    private int movieId;
    public ArrayList<MovieReview> reviews;
    public RecyclerView recyclerView;
    public ReviewAdapter adapter;


    public ReviewMovieFragment() {
        // Required empty public constructor
    }

    public static ReviewMovieFragment newInstance(int movieId) {
        ReviewMovieFragment fragment = new ReviewMovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MOVIE_ID, movieId);
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_review_movie, container, false);
        getLoaderManager().initLoader(2, null, this).forceLoad();
        reviews = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.rv_reviews_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReviewAdapter();
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        try {
            JSONObject params = new JSONObject(String.format("{'movie_id':%s, 'details':%s}", movieId, "reviews"));
            return new FetchMovieTask(getActivity(), params);

        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
       // if(loader.getId() == 2){
            reviews = Objectify.getMovieReviews(data);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
       // }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    public class ReviewAdapter extends RecyclerView.Adapter<ReviewVH>{


        @NonNull
        @Override
        public ReviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reviews, parent, false);
            return new ReviewVH(v);
        }


        @Override
        public void onBindViewHolder(@NonNull ReviewVH holder, int position) {
            holder.reviewAuthor.setText(reviews.get(position).getAuthor());
            holder.reviewContent.setText(reviews.get(position).getContent());
        }

        @Override
        public int getItemCount() {
            return reviews.size();
        }


    }
    public class ReviewVH extends RecyclerView.ViewHolder{

        TextView reviewAuthor;
        TextView reviewContent;
        public ReviewVH(View itemView) {
            super(itemView);
            reviewAuthor =  itemView.findViewById(R.id.tv_review_author);
            reviewContent = itemView.findViewById(R.id.tv_review_content);
        }
    }
}
