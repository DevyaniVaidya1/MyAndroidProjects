package com.example.slider.views.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.slider.R;
import com.example.slider.adapter.SliderAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager mviewPager;
    private SliderAdapter msliderAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mviewPager = (ViewPager) findViewById(R.id.viewpager);
        msliderAdaper = new SliderAdapter(this);
    }


//
//    private void retroDAtaFetch(){
//     APIInterface service = RetrofitClient.getRetrofitInstance().create(APIInterface.class);
//      //  Call<List<RetroPhoto>> call = service.getAllPhotos();
//    }
}
