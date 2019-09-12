package com.example.sampleasset;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class ImageActivity extends AppCompatActivity {
    private ImageView mImage1;
    private ImageView mImage2;
    private ImageView mImage3;
    private AssetManager massetManager;
    private InputStream minputStream;
    private Drawable mdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_image);

        massetManager = getAssets();
        mImage1 = (ImageView) findViewById(R.id.iv_image1);

        try {
            minputStream = massetManager.open("cycle.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mdraw = Drawable.createFromStream(this.minputStream,null);
        mImage1.setImageDrawable(mdraw);
    }

}
