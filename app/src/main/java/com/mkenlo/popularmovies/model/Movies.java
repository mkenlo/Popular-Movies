package com.mkenlo.popularmovies.model;

import java.io.Serializable;

public class Movies implements Serializable{

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

    public Movies(String mTitle) {
        this.mTitle = mTitle;
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
}
