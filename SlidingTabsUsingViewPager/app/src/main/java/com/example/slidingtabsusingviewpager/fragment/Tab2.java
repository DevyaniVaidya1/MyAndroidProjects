package com.example.slidingtabsusingviewpager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.slidingtabsusingviewpager.R;


public class Tab2 extends Fragment implements View.OnClickListener {
    private Button btnNotification;
    private static final String CHANNEL_ID = "pernal notification";
    private static final int NOTIFICATION_ID = 001;
    private static final String TAG = "TAB2";

    public Tab2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        btnNotification = view.findViewById(R.id.notification_button);
        btnNotification.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notification_button:
                Log.d(TAG, "onClick: ");
                callNotification(view);
                break;
            default:
        }
    }

    /**
     * method to show notification
     *
     * @param view view
     */
    private void callNotification(View view) {
        Log.d(TAG, "callNotification: ");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle("Simple Notification")
                .setContentText("This is a  Simple Notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}