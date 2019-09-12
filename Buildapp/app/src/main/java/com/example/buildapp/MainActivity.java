package com.example.buildapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int seconds =30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button  btnAction= this.<Button>findViewById(R.id.btn1);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Custom code
                 Intent i = new Intent(MainActivity.this,GameActivity.class);
                 startActivity(i);
            }

    private void timer()
    {
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (seconds>0)
                {
                    seconds--;
                    handler.postDelayed(this,1000);
                }
                else
                {
                    //Timer complete
                }
            }
        });
    }


        });
    }
}
