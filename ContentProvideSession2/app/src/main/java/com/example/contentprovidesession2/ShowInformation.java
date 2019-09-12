package com.example.contentprovidesession2;

import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowInformation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);
        animation();
        declarationAndInitialization();
    }

    /**
     * GET ID OF EACH VIEW,GET DATA OF ANOTHER ACTIVITY  AND SET IT VIEW
     */
    private void declarationAndInitialization() {
        TextView mTextName;
        TextView mTextNumber;
        TextView mTextLogo;
        mTextLogo = findViewById(R.id.imagecontact);
        mTextName = findViewById(R.id.textdisplayname);
        mTextNumber = findViewById(R.id.textdisplaynumber);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String number = bundle.getString("Number");
        String logoText = bundle.getString("LogoLetter");
        Log.d("values", "onCreate: " + name + number + logoText);
        mTextNumber.setText("Number:" + number);
        mTextName.setText("Name: " + name);
        mTextLogo.setText(logoText);

    }

    /**
     * CODE TO ANIMAT TEXTVIEW
     */
    private void animation() {
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
    }


}
