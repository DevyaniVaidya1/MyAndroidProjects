package com.example.customedialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    //Button button;
    FloatingActionButton floatingActionButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                // custom dialog
//                final Dialog dialog = new Dialog(getApplicationContext());
//                dialog.setContentView(R.layout.toadd_data);
//                dialog.setTitle("Add Ingredient");

                final AlertDialog dialogBuilder = new AlertDialog.Builder(getApplicationContext()).create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.toadd_data, null);

                final EditText userName = dialogView.findViewById(R.id.etname);
                final EditText userContact = dialogView.findViewById(R.id.etnumber);
                Button submit =  dialogView.findViewById(R.id.dialogButtonSubmit);
                Button delete =  dialogView.findViewById(R.id.btndelete);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // DO SOMETHINGS
                        dialogBuilder.dismiss();
                    }
                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();


            }
        });
    }
}
