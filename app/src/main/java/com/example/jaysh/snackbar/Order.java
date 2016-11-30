package com.example.jaysh.snackbar;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;


/**
 * Created by jaysh on 11/21/2016.
 */
public class Order {

    static ArrayList<Cart> mCartItems;
    static String mTimeStamp;
    static int mTotalPrice;
    static String mEstimatedDeliveryTime;
    static String mUsername;
    static String mAddress;
    Customer customer;

    public void Order(){
        customer = new Customer();
        Log.d("Order","Inside Order");
        mCartItems = MenuActivity.cartDishList;
        mTotalPrice = CartScreen.orderTotal;
        mTimeStamp = intialiseTime();
        mEstimatedDeliveryTime = intialiseEstimatedDeliveryTime();
        mUsername =  customer.getuserName();
        mAddress= customer.getAddress();
        Log.d("Order","Inside Order"+mAddress);

        Log.d("Order","Inside Order"+mUsername);

    }

    public static String intialiseTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm aa");

        Calendar now = Calendar.getInstance();

        Log.d("Order",String.valueOf(df.format(now.getTime())));

        return String.valueOf(df.format(now.getTime()));

    }

    public static String intialiseEstimatedDeliveryTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm aa");

        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 30);

        Log.d("Order",String.valueOf(df.format(now.getTime())));

        return String.valueOf(df.format(now.getTime()));
    }

}


