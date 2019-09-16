package com.example.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimateBoll extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBollImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button mAlpha = findViewById(R.id.btn_alpha);
        Button mRotate = findViewById(R.id.btn_rotate);
        Button mTraslate = findViewById(R.id.btn_translate);
        Button mScale = findViewById(R.id.btn_scale);
        mBollImage = findViewById(R.id.iv_football);
        mScale.setOnClickListener(this);
        mTraslate.setOnClickListener(this);
        mRotate.setOnClickListener(this);
        mAlpha.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha:
                alpha();
                break;
            case R.id.btn_rotate:
                rotate();
                break;
            case R.id.btn_scale:
                scale();
                break;
            case R.id.btn_translate:
                translate();
                break;

        }
    }

    private void alpha() {
        Animation mAlphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mBollImage.startAnimation(mAlphaAnimation);
    }

    private void scale() {
        Animation mScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        mBollImage.startAnimation(mScaleAnimation);
    }

    private void rotate() {
        Animation mRoatateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mBollImage.startAnimation(mRoatateAnimation);
    }

    private void translate() {
        Animation mTransalateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        mBollImage.startAnimation(mTransalateAnimation);
    }


}
