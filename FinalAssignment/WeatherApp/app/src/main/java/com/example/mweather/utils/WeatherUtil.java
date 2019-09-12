package com.example.mweather.utils;


import android.util.Log;

import java.math.BigDecimal;

/**
 * Weather Util to convert kelvin to degree celsius or Fahrenheit
 */

public class WeatherUtil {

    /**
     * Convert from kelvin to celsius
     *
     * @param kelvin is value get from api
     * @return celsius
     */
    public static float convertKelvinInCelsius(double kelvin) {
        return (float) (kelvin - 273.15);
    }

    /**
     * Convert from kelvin to Fahrenheit
     *
     * @param kelvin is value get from api
     * @return fahrenheit
     */
    public static float convertKelvinInFahrenheit(double kelvin) {
        return (float) (((kelvin - 273.15) * 9 / 5) + 32);
    }

    /**
     * return the weather based on api response
     *
     * @param weatherIconAPI value
     * @return weather icon
     */
    public static String getWeatherIcon(String weatherIconAPI) {
        String weatherIcon;
        Log.d("weathericon", "getWeatherIcon: "+weatherIconAPI);
        switch (weatherIconAPI) {
            //  clear sky
            case "01d":
                weatherIcon = "sw_01";
                break;
            case "01n":
                weatherIcon = "sw_02";
                break;
            // few clouds
            case "02d":
                weatherIcon = "sw_03";
                break;
            case "02n":
                weatherIcon = "sw_07";
                break;
            // scattered clouds
            case "03d":
                weatherIcon = "sw_04";
                break;
            case "03n":
                weatherIcon = "sw_04";
                break;
            //   broken clouds
            case "04d":
                weatherIcon = "sw_06";
                break;
            case "04n":
                weatherIcon = "sw_06";
                break;
            //    shower rain
            case "09d":
                weatherIcon = "sw_23";
                break;
            case "09n":
                weatherIcon = "sw_23";
                break;
            //    rain
            case "10d":
                weatherIcon = "sw_12";
                break;
            case "10n":
                weatherIcon = "sw_32";
                break;
            //   thunderstorm
            case "11d":
                weatherIcon = "sw_17";
                break;
            case "11n":
                weatherIcon = "sw_37";
                break;
            //   snow
            case "12d":
                weatherIcon = "sw_15";
                break;
            case "12n":
                weatherIcon = "sw_35";
                break;
            //  mist
            case "13d":
                weatherIcon = "sw_10";
                break;
            case "13n":
                weatherIcon = "sw_30";
                break;
            default:
                weatherIcon = "error_icon";
        }
        return weatherIcon;
    }

    /**
     * return float to provided decimal places
     */
    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
