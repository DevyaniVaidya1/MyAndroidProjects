package com.example.jsonvollyexample.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsonvollyexample.R;
import com.example.jsonvollyexample.adapter.UserAdapter;
import com.example.jsonvollyexample.constants.Constatnts;
import com.example.jsonvollyexample.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "UUUUUUUUUUUUUUUUUUUUUU";

    //public static final String TAG_URI="https://api.github.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
        * INITIALIZE AND RECYCLER VIEW
         */
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        StringRequest stringRequest = new StringRequest(
                Constatnts.TAG_URI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /**
                         * SUCCESS CALL BACK
                         */
                        Log.d(TAG, "onResponse: " + response);
                        /**
                         * CREATE GSON
                         */
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        User[] users = gson.fromJson(response, User[].class);
                        /**
                         * CONNECT RECYCLER WITH ADAPTER
                         */
                        recyclerView.setAdapter(new UserAdapter(MainActivity.this, users));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * ERROR CALL
                 */
                Log.d(TAG, "onErrorResponse: " + error);
                Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        }
        );
        /**
         * DO NOT FORGET TO ADD PERMISSION IN MANIFEST
         */
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
