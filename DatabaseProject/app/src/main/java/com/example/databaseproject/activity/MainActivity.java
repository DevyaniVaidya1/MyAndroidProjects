package com.example.databaseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.databaseproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonWrite;
    private Button mButtonView;
    private Button mButtonDelete;
    private Button mButtonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonWrite = findViewById(R.id.btn_main_write);
        mButtonView = findViewById(R.id.btn_main_view);
        mButtonUpdate = findViewById(R.id.btn_main_update);
        mButtonDelete = findViewById(R.id.btn_main_delete);

        mButtonView.setOnClickListener(this);
        mButtonUpdate.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
        mButtonWrite.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_write:
                Intent intent = new Intent(MainActivity.this, WriteData.class);
                startActivity(intent);
                break;
            case R.id.btn_main_view:
                Intent intent1 = new Intent(MainActivity.this, RecyclerLayout.class);
                startActivity(intent1);
                break;
            case R.id.btn_main_update:
                Intent intent2 = new Intent(MainActivity.this, UpdateData.class);
                startActivity(intent2);
                break;

            case R.id.btn_main_delete:
                Intent intent3 = new Intent(MainActivity.this, DeleteData.class);
                startActivity(intent3);
                break;


            default:
                Toast.makeText(getApplicationContext(), "Press Button", Toast.LENGTH_SHORT).show();
        }
    }
}
