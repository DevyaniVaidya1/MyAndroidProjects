package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.loginapp.R.id.tv_dispay_msg;

public class MainActivity extends AppCompatActivity {
    //variable declare here
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int Counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setID();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate(Name.getText().toString(), Password.getText().toString());

            }
        });

    }

    public void validate(String userName, String userPasssword) {

        if ((userName.equals("Admin")&& userPasssword.equals("1234"))) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

        } else {
            int i = Counter--;
            Info.setText("Number Of Attempt Remaining :" + String.valueOf(i));
            if (Counter == 0) {
                Login.setEnabled(false);
            }
        }

    }
    /**
     * get id of all variable
     */
    private void setID(){
        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPasssword);
        Login = findViewById(R.id.btnLogin);
        Info = findViewById(R.id.tvInfo);
        Info.setText("Number of attemt reamaining :5");
    }
    }


