package com.example.challengeassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Database";
    private static final String TABLE_NAME = "User_Details";
    private static final String USER_PASSWORD = "User_Password";
    private static final String USER_NAME = "User_Name";
    private static final String USER_PHONE_NUMBER = "User_Phone_Number";
    private static final String USER_EMAIL = "User_email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + USER_EMAIL + " Text PRIMARY KEY ," + USER_NAME + " Text ,"
                + USER_PHONE_NUMBER + " Text ," + USER_PASSWORD + " Text " + ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addUser(UserClass userDiary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL, userDiary.getEmail());
        contentValues.put(USER_NAME, userDiary.getName());
        contentValues.put(USER_PHONE_NUMBER, userDiary.getPhoneNumber());
        contentValues.put(USER_PASSWORD, userDiary.getPassword());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }


    public ArrayList<UserClass> getLogIndData() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<UserClass> datalist = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_NAME
                + " = ? " + " and " + USER_PASSWORD + "= ?";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            UserClass userdata = new UserClass(cursor.getString(0), cursor.getString(1));
            datalist.add(userdata);
        }
        cursor.close();
        return datalist;
    }

    public ArrayList<UserClass> getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<UserClass> datalist = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            UserClass userdata = new UserClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            datalist.add(userdata);
        }
        cursor.close();
        return datalist;
    }


}
