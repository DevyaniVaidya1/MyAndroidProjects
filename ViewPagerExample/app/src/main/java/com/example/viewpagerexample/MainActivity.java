package com.example.viewpagerexample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpagerexample.api.GetMoviesInterface;
import com.example.viewpagerexample.api.RetrofitClient;
import com.example.viewpagerexample.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    private String[] imageUrls = new String[]{
            "https://www.livemint.com/science/news/chandrayaan-2-isro-hopeful-tries-to-restore-link-with-vikram-latest-updates-1568017677858.html",
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        callToApi();

        //view pager
        mViewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, imageUrls);
        mViewPager.setAdapter(viewPagerAdapter);
    }
    private void callToApi(){
        /** Create handle for the RetrofitInstance interface*/
        GetMoviesInterface service = RetrofitClient.getRetrofitInstance().create(GetMoviesInterface.class);
        Call<List<Movie>> call=service.getAllData();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                Toast.makeText(MainActivity.this, String.valueOf(response.body().size()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
            }
        });



    }


}
