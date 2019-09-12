package com.example.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * this class is use to redirect to second activity
 */
public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    Button btnSendMessage ;
    Button btnDial;
    Button btnOpenCamera;
    Button btnOpenDocument;

    /**
     * introduction to android life cycle
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendMessage= findViewById(R.id.btn_sendMessage);
        btnDial= findViewById(R.id.btn_dial);
        btnOpenCamera=findViewById(R.id.btn_camera);


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText= findViewById(R.id.et_message);
                String message=editText.getText().toString();  // get text msg

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("EXTRA_MESSAGE",message);//send text to new window
                startActivity(intent);

            }

        });
    btnDial.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            startActivity(intent);
        }
    });


        btnOpenDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivity(intent);
            }
        });


    }



// cycle completed

    /**
     * redirecting code to next window
     * @param view editable text
     */



}
