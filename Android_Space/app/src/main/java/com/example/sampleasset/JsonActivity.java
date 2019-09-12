package com.example.sampleasset;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class JsonActivity extends AppCompatActivity {

    private TextView mjson_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_json);

        mjson_txt = (TextView)findViewById(R.id.tv_json_txt);

        String json_txt;

        try {
            InputStream inputStream = getAssets().open("abc.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            json_txt = new String(buffer, "UTF-8");

            mjson_txt.setText(json_txt);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
