package com.example.sessionbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.widget.Toast;

public class CustomeBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      //  String number= (String) intent.getExtras().get(TelecomManager.EXTRA_INCOMING_CALL_EXTRAS);
        //Toast.makeText(context,"custome broad cast ",Toast.LENGTH_SHORT).show();
        Toast.makeText(context,intent.getStringExtra("name"),Toast.LENGTH_SHORT).show();
    }

}
