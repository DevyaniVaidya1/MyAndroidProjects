package com.example.databaseproject.model;

/**
 * POJO CLASS
 */
public class ContactDiary {
    private String mContactName;
    private String mContactNumber;
    private String mContactID;

    public ContactDiary() {

    }

    public ContactDiary(String mContactID, String mContactName, String mNumber) {
        this.mContactName = mContactName;
        this.mContactNumber = mNumber;
        this.mContactID = mContactID;
    }

    public ContactDiary(String mContactName, String mNumber) {
        this.mContactName = mContactName;
        this.mContactNumber = mNumber;
    }

    public String getmContactName() {
        return mContactName;
    }

    public void setmContactName(String mContactName) {
        this.mContactName = mContactName;
    }

    public String getmContactNumber() {
        return mContactNumber;
    }

    public void setmContactNumber(String mContactNumber) {
        this.mContactNumber = mContactNumber;
    }

    public String getmContactID() {
        return mContactID;
    }

    public void setmContactID(String mContactID) {
        this.mContactID = mContactID;
    }
}
