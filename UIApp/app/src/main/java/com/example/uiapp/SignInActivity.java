package com.example.uiapp;
/**
 * sign in  page information  and send information to next activity
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import static android.text.TextUtils.isEmpty;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mName;
    private EditText mEmail;
    private EditText mPass;
    private EditText mAbout;
    private Button mSignin;

    private Button mbtnDatePicker;
    private Button mbtnTimePicker;
    private TextView mtextviewShowDate;
    private TextView mtextviewShowTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mName = findViewById(R.id.etUserName);
        mEmail = findViewById(R.id.etUserEmail);
        mPass = findViewById(R.id.etPassword);
        mAbout = findViewById(R.id.etAbout);
        mSignin = findViewById(R.id.btnSignUp);
        mbtnDatePicker = findViewById(R.id.btn_datepicker);
        mbtnTimePicker = findViewById(R.id.btn_timepicker);
        mtextviewShowDate = findViewById(R.id.txtDate);
        mtextviewShowTime = findViewById(R.id.txtTime);
        //get value of each view
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();
        String about = mAbout.getText().toString();
        String date = mtextviewShowDate.getText().toString();
        String time = mbtnTimePicker.getText().toString();
        // call to listener
        mbtnDatePicker.setOnClickListener(this);
        mbtnTimePicker.setOnClickListener(this);
        mSignin.setOnClickListener(this);
    }

    //lister of button
    @Override
    public void onClick(View view) {
        // for date picker
        switch (view.getId()) {
            case R.id.btn_datepicker:
                // Get Current Date
                final Calendar calenderDate = Calendar.getInstance();
                mYear = calenderDate.get(Calendar.YEAR);
                mMonth = calenderDate.get(Calendar.MONTH);
                mDay = calenderDate.get(Calendar.DAY_OF_MONTH);
                //Launch  date picker
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            //
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //set text
                                mtextviewShowDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;

            case R.id.btn_timepicker:
                // Get Current Time
                final Calendar calendarTime = Calendar.getInstance();
                mHour = calendarTime.get(Calendar.HOUR_OF_DAY);
                mMinute = calendarTime.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            //set listener
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                mtextviewShowTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

            case R.id.btnSignUp:
                if (isEmpty(mName.getText().toString()) || isEmpty(mAbout.getText().toString()) ||
                        isEmpty(mPass.getText().toString()) || isEmpty(mtextviewShowDate.getText().toString())
                        || isEmpty(mtextviewShowTime.getText().toString()) || isEmpty(mEmail.getText().toString())) {
                    //toast
                    Toast toast = Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    Intent intent = new Intent(SignInActivity.this, InformationActivity.class);
                    intent.putExtra("UserName", mName.getText().toString());
                    intent.putExtra("UserEmail", mEmail.getText().toString());
                    intent.putExtra("UserAbout", mAbout.getText().toString());
                    intent.putExtra("Date", mtextviewShowDate.getText().toString());
                    intent.putExtra("Time", mtextviewShowTime.getText().toString());
                    startActivity(intent);
                    break;
                }
        }
    }
}
