package com.example.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

// fetch data from window one nd display it
        Intent intent =getIntent();
        String message=intent.getStringExtra("EXTRA_MESSAGE");
        textview  = (TextView)findViewById(R.id.tv_display);
        textview.setText(message);



    }
    public void onClickNext(View view){
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }
}
