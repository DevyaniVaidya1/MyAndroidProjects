package com.example.slidingtabsusingviewpager.api;

import android.graphics.Movie;

import com.example.slidingtabsusingviewpager.model.MarvelMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMoviesInterface {
    @GET("marvel")
    Call<List<MarvelMovie>> getAllData();
}
