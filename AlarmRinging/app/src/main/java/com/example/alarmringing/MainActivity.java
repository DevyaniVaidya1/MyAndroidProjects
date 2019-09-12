package com.example.alarmringing;
/**
 * class to register and unregister broadcast receiver .
 * class which  create alarm  nd call broadcast receiver  to play ring
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private AlarmManager malarmManager;
    private PendingIntent mAlarmPendingIntent;
    private String mAction = "com.example.alarmringing.ALARM";
    private BroadcastReceiver mbroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttoncode();
    }

    /**
     * declaration and code  of view button  and onclick listener
     */
    private void buttoncode() {
        Button btnRingAlarm;
        Button btnStopRingAlarm;
        mbroadcastReceiver = new BroadCastReceiver();
        btnRingAlarm = findViewById(R.id.btn_ringalarm);
        btnStopRingAlarm = findViewById(R.id.btn_stopalarm);
        btnRingAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAlarm();
            }
        });
        btnStopRingAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                malarmManager.cancel(mAlarmPendingIntent);
                Toast.makeText(getApplicationContext(), "Alarm stoped", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * function to create alarm which ring after 30 second
     */
    private void startAlarm() {
        Intent intent;
        Toast.makeText(getApplicationContext(), "Alarm is set", Toast.LENGTH_SHORT).show();
        malarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        intent = new Intent(this, BroadCastReceiver.class);
        mAlarmPendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        malarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3 * 1000, mAlarmPendingIntent);
    }

    @Override
    protected void onResume() {
        IntentFilter intentFilter = new IntentFilter(mAction);
        intentFilter.addAction(mAction);
        registerReceiver(mbroadcastReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mbroadcastReceiver);
        super.onStop();
    }

}

