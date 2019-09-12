package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * when click on image get the  image from previous activity and set it to this activity #openimage
 */
public class OpenImage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_image);
        ImageView OpenImg;
        OpenImg=findViewById(R.id.openImg);
//Bundle generally use for passing data between various Activities.It depends on you what type of
// values you want to pass but bundle can hold all types of values and pass to the new activity.
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            //get the  image path  passed from previous activity
            int ResId=bundle.getInt("IMAGE_PATH");
            //set image path to image view
            OpenImg.setImageResource(ResId);
        }
    }
}
