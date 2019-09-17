package com.example.animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnimateBoll extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {
    private ImageView mBollImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button Alpha = findViewById(R.id.btn_alpha);
        Button Rotate = findViewById(R.id.btn_rotate);
        Button Traslate = findViewById(R.id.btn_translate);
        Button Scale = findViewById(R.id.btn_scale);
        Button nextscreen=findViewById(R.id.btn_next);
        mBollImage = findViewById(R.id.iv_football);
        Scale.setOnClickListener(this);
        Traslate.setOnClickListener(this);
        Rotate.setOnClickListener(this);
        nextscreen.setOnClickListener(this);
        Alpha.setOnClickListener(this);

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
            case R.id.btn_next:
                 moveToNextScreen();
                break;

        }
    }

    private void moveToNextScreen() {
        startActivity(new Intent(AnimateBoll.this,InterpolatorScreen.class));
    }

    private void alpha() {
        Animation mAlphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mAlphaAnimation.setAnimationListener(this);
        mBollImage.startAnimation(mAlphaAnimation);
    }

    private void scale() {
        Animation mScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        mScaleAnimation.setAnimationListener(this);
        mBollImage.startAnimation(mScaleAnimation);
    }

    private void rotate() {
        Animation mRoatateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mRoatateAnimation.setAnimationListener(this);
        mBollImage.startAnimation(mRoatateAnimation);
    }

    private void translate() {
        Animation mTransalateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        mTransalateAnimation.setAnimationListener(this);
        mBollImage.startAnimation(mTransalateAnimation);
    }


    @Override
    public void onAnimationStart(Animation animation) {
        generateToast("Animation Started");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        generateToast("Animation Ended");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        generateToast("Animation Repeated");
    }

    private void generateToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
