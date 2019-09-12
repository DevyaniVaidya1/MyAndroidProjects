package com.example.fragmentdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    private TestFragment mtestFragment;
    private Button mbtnOpenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnOpenFragment = findViewById(R.id.btn_open_fragment);
        mtestFragment = new TestFragment();
        frameLayout = findViewById(R.id.frame_container);
        if (findViewById(R.id.frame_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            mbtnOpenFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // frameLayout.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, mtestFragment)
                            .addToBackStack(null).commit();
                }
            });
        }

//        if (findViewById(R.id.frame2) != null) {
//            if (savedInstanceState != null) {
//                return;
//            }
//
//            Button addfragment = findViewById(R.id.btn_open_fragment2);
//            addfragment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Fragment testFragment =new TestFragment();
//                    getSupportFragmentManager().beginTransaction()
//                            .add(R.id.frame2, testFragment).commit();
//                }
//            });
//        }

    }


}
