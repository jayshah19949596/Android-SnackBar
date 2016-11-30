package com.example.jaysh.snackbar;

import android.database.Cursor;
import android.util.Log;

/**
 * Created by jaysh on 11/23/2016.
 */


public class LoginProfile {
    String mUsername;
    String mpassword;
    public void LoginProfile(Cursor userCursor){
        Log.d("LoginProfile","IKn login profile");

        mUsername = userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_EMAIL));
        mpassword = userCursor.getString(userCursor.getColumnIndex(DBMgr.USERPROFILE_PASSWORD));
        Log.d("LoginProfile","IKn login profile"+mUsername);
        Log.d("LoginProfile","IKn login profile"+mpassword);

    }

    public boolean checkPassowrd(String enteredPassword){
        if(mpassword.equals(enteredPassword))
        {
            Log.d("LoginProfile","Password matched");

            return true;
        }
        else
        {
            Log.d("LoginProfile","Password not matched");

            return false ;
        }
    }
}
