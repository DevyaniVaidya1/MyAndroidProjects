package com.example.martinruiz.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.martinruiz.myapplication.R;
import com.example.martinruiz.myapplication.activities.fragments.SettingFragment;

public class SettingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        if (findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null)
            return;

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,new SettingFragment())
                    .commit();
        }
    }
}
