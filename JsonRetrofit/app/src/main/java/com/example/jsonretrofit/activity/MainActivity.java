package com.example.jsonretrofit.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonretrofit.R;
import com.example.jsonretrofit.adapter.CustomAdapter;
import com.example.jsonretrofit.interfaces.ApiInterface;
import com.example.jsonretrofit.model.RetroPhoto;
import com.example.jsonretrofit.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter mcustomAdapter;
    private RecyclerView mrecyclerView;
    ProgressDialog mprogressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mprogressDoalog = new ProgressDialog(MainActivity.this);
        mprogressDoalog.setMessage("Loading....");
        mprogressDoalog.show();
        /**
         * Create handle for the RetrofitInstance interface
         * Make request
         */

        ApiInterface service = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();

        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                /**
                 * Handle Response if success
                 */
                mprogressDoalog.dismiss();
                /**
                 *  generateDataList store response in it  in the form of list
                 */
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                /**
                 * Handle Response  failure
                 */
                mprogressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *   get list data  and  set  it adapter
     * @param body  it contains the response from api in the form of list
     */
    private void generateDataList(List<RetroPhoto> body) {
        mrecyclerView = findViewById(R.id.recyclerView);
        mcustomAdapter = new CustomAdapter(this, body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mrecyclerView.setLayoutManager(layoutManager);
        mrecyclerView.setAdapter(mcustomAdapter);
    }
}
