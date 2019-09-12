package com.example.mweather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class to store weather details received in response
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    public Integer all;

}
