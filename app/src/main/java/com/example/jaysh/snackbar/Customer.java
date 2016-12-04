package com.example.jaysh.snackbar;

import android.database.Cursor;

/**
 * Created by jaysh on 11/23/2016.
 */
public class Customer extends User {
    static  String mRole;
    public void Customer(Cursor userCursor){

        super.User(userCursor);
        mRole = "CUSROMER";
    }


}
