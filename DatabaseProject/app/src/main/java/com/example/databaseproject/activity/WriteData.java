package com.example.databaseproject.activity;
/**
 * CLASS TO ADD DATA INTO DATABASE
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.databaseproject.model.ContactDiary;
import com.example.databaseproject.R;
import com.example.databaseproject.dataset.DatabaseHelper;

public class WriteData extends AppCompatActivity {
    private Button mbtnWriteData;
    private EditText mName;
    private EditText mNumber;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_data);
        initialization();
        mbtnWriteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mName.getText().toString().isEmpty() && mNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Plese enter data !!", Toast.LENGTH_SHORT).show();
                } else {
                    ContactDiary contactDiary = new ContactDiary(mName.getText().toString(), mNumber.getText().toString());
                    mDatabaseHelper.addContact(contactDiary);
                    //Toast.makeText(getApplicationContext(), "data"+ " " + contactDiary.getmContactName() + " " + contactDiary.getmContactNumber(), Toast.LENGTH_LONG).show();
                    alartbox();
                }
            }
        });
    }

    /**
     * THIS FUNCTION IS USE TO DISPLAY DIALOG   TO ASK USER   WHERE TO GO .
     * IF YES THEN GOTO HOME PAGE TO DO NEXT  STEP .
     * IF NO THE  REFRESH THE PAGE .
     */
    private void alartbox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WriteData.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveToNextPage();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(WriteData.this, WriteData.class);
                startActivity(intent);
                Toast.makeText(WriteData.this, " Data added  Sucessfully ", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setIcon(R.drawable.diary);
        alertDialog.show();

    }

    /**
     * FUNCTION TO MOVE NEXT ACTIVITY
     */
    private void moveToNextPage() {
        Toast.makeText(getApplicationContext(), " Data submited Sucessfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(WriteData.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * FUNCTION USE TO INITIALIZE VALUE
     */
    private void initialization() {
        mbtnWriteData = findViewById(R.id.btn_writedata);
        mName = findViewById(R.id.et_witedata_name);
        mNumber = findViewById(R.id.et_writedata_number);
        mDatabaseHelper = new DatabaseHelper(this);
    }
}