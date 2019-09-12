package com.example.viewpagerexample.api;

import com.example.viewpagerexample.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMoviesInterface {
    @GET("marvel")
   Call<List<Movie>> getAllData();
}
