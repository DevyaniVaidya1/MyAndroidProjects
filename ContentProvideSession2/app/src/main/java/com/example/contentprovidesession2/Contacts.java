package com.example.contentprovidesession2;

/**
 * POJO CLASS FOR RECYCLER VIEW
 */
public class Contacts {
    private String mContactName;
    private String mContactNumber;
    private String mFirstLetterOfName;

    public Contacts(String mContactName, String mContactNumber, String mFirstLetterOfName) {
        this.mContactName = mContactName;
        this.mContactNumber = mContactNumber;
        this.mFirstLetterOfName = mFirstLetterOfName;
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

    public String getmFirstLetterOfName() {
        return mFirstLetterOfName;
    }

    public void setmFirstLetterOfName(String mFirstLetterOfName) {
        this.mFirstLetterOfName = mFirstLetterOfName;
    }
}
