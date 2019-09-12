package com.example.rrtrofitusingapiquery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rrtrofitusingapiquery.api.GetMoviesInterface;
import com.example.rrtrofitusingapiquery.api.RetrofitClient;
import com.example.rrtrofitusingapiquery.constants.Constants;
import com.example.rrtrofitusingapiquery.model.Example;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private TextView mdisplayName;
private TextView mlink;
private TextView muserType;
private List<Example> mexampleList;
public static final String TAG="MainActivty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mexampleList= new ArrayList<>();


    }

    private  void callToApi(){
        /** Create handle for the RetrofitInstance interface*/
        GetMoviesInterface service = RetrofitClient.getRetrofitInstance().create(GetMoviesInterface.class);
        Call<List<Example>> call = service.getAllData(Constants.ORDER,Constants.SORT,Constants.SITE);
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                Toast.makeText(MainActivity.this, "response"+response.body().size(),
                        Toast.LENGTH_SHORT).show();


//                mexampleList = response.body();
//                Toast.makeText(getApplicationContext(), String.valueOf(response.body().size()) +
//                        "response size " + mexampleList.size(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onResponse: " + mexampleList.get(0).get);
//                Log.d(TAG, "size of array list " + mexampleList.size());

            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
