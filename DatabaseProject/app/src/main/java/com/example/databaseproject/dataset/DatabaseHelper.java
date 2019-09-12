package com.example.databaseproject.dataset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.databaseproject.model.ContactDiary;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Contact_Diary";
    private static final String TABLE_NAME = "Contact_Details ";
    private static final String CONTACT_ID = "Contact_Id";
    private static final String CONTACT_NAME = "Contact_Name";
    private static final String CONTACT_PHONE_NUMBER = "Contact_Phone_Number";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONTACT_NAME + " Text,"
                + CONTACT_PHONE_NUMBER + " Text" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addContact(ContactDiary contactDiary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, contactDiary.getmContactName());
        contentValues.put(CONTACT_PHONE_NUMBER, contactDiary.getmContactNumber());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    ContactDiary getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{CONTACT_ID,
                        CONTACT_NAME, CONTACT_NAME}, CONTACT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ContactDiary contactDiary = new ContactDiary(
                cursor.getString(1), cursor.getString(2), cursor.getString(0));
        cursor.close();
        return contactDiary;
    }

    public ArrayList<ContactDiary> getAllContacts() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ContactDiary> contactDiaries = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            ContactDiary contact = new ContactDiary(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            contactDiaries.add(contact);
        }
        cursor.close();
        return contactDiaries;
    }

    public boolean updateData(ContactDiary contactDiary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_ID, contactDiary.getmContactID());
        contentValues.put(CONTACT_NAME, contactDiary.getmContactName());
        contentValues.put(CONTACT_PHONE_NUMBER, contactDiary.getmContactNumber());
        int i = db.update(TABLE_NAME, contentValues, CONTACT_ID + " = ?", new String[]{String.valueOf(contactDiary.getmContactID())});

        if (i > 0)
            return true;
        else
            return false;
    }

    public int deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, CONTACT_ID + " = ?", new String[]{id});
        Log.d("i", "deleteData: " + i);
        return i;
    }


}
