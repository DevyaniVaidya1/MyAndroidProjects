package com.example.challengeassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {
    private EditText mEmail;
    private EditText mName;
    private EditText mPhoneNumber;
    private EditText mPassword;
    private Button mbtnSave;
    private DatabaseHelper mdatabaseHelper;
    private static final String TAG = "Registration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialization();
        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDatatoSaveInDataBase();
            }
        });

    }
private void getDatatoSaveInDataBase(){
    UserClass userClass = new UserClass(mEmail.getText().toString(), mName.getText().toString(),
            mPhoneNumber.getText().toString(), mPassword.getText().toString());
    mdatabaseHelper.addUser(userClass);
    ArrayList<UserClass> datalist = mdatabaseHelper.getAllData();
    for (int i = 0; i < datalist.size(); i++) {
        UserClass userData = datalist.get(i);
        Log.d(TAG, "onClick: " + userData.getName() + " " + userData.getPassword() + " " +
                userData.getEmail() + " " + userData.getPhoneNumber());
    }
    Toast.makeText(getApplicationContext(), "Data Submited to databse", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(Registration.this, LogIn.class));
}
    private void initialization() {
        mEmail = findViewById(R.id.et_email);
        mName = findViewById(R.id.et_name);
        mPhoneNumber = findViewById(R.id.et_phonenumber);
        mPassword = findViewById(R.id.et_password);
        mbtnSave = findViewById(R.id.btn_save);
        mdatabaseHelper = new DatabaseHelper(this);
    }


}
