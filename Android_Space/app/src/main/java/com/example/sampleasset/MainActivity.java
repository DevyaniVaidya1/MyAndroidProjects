package com.example.sampleasset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button btn_1 = (Button) findViewById(R.id.btn_image);
//        btn_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this,ImageActivity.class);
//                startActivity(i);
//            }
//        });
//
//        Button btn_2 = (Button) findViewById(R.id.btn_json);
//        btn_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this,JsonActivity.class);
//                startActivity(i);
//            }
//        });
    }

    public void onClickBtn(View view){
        switch (view.getId()){
            case R.id.btn_image:  Intent i = new Intent(MainActivity.this,ImageActivity.class);
               startActivity(i);
               break;

            case R.id.btn_json :
                Intent intent = new Intent(MainActivity.this,JsonActivity.class);
                startActivity(intent);
                break;
        }
    }
}
