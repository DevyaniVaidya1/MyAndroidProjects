package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_table);
        Button BtnViewGallary;
        BtnViewGallary = findViewById(R.id.btn_viewgallary);
        BtnViewGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondTableActivity.this, GridGallaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
