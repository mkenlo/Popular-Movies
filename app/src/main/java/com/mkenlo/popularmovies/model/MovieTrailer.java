package com.mkenlo.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieTrailer implements Parcelable{

    String name;
    int movieId;
    String youtubeKey;


    public MovieTrailer() {
    }

    public MovieTrailer(Parcel in) {
        this.movieId = in.readInt();
        this.name = in.readString();
        this.youtubeKey = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getYoutubeKey() {
        return youtubeKey;
    }

    public void setYoutubeKey(String youtubeKey) {
        this.youtubeKey = youtubeKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(youtubeKey);
        dest.writeInt(movieId);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public MovieTrailer createFromParcel(Parcel in) {
            return new MovieTrailer(in);
        }

        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };
}
