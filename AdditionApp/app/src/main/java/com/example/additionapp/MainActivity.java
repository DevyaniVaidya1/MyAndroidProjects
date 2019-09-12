package com.example.additionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.additionapp.R.id.cb_addition;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
     public void onButtonClick(View veiw){

         EditText etNum1= this.<EditText>findViewById(R.id.et_num1);
         EditText etNum2= this.<EditText>findViewById(R.id.et_num2);
         TextView tvResult= this.<TextView>findViewById(R.id.tv_result);
         Button btnAdd= this.<Button>findViewById(R.id.btn_add);

         // convert string to integer
         int num1= Integer.parseInt(etNum1.getText().toString());
         int num2= Integer.parseInt(etNum2.getText().toString());
        int sum=num1+num2;
        tvResult.setText(Integer.toString(sum));





     }


}
