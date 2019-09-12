package com.example.mweather.api;


import com.example.mweather.models.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API provider for weather api
 */
public interface WeatherAPI {
    @GET("weather")
    Call<WeatherData> getWeatherCity(@Query("q") String city);
}

