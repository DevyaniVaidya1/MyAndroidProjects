package com.example.databaseproject.activity;
/**
 * CLASS  TO UPDATE DATA IN THE DATABASE
 */

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.model.ContactDiary;
import com.example.databaseproject.model.MyAdapter;
import com.example.databaseproject.R;
import com.example.databaseproject.dataset.DatabaseHelper;

import java.util.ArrayList;

public class UpdateData extends AppCompatActivity implements MyAdapter.OnItemLongClickListener {
    private static final String TAG = "RecyclerLayout";
    private RecyclerView mRecyclerView;
    private DatabaseHelper mDatabaseHelper;
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
        // ContactDiary contactDiary = arrayListContact.get();
        // Log.d(TAG, "setConnection: " + contactDiary.getmContactID() + " : " + contactDiary.getmContactName() + " : " + contactDiary.getmContactNumber());
        mMyAdapter = new MyAdapter(arrayListContact);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);
    }

    /**
     * LISTENER OF  RECYCLER VIEW
     * @param adapterPosition IS POSITION OF ITEM.
     * THIS  LISTENER SHOW DIALOG WHICH TAKE VALUES FROM DATA BASE AND UPDATE IT INTO DATA BASE.
     */
    @Override
    public void OnItemLongClick(final int adapterPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateData.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_update, null);
        final EditText name = view.findViewById(R.id.et_updatedialog_name);
        final EditText number = view.findViewById(R.id.et_updatedialog_number);
        Button update = view.findViewById(R.id.btn_updatedialog_update);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        /**
         * LISTENER OF DIALOG TO GET DATA FROM USER AND SET IT TO  DATABASE
         */

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdateCode(adapterPosition, name, number, dialog);

            }
        });
    }

    /**
     * FUNCTION TO  TAKE USERINPUT TO UPDATE DATA INTO  DATABASE
     *
     * @param adapterPosition POSITION OF  LIST
     * @param name            USERINPUT
     * @param number          USERINPUT
     * @param dialog          OBJECT IOF ALERT DIALOG
     */
    private void dialogUpdateCode(int adapterPosition, EditText name, EditText number, AlertDialog dialog) {
        ArrayList<ContactDiary> contactList = mDatabaseHelper.getAllContacts();
        int index = Integer.parseInt(contactList.get(adapterPosition).getmContactID());
        Log.d(TAG, "index: " + index);
        ContactDiary contactDiary = contactList.get(index -1);
        Log.d(TAG, "dialogcontact id: " + contactDiary.getmContactID());
        contactDiary.setmContactName(name.getText().toString());
        contactDiary.setmContactNumber(number.getText().toString());
        boolean state = mDatabaseHelper.updateData(contactDiary);
        if (state) {
            Toast.makeText(getApplicationContext(), "data update", Toast.LENGTH_SHORT).show();
            contactList = mDatabaseHelper.getAllContacts();
            mRecyclerView.setAdapter(new MyAdapter(contactList));
            mMyAdapter.notifyDataSetChanged();
            mRecyclerView.scrollToPosition(adapterPosition);
            dialog.dismiss();
        } else
            Toast.makeText(getApplicationContext(), "Updation failed", Toast.LENGTH_SHORT).show();
    }

}

