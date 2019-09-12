package com.example.uiapp;
/**
 * class to make decision based on which radio button selected by user
 * radio log in  button redirect to login page
 * radio log in  button redirect to  sign in page
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Button mButnLogSignIn;
    private RadioGroup mRadioGroup;
    private RadioButton mRadionbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiocheaking();
        //button listner
        mButnLogSignIn = findViewById(R.id.btnLogSignIn);
        //Button text disable
        mButnLogSignIn.setEnabled(false);
        mButnLogSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mButnLogSignIn.getText().toString();
                if (text.equals("Log In")) {
                    startActivity(new Intent(MainActivity.this, LogInActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));

                }
            }
        });
    }

    public void radiocheaking() {
        mRadioGroup = findViewById(R.id.rgRadioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    //listener of radio buuton
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        //dynamically select which btn is checked
        int RadioBtnId = radioGroup.getCheckedRadioButtonId();
        //find id od radio btn
        mRadionbtn = findViewById(RadioBtnId);
        //get text of radio btn
        String RadioText = mRadionbtn.getText().toString();
        if (RadioText.equalsIgnoreCase("Log In")) {
            mButnLogSignIn.setText("Log In");
            mButnLogSignIn.setEnabled(true);
        } else {
            mButnLogSignIn.setText("Sign In");
            mButnLogSignIn.setEnabled(true);
        }
    }
}
