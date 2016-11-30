package com.example.jaysh.snackbar;

import android.database.Cursor;

/**
 * Created by jaysh on 11/23/2016.
 */
public class User {
    String mUserName;
    String mUserId;
    String mPassword;
    String mAddress;
    String mCity;
    String mZipCode;
    String mCountry;
    String mNumber;
    public  void User(Cursor userCursor){
        mUserName = userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_NAME));
        mUserId = userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_EMAIL));
        mPassword =  userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_PASSWORD));
        mAddress =  userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_ADDRESS));
        mCity =  userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_CITY));
        mZipCode =  userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_ZIPCODE));
        mCountry = userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_COUNTRY));
        mNumber = userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_NUMBER));


    }

    public String getuserID(){
        return mUserId;
    }

    public String getuserName(){
        return mUserName;
    }


    public String getAddress(){
        return mAddress;
    }

    public String getmPassword(){return mPassword; }
}
