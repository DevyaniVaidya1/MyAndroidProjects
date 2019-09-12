package com.example.mweather.models;

import androidx.annotation.NonNull;

import com.example.mweather.utils.WeatherUtil;

/**
 * Model class to store weather city wise
 */
public class WeatherItem {
    private final String mCity;
    private final String mImg;
    private float mDegreeCelsius =0;
    private final int mHumidity;
    private final float mRainLevel;
    private final float mWind;
    private String mTemperature = "0";

    public WeatherItem(@NonNull String city, @NonNull String img, @NonNull float degreeCelsius, @NonNull int humidity, @NonNull float rainLevel, @NonNull float wind) {
        mCity = city;
        mImg = img;
        mDegreeCelsius = degreeCelsius;
        mHumidity = humidity;
        mRainLevel = rainLevel;
        mWind = wind;
    }

    public WeatherItem(@NonNull String city, @NonNull String img, @NonNull double kelvin, @NonNull int humidity, @NonNull float rainLevel, @NonNull float wind) {
        mCity = city;
        mImg = img;
        mDegreeCelsius = WeatherUtil.convertKelvinInCelsius(kelvin);
        mHumidity = humidity;
        mRainLevel = rainLevel;
        mWind = wind;
        mTemperature = "";
    }

    public WeatherItem(@NonNull String city, @NonNull String img, @NonNull String temp, @NonNull int humidity, @NonNull float rainLevel, @NonNull float wind) {
        mCity = city;
        mImg = img;
        mTemperature = temp;
        mHumidity = humidity;
        mRainLevel = rainLevel;
        mWind = wind;

    }

    public String getCity() {
        return mCity;
    }

    public String getImg() {
        return mImg;
    }

    public float getDegreeCelsius() {
        return mDegreeCelsius;
    }

    public float getHumidity() {
        return mHumidity;
    }

    public float getRainLevel() {
        return mRainLevel;
    }

    public float getWind() {
        return mWind;
    }

    public String getTemperature() {
        return mTemperature;
    }
}
