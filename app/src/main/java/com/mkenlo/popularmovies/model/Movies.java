package com.mkenlo.popularmovies.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


@Entity(tableName = "favorite_movies")
public class Movies implements Parcelable {

    private String title;
    private String storyline;
    private String poster;
    private String rating;
    private String genre;
    private String released_date;
    private String backdrop_poster;
    @PrimaryKey
    private int id;

    public Movies() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) { this.genre = genre;  }

    public String getReleased_date() {
        return released_date;
    }

    public void setReleased_date(String mReleased_date) {
        this.released_date = mReleased_date;
    }

    public int getId() {
        return id;
    }

    public void setBackdrop_poster(String backdrop_poster) {
        this.backdrop_poster = backdrop_poster;
    }

    public void setId(int id) { this.id = id;  }

    public String getBackdropPoster() {
        return backdrop_poster;
    }

    public void setBackdropPoster(String mBackdrop_poster) {
        this.backdrop_poster = mBackdrop_poster;
    }

    public String getBackdrop_poster() {
        return backdrop_poster;
    }

    private Movies(Parcel in){
        this.id = in.readInt();
        this.title = in.readString();
        this.poster = in.readString();
        this.backdrop_poster = in.readString();
        this.storyline = in.readString();
        this.rating = in.readString();
        this.released_date = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(backdrop_poster);
        dest.writeString(storyline);
        dest.writeString(rating);
        dest.writeString(released_date);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
