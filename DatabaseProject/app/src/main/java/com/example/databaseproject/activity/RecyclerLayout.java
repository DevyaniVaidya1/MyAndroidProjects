package com.example.databaseproject.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.model.ContactDiary;
import com.example.databaseproject.model.MyAdapter;
import com.example.databaseproject.R;
import com.example.databaseproject.dataset.DatabaseHelper;

import java.util.ArrayList;

public class RecyclerLayout extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<ContactDiary> mListData = new ArrayList<ContactDiary>();
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "RecyclerLayout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_layout);
        mDatabaseHelper = new DatabaseHelper(this);
        setConnection();
    }

    /**
     * GET DATA FROM DATABASE AND SET IT RECYCLERVIEW
     * CREATE CONNECTION OF RECYCLER VIEW AND ADAPTER AND DATABASE
     */
    private void setConnection() {
        ArrayList<ContactDiary> arrayListContact = mDatabaseHelper.getAllContacts();
        mRecyclerView = findViewById(R.id.recyclerView);
        ContactDiary contactDiary = arrayListContact.get(0);
        Log.d(TAG, "setConnection: "+contactDiary.getmContactID()+" : "+contactDiary.getmContactName() +" : "+contactDiary.getmContactNumber());
        MyAdapter mMyAdapter = new MyAdapter(arrayListContact);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);
    }
}
