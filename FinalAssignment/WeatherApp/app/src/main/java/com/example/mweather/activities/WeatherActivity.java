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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        addFragment();

    }

    /**
     * getSupportFragmentManager()
     * Return the FragmentManager for interacting with fragments associated
     * with this activity.
     */
    private void addFragment() {
        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.newInstance();
            UiUtils.addFragmentToActivity(getSupportFragmentManager()
                    , weatherFragment
                    , R.id.contentFrame
                    , false);
        }
    }

}
