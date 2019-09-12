package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * class for Log In page
 */
public class MainActivity extends AppCompatActivity {
    TextView mAttempt;
    EditText mUserName;
    EditText mUserPassword;
    Button mBtnLogin;
    private int Counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get id's
        mUserName = findViewById(R.id.et_UserName);
        mUserPassword = findViewById(R.id.et_Password);
        mBtnLogin = findViewById(R.id.btn_LogIn);
        mAttempt = findViewById(R.id.tv_attempt);
        //code when  user click on button
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(mUserName.getText().toString(), mUserPassword.getText().toString());
            }
        });
    }
    public void validate(String userName, String userPasssword) {
        // to compare string euals function is use
        if ((userName.equals("Admin") && userPasssword.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this, SecondTableActivity.class);
            startActivity(intent);
        } else {
            Counter--;
            mAttempt.setText("Number Of Attempt Remaining :" + String.valueOf(Counter));
            if (Counter == 0) {
                mBtnLogin.setEnabled(false);
            }
        }

    priv

}
