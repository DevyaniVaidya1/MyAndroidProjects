package com.example.sessionlistview;

import android.widget.BaseAdapter;

public class City {
    private String CityName;

    public City(String cityName) {
        CityName = cityName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}
