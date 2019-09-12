package com.example.orderedbroadcast;
/**
 * broadcast receiver to show toast when call
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomeBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"CustomeBroadcastReceiver2",Toast.LENGTH_LONG).show();
    }
}
