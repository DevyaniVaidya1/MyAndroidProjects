package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class InterpolatorScreen extends AppCompatActivity  {
    private ImageView mLoadImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        Button btnLoad=findViewById(R.id.btn_load);
        mLoadImg=findViewById(R.id.iv_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.interpolator_anim);
                mLoadImg.startAnimation(animation);
            }
        });
    }
}
