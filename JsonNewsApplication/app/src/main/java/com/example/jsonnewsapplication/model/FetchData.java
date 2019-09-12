package com.example.jsonnewsapplication.model;

import android.os.AsyncTask;

import com.example.jsonnewsapplication.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData extends AsyncTask<Integer, String, NewsData> {

       /**
     * Downloading file in background thread
     */
    @Override
    protected NewsData doInBackground(Integer... params) {
        int count = 1;
        try {
            URL url = new URL(Constants.TAG_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            /**
             * CREATE GSON
             */
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            NewsData newsData = gson.fromJson(bufferedReader, NewsData.class);

            return newsData;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     **/
    @Override
    protected void onPostExecute(NewsData newsData) {
        super.onPostExecute(newsData);
    }
}


