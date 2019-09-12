package com.example.databaseproject.activity;
/**
 * CLASS TO DELETE DATA FROM DATABASE  AND UPDATE THE LIST
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.model.ContactDiary;
import com.example.databaseproject.model.MyAdapter;
import com.example.databaseproject.R;
import com.example.databaseproject.dataset.DatabaseHelper;

import java.util.ArrayList;

public class DeleteData extends AppCompatActivity implements MyAdapter.OnItemLongClickListener {
    private RecyclerView mRecyclerView;
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "RecyclerLayout";
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_layout);
        mDatabaseHelper = new DatabaseHelper(this);
        setConnection();
        mMyAdapter.setOnLongClickListener(this);
    }

    /**
     * GET DATA FROM DATABASE AND SET IT RECYCLERVIEW
     * CREATE CONNECTION OF RECYCLER VIEW AND ADAPTER AND DATABASE
     */
    private void setConnection() {
        ArrayList<ContactDiary> arrayListContact = mDatabaseHelper.getAllContacts();
        mRecyclerView = findViewById(R.id.recyclerView);
        mMyAdapter = new MyAdapter(arrayListContact);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);
    }

    /**
     * LISTNER OF RECYCLER VIEW
     *
     * @param adapterPosition IS THE POSITION OF LIST
     */
    @Override
    public void OnItemLongClick(final int adapterPosition) {
        Log.d(TAG, "OnItemLongClick: " + adapterPosition);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("  Delete");
        alertDialog.setMessage("Are You Sure You Want To Delete This Item");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ArrayList<ContactDiary> contactList = mDatabaseHelper.getAllContacts();
                int position = Integer.parseInt(contactList.get(adapterPosition).getmContactID());
                Log.d(TAG, "position: " + position);
                int deletedRows = mDatabaseHelper.deleteData(toString().valueOf(position));
                if (deletedRows > 0) {
                    Toast.makeText(getApplicationContext(), "Data Deleted", Toast.LENGTH_LONG).show();
                    contactList = mDatabaseHelper.getAllContacts();
                    mRecyclerView.setAdapter(new MyAdapter(contactList));
                    mMyAdapter.notifyItemRemoved(position);
                } else
                    Toast.makeText(getApplicationContext(), "Try again after some time ", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setIcon(R.drawable.diary);
        alertDialog.show();

    }
}

