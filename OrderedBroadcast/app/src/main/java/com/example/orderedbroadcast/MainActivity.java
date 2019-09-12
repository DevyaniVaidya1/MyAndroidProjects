package com.example.orderedbroadcast;
/**
 * ordered  broadcast receiver
 */

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CustomeBroadcastReceiver1 mcustomeBroadcastReceiver1;
    private CustomeBroadcastReceiver2 mcustomeBroadcastReceiver2;
    private String actionName = "com.example.orderedbroadcast.ORDERED_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcustomeBroadcastReceiver1 = new CustomeBroadcastReceiver1();
        mcustomeBroadcastReceiver2 = new CustomeBroadcastReceiver2();

        Button buttonSendBroadcast = findViewById(R.id.btn_sendbroadcast);
        buttonSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(actionName);
                sendOrderedBroadcast(intent, null);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter1 = new IntentFilter(actionName);
        intentFilter1.addAction(actionName);
        intentFilter1.setPriority(2);
        IntentFilter intentFilter2 = new IntentFilter(actionName);
        intentFilter2.addAction(actionName);
        intentFilter2.setPriority(1);
        registerReceiver(mcustomeBroadcastReceiver1, intentFilter1);
        registerReceiver(mcustomeBroadcastReceiver2, intentFilter2);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mcustomeBroadcastReceiver1);
        unregisterReceiver(mcustomeBroadcastReceiver2);

    }
}
