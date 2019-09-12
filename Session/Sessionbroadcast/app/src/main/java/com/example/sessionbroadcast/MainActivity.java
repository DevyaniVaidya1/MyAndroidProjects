package com.example.sessionbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private CustomeBroadcastReceiver mcustomeBroadcastReceiver;
private Button mbtnclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcustomeBroadcastReceiver=new  CustomeBroadcastReceiver();
        mbtnclick=findViewById(R.id.btnclick);
        mbtnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PhoneStateBroadcastReceiver.class);
                intent.setAction("com.example.MY_CUSTOME_BROADCAST_RECEIVER");
                Bundle bundle=new Bundle();
                bundle.putString("name","information");
                sendBroadcast( intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter =new IntentFilter("com.example.MY_CUSTOME_BROADCAST_RECEIVER");
        //intentFilter.addAction("com.example.MY_CUSTOME_BROADCAST_RECEIVER");
        //registerReceiver(mcustomeBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
       // IntentFilter intentFilter =new IntentFilter("com.example.MY_CUSTOME_BROADCAST_RECEIVER");
        //intentFilter.addAction("com.example.MY_CUSTOME_BROADCAST_RECEIVER");
      //unregisterReceiver(mcustomeBroadcastReceiver);
    }
}
