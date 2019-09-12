package com.example.uiapp;
/**
 * class to display random image and convert color image to black and white
 */

import android.content.DialogInterface;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ImageViewActivity extends AppCompatActivity {
    private  ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        //btn declare
        Button btnConvertImg;
        //find id
        mImageView = findViewById(R.id.openImg);
        btnConvertImg = findViewById(R.id.btnImgConvert);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //get the  image path  passed from previous activity
            int ResId = bundle.getInt("ImageID");
            //set image path to image view
            mImageView.setImageDrawable(getResources().getDrawable(ResId));
        }
        //set listener
        btnConvertImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               open();
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                mImageView.setColorFilter(filter);
            }
        });
    }

    public void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(ImageViewActivity.this,
                                "You clicked yes button",Toast.LENGTH_LONG).show();
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
