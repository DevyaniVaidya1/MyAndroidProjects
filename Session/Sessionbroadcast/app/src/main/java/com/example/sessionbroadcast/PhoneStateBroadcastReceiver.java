package com.example.sessionbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneStateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String number= (String) intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if(number==null) {
            Toast.makeText(context, number + "Calling", Toast.LENGTH_SHORT).show();
        }
    }
}
