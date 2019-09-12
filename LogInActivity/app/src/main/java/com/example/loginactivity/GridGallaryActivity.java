package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static com.example.loginactivity.R.drawable.user;

/**
 * class for grid view to display imgaes like gallary
 */
public class GridGallaryActivity extends AppCompatActivity implements View.OnClickListener {
   //Declare variable
    ImageView mImg1;
    ImageView mImg2;
    ImageView mImg3;
    ImageView mImg4;
    ImageView mImg5;
    ImageView mImg6;
    ImageView mImg7;
    ImageView mImg8;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_gallary);

        //get id of each view  nd set listener
        mImg1=findViewById(R.id.img1);
        mImg1.setOnClickListener(this);

        mImg2=findViewById(R.id.img2);
        mImg2.setOnClickListener(this);

        mImg3=findViewById(R.id.img3);
        mImg3.setOnClickListener(this);

        mImg4=findViewById(R.id.img4);
        mImg4.setOnClickListener(this);

        mImg5=findViewById(R.id.img5);
        mImg5.setOnClickListener(this);

        mImg6=findViewById(R.id.img6);
        mImg6.setOnClickListener(this);

        mImg7=findViewById(R.id.img7);
        mImg7.setOnClickListener(this);

        mImg8=findViewById(R.id.img8);
        mImg8.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
// get id of view compare it nd perform operation accordingly
         switch (view.getId())
        {
            case R.id.img1 :
                Intent intent1 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent1.putExtra("IMAGE_PATH", R.drawable.astronomy_black_wallpaper);
                startActivity(intent1);
                break;
            case R.id.img2:
                Intent intent2 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent2.putExtra("IMAGE_PATH", R.drawable.bit);
                startActivity(intent2);
                break;
            case R.id.img3 :
                Intent intent3 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent3.putExtra("IMAGE_PATH", R.drawable.redcirclespace);
                startActivity(intent3);
                break;
            case R.id.img4 :
                Intent intent4 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent4.putExtra("IMAGE_PATH", R.drawable.astronomy_explosion);
                startActivity(intent4);
                break;
            case R.id.img5:
                Intent intent5 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent5.putExtra("IMAGE_PATH", R.drawable.astronomy_earth);
                startActivity(intent5);
                break;
            case R.id.img6:
                Intent intent6 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent6.putExtra("IMAGE_PATH", R.drawable.astronomy_backlit_blue);
                startActivity(intent6);
                break;
            case R.id.img7:
                Intent intent7 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent7.putExtra("IMAGE_PATH", R.drawable.artificial_intelligence_astronomy_machine);
                startActivity(intent7);
                break;
            case R.id.img8 :
                Intent intent8 = new Intent(GridGallaryActivity.this, OpenImage.class);
                //pass img path to next activity
                intent8.putExtra("IMAGE_PATH", R.drawable.astronomy2);
                startActivity(intent8);
                break;
        }

    }
}




