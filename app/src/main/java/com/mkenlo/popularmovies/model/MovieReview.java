package com.mkenlo.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieReview implements Parcelable{

    String author;
    String content;
    int movieId;


    public MovieReview() {
    }

    public MovieReview(Parcel in) {
        this.movieId = in.readInt();
        this.author = in.readString();
        this.content = in.readString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeString(author);
        dest.writeString(content);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };
}
