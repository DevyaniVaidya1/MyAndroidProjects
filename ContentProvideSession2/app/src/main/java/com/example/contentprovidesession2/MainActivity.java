package com.example.contentprovidesession2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] projection = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};
    private ArrayList<Contacts> mlistdata = new ArrayList<Contacts>();
    private RecyclerView mrecyclerView;
    private MyListAdapter mMyListAdapter;
    private String mphoneNumber;
    private Bundle mbundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContactData();
        buildRecyclerView();
    }

    /**
     * function to connect recycler view ,data set and adapter .
     * also this function transfer data to another activity
     */
    private void buildRecyclerView() {
        mrecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mMyListAdapter = new MyListAdapter(mlistdata);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setAdapter(mMyListAdapter);
/**
 * send data  to next activity
 */
        mMyListAdapter.setOnItemClickListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView text) {
                passDataToNextActivity(position);
                animationCode();
                goToNextActivity(text);
            }
        });
    }

    /**
     * GET DATA FROM POJO CLASS USING POJO CLASS METHOD AND USING BUNDLE PASS IT TO NEXT ACTIVITY
     *
     * @param position OF VIEW
     */
    private void passDataToNextActivity(int position) {
        Contacts myConmtacts = mlistdata.get(position);
        String name = myConmtacts.getmContactName();
        String number = myConmtacts.getmContactNumber();
        String logoLetter = myConmtacts.getmFirstLetterOfName();
        Log.d("values", "onItemClick: " + name);
        Log.d("values", "onItemClick: " + number);
        mbundle = new Bundle();
        mbundle.putString("Name", name);
        mbundle.putString("Number", number);
        mbundle.putString("LogoLetter", logoLetter);
    }

    /**
     * CODE FOR ANIMATING TEXTVIEW
     */
    private void animationCode() {
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
    }

    /**
     * CODE TO MOVE TO NEXT ACTIVITY
     *
     * @param text ID OF  TEXTVIEW
     */
    private void goToNextActivity(TextView text) {
        Intent intent = new Intent(MainActivity.this, ShowInformation.class);
        intent.putExtras(mbundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                MainActivity.this, text, ViewCompat.getTransitionName(text));
        startActivity(intent, options.toBundle());
    }

    /**
     * FUNCTION TO READ CONTACT DATA FROM DEVICE AND SET IT RECYCLER VIEW
     */
    private void getContactData() {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, null, null,
                "display_name ASC");
        Log.d("cursor", "onCreate:" + cursor.getCount());
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            name = name.toUpperCase();
            char firstLetter = name.charAt(0);

            if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + cursor.getString(0), null, null);
                while (phones.moveToNext()) {
                    mphoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.i("Number", mphoneNumber + "" + cursor.getString(1));
                }
                mlistdata.add(new Contacts(cursor.getString(1), mphoneNumber, toString().valueOf(firstLetter)));
                phones.close();
            }
        }
        cursor.close();

    }

}
