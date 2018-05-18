package com.mkenlo.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Movies implements Parcelable {

    private String mTitle;
    private String mStoryline;
    private String mPoster;
    private String mTrailer;
    private String mRating;
    private String mGenre;
    private String[] mCast;
    private String mOriginal_lang;
    private String mReleased_date;
    private int id;

    public Movies() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getStoryline() {
        return mStoryline;
    }

    public void setStoryline(String mStoryline) {
        this.mStoryline = mStoryline;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String mPoster) {
        this.mPoster = mPoster;
    }

    public String getTrailer() {
        return mTrailer;
    }

    public void setTrailer(String mTrailer) {
        this.mTrailer = mTrailer;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String mRating) {
        this.mRating = mRating;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String[] getCast() {
        return mCast;
    }

    public void setCast(String[] mCast) {
        this.mCast = mCast;
    }

    public String getOriginal_lang() {
        return mOriginal_lang;
    }

    public void setOriginal_lang(String mOriginal_lang) {
        this.mOriginal_lang = mOriginal_lang;
    }

    public String getReleased_date() {
        return mReleased_date;
    }

    public void setReleased_date(String mReleased_date) {
        this.mReleased_date = mReleased_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movies(Parcel in){
        this.id = in.readInt();
        this.mTitle = in.readString();
        this.mPoster = in.readString();
        this.mStoryline = in.readString();
        this.mRating = in.readString();
        this.mReleased_date = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mTitle);
        dest.writeString(mPoster);
        dest.writeString(mStoryline);
        dest.writeString(mRating);
        dest.writeString(mReleased_date);
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
