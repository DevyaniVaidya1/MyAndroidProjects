package com.example.alarmringing;
/**
 * Broadcast Receiver to play ring
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class BroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer;
        Toast.makeText(context, "broadcastreceiver", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(context, R.raw.ring_sms);
        mediaPlayer.start();

    }
}
