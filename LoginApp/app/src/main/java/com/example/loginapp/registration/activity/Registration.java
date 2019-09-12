package com.example.loginapp.registration.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.MainActivity;
import com.example.loginapp.R;
import com.example.loginapp.registration.presenter.RegistrationPresenter;

public class Registration extends AppCompatActivity implements IRegistration {

    private EditText mName;
    private EditText mEmail;
    private EditText mPasssword;
    private Button mBtnSignIn;
    private RegistrationPresenter mRegistrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mName = findViewById(R.id.et_name);
        mEmail = findViewById(R.id.et_email);
        mPasssword = findViewById(R.id.etPasssword);
        mBtnSignIn = findViewById(R.id.btn_register);
       mRegistrationPresenter=new RegistrationPresenter(this);
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    //Upload data to database
                }
            }
        });

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });
    }

    @Override
    public Boolean validate() {
        boolean result = false;
        String Name = mName.getText().toString();
        String Email = mEmail.getText().toString();
        String Password = mPasssword.getText().toString();


        if (Name.isEmpty() && Email.isEmpty() && Password.isEmpty()) {
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
