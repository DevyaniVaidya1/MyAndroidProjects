package com.example.challengeassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private EditText medittextUserName;
    private EditText medittextPassword;
    private Button mbtnSubmit;
    private TextView mtextlink;
    private DatabaseHelper mdatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        mbtnSubmit.setOnClickListener(this);
        mtextlink.setOnClickListener(this);
    }


    private void initialization() {
        mbtnSubmit = findViewById(R.id.btn_submit);
        medittextUserName = findViewById(R.id.et_username);
        medittextPassword = findViewById(R.id.et_userpassword);
        mtextlink = findViewById(R.id.textregisterlink);
        mdatabaseHelper = new DatabaseHelper(this);
    }


    private void validation(String name, String password) {
        Log.d("LogIn", "validation: "+name);
        ArrayList<UserClass> userData = mdatabaseHelper.getLogIndData();
        for (int i = 0; i < userData.size(); i++) {
            UserClass data = userData.get(i);
            if (data.getName().equalsIgnoreCase(name) && data.getPassword().equalsIgnoreCase(password)) {
                Toast.makeText(getApplicationContext(), " Log In sucessfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogIn.this,RecyclerView.class));
            }else{
                Toast.makeText(getApplicationContext(), "Wrong UserName or Password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                String name = medittextUserName.getText().toString();
                String password = medittextPassword.getText().toString();
                validation(name, password);
                break;

            case R.id.textregisterlink:
                startActivity(new Intent(LogIn.this, Registration.class));
                break;
        }
    }
}
