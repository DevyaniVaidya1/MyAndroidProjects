package com.example.sessionservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("intentservice");

    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("intentservice", "onHandleIntent: ");
        Intent intent1 = new Intent();
        intent1.setAction("com.example.sessionservice.INTENTSERVICE");
        intent1.putExtra("key","Going ");
//                sendBroadcast( intent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("intentservice", "onCreate: ");
    }

    @Override
    public void onDestroy() {
        Log.d("intentservice", "onDistroy: ");
        super.onDestroy();
    }
}
