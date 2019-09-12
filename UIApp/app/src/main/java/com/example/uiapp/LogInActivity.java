package com.example.uiapp;
/**
 * class for Log In page
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LogInActivity extends AppCompatActivity {
    private TextView mAttempt;
    private EditText mUserName;
    private EditText mUserPassword;
    private Button mBtnLogin;
    private int Counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
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

    //validate whether user enter correct id & password
    public void validate(String userName, String userPasssword) {
        // to compare string euals function is use
        if ((userName.equals("Admin") && userPasssword.equals("1234"))) {
            Intent intent = new Intent(LogInActivity.this, InformationActivity.class);
            intent.putExtra("UserName", userName);
            startActivity(intent);
        } else {
            Counter--;
            //creation of toast
            Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Valid UserName " +
                    "and Password", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            mAttempt.setText("Number Of Attempt Remaining :" + String.valueOf(Counter));
            if (Counter == 0) {
                mBtnLogin.setEnabled(false);
            }
        }
    }
}