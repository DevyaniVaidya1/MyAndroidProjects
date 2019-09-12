package com.example.masternewsapplication.model;

import android.os.AsyncTask;

import com.example.masternewsapplication.IDataResponderListener;
import com.example.masternewsapplication.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * class which contain perform in background thread  and set  data to UI thread .
 */
public class FetchDataFromServer extends AsyncTask<Void, Void, NewsData> {

private IDataResponderListener mDataResponderListener;

    public FetchDataFromServer(IDataResponderListener mDataResponderListener) {
        this.mDataResponderListener = mDataResponderListener;
    }

    @Override
    protected void onPreExecute() {
        if (mDataResponderListener != null) {
            mDataResponderListener.onPreExecute();
        }

    }

    @Override
    protected NewsData doInBackground(Void... voids) {

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

    @Override
    protected void onPostExecute(NewsData newsData) {
        super.onPostExecute(newsData);
    }
}


