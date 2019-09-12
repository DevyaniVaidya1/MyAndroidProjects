package com.example.rrtrofitusingapiquery.api;

import com.example.rrtrofitusingapiquery.model.Example;

import java.util.List;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMoviesInterface {
@GET("answers")
    Call<List<Example>> getAllData(@Query("order") String order,
                                   @Query("sort") String sort,
                                   @Query("site") String  site);
}
