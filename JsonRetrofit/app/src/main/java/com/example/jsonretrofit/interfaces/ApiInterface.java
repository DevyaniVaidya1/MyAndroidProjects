package com.example.jsonretrofit.interfaces;

import com.example.jsonretrofit.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}
