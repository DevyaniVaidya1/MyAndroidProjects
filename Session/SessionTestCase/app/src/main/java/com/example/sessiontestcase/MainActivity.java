package com.example.sessiontestcase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText mInput;
private Button mbtnSubmit;
private TextView mDisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInput=findViewById(R.id.et_text_activitymain);
        mbtnSubmit=findViewById(R.id.btn_submit);
        mDisplayText=findViewById(R.id.tv_diplaytext);
        mbtnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mDisplayText.setText(mInput.getText().toString());
    }
}
