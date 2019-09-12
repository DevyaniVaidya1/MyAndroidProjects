package com.example.contentproviders.views.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.contentproviders.R;
import com.example.contentproviders.loaders.SongsLoader;
import com.example.contentproviders.models.Song;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> mSongsList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {"android.permission.WRITE_EXTERNAL_STORAGE"},
                    1);
        }

        new LoadAllSongs().execute();

    }

    class LoadAllSongs extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            mSongsList = SongsLoader.getInstance()
                    .getAllAudioFiles(getApplicationContext());
            return null;
        }
    }
}
