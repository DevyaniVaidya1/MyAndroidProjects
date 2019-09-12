package com.example.mweather.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mweather.R;
import com.example.mweather.fragment.WeatherFragment;
import com.example.mweather.utils.Constants;
import com.example.mweather.utils.UiUtils;

/**
 * Activity to hold weather and settings fragment
 */
public class WeatherActivity extends AppCompatActivity implements Constants {

    public static final String TAG = WeatherActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.newInstance();

//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.contentFrame, weatherFragment);
//            transaction.commit();

            UiUtils.addFragmentToActivity(getSupportFragmentManager(), weatherFragment, R.id.contentFrame, false);
        }

    }

}
