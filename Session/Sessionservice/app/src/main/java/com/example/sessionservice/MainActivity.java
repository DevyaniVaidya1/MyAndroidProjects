package com.example.sessionservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {
    private Button mbtnStart;
    private Button mbtnStop;
    private TextView mtext;
    private MediaPlayer mMmdiaPlayer;
    String action = "com.example.sessionservice.INTENTSERVICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnStart = findViewById(R.id.btn_start);
        mbtnStop = findViewById(R.id.btn_stop);
        mtext=findViewById(R.id.textdisplay);
        //listener for start
        mbtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ServiceClass.class);

//                Intent intent1=new Intent(MainActivity.this,ServiceClass.class);
//                startService(intent1);
//                mMmdiaPlayer= MediaPlayer.create(MainActivity.this,R.raw.i_am_alive);
//                mMmdiaPlayer.start();

                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                startService(intent);
//                sendBroadcast( intent);


            }
        });

        //listener for stop
        mbtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(MainActivity.this,ServiceClass.class);
                Intent intent2 = new Intent(MainActivity.this, ServiceClass.class);
                mMmdiaPlayer.stop();
                // stopService(intent);
                stopService(intent2);
            }
        });

    }

    //broadcast Receiver
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          String Key=(String)intent.getStringExtra("key");
          mtext.setText(Key);
            Log.d("BrodcastReceiver", "onReceive: BroadcastReceiver");
        }
    };

    @Override
    protected void onResume() {
        IntentFilter intentFilter = new IntentFilter(action);
        intentFilter.addAction(action);
        registerReceiver(broadcastReceiver, intentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter);
        super.onResume();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(broadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onStop();
    }
}
