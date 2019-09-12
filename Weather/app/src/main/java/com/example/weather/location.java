package com.example.weather;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class location extends AppCompatActivity {
    EditText s1;
    Button b1;
    SQLiteDatabase db1;

    public SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
       s1=(EditText)findViewById(R.id.search_location);
       b1=(Button)findViewById(R.id.submit);

       b1.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("WrongConstant")
           @Override
           public void onClick(View v) {
               try {
                   String loc=s1.getText().toString();
                   SharedPreferences.Editor editor = sharedpreferences.edit();
                   editor.putString("LOC", loc);
                   editor.commit();

                   db1 = openOrCreateDatabase("Location",SQLiteDatabase.CREATE_IF_NECESSARY, null);
                   db1.execSQL("create table IF NOT EXISTS City(Name varchaar(90))");

                   ContentValues cv1=new ContentValues();
                   cv1.put("Name",loc);
                   db1.insert("City",null,cv1);
               }
               catch (Exception e){

                   Toast.makeText(location.this,""+e,Toast.LENGTH_LONG).show();

               }
               Intent i1=new Intent(location.this,home.class);
               startActivity(i1);


           }
       });
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


    }
}
