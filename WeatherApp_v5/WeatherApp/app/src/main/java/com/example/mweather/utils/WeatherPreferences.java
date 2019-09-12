package com.example.mweather.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mweather.models.WeatherItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * App specific Shared preference to store list of added cities
 */
public class WeatherPreferences {
    String KEY_WEATHER_ARRAY_LIST = "KEY_WEATHER_ARRAY_LIST";
    String KEY_WEATHER_DATA_LIST = "KEY_WEATHER_DATA_LIST";
    private static WeatherPreferences INSTANCE = null;
    private TinyDB mTinyDB = null;
    private Gson gson = new Gson();
    public static final String TAG = WeatherPreferences.class.getSimpleName();

    private WeatherPreferences(@NonNull Context context) {
        checkNotNull(context);
        mTinyDB = new TinyDB(context);
    }

    /**
     * get instance of this preference class
     * @param context
     * @return
     */
    public static WeatherPreferences getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new WeatherPreferences(context);
        }
        return INSTANCE;
    }

    /**
     * Stores the list of cities
     * @param weatherCities
     */
    public void saveWeatherCities(ArrayList<String> weatherCities) {
        mTinyDB.putListString(KEY_WEATHER_ARRAY_LIST, weatherCities);
    }

    /**
     * get the list of added cities
     * @return
     */
    public ArrayList<String> getWeatherCities() {
        return mTinyDB.getListString(KEY_WEATHER_ARRAY_LIST);
    }


//    public void saveOfflineCitiesWeatherData(ArrayList<Object> citiesWeather) {
//        mTinyDB.putListObject(KEY_WEATHER_DATA_LIST, citiesWeather);
//    }
//
//    public ArrayList<String> getOfflineCitiesWeatherData() {
//        return mTinyDB.getList(KEY_WEATHER_DATA_LIST);
//    }

    public void saveOfflineCitiesWeatherData(ArrayList<WeatherItem> citiesWeather) {
        String jsonCitiesWeather = gson.toJson(citiesWeather);
        Log.d(TAG,"jsonCitiesWeather = " + jsonCitiesWeather);
        mTinyDB.putString(KEY_WEATHER_DATA_LIST, jsonCitiesWeather);

    }

    public ArrayList<WeatherItem> getOfflineCitiesWeatherData() {
        String jsonCitiesWeather = mTinyDB.getString(KEY_WEATHER_DATA_LIST);
        Type type = new TypeToken<List<WeatherItem>>(){}.getType();

        ArrayList<WeatherItem> citiesWeather = gson.fromJson(jsonCitiesWeather, type);
        if(citiesWeather == null){
            citiesWeather = new ArrayList<WeatherItem>();
        }
        Log.d(TAG, "getOfflineCitiesWeatherData: "+ citiesWeather.size());
        return citiesWeather;
    }


}
