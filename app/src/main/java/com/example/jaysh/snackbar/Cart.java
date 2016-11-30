package com.example.jaysh.snackbar;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jaysh on 11/18/2016.
 */
public class Cart  {

    public  String mDishName ;
    public   String mPrice ;
    public  int mQuantity;

    public Cart(String selectedDish, String selectedDishPrice)
    {
        Log.d("Cart","Inside Cart constructor");

        mDishName = selectedDish;
        mPrice = selectedDishPrice;
        mQuantity = 1;

        Log.d("Cart","selectedDishPrice = "+ selectedDishPrice);

        Log.d("Cart","Cart initialised");

    }

    public String getDishName()
    {
        return mDishName;
    }
    public String getDishPrice()
    {
        Log.d("Cart","mprice = "+ mPrice);

        return mPrice ;
    }

    public String getDishQuantity()
    {
        Log.d("Cart","mprice = "+ mPrice);

        return String.valueOf(mQuantity) ;
    }


    public  String updateQuantity(int quantity)
    {
        mQuantity = quantity ;
        return String.valueOf(mQuantity);
    }

    public  void updatePrice(String price)
    {

        Log.d("Cart","Updated mprice = "+ mPrice);

        mPrice = price;

    }
}


