package com.example.jaysh.snackbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    RadioGroup radgrp;
    Button buttonPayment;
    TextView tvAddress;
    EditText editTextAddress;
    DBMgr DBMgr;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();
        buttonPayment = (Button) findViewById(R.id.buttonPayment);
        radgrp = (RadioGroup) findViewById(R.id.radiogroup) ;
        tvAddress = (TextView) findViewById(R.id.textViewAddress);
        editTextAddress = (EditText) findViewById(R.id.editTextViewAddress);

        radgrp.clearCheck();


        radgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);

                switch(checkedId) {
                    case R.id.rbChangeAddress:
                        Log.d("Paymeny","In changeadress switch");
                        tvAddress.setVisibility(View.VISIBLE);
                        editTextAddress.setVisibility(View.VISIBLE);
                        /*LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);
                        EditText et = new EditText(Payment.this);
                        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        et.setLayoutParams(p);
                        et.setHint("Write your Delivery Address overhere");
                        ll.addView(et);*/

                        break;

                    case R.id.rbDefault:

                        Log.d("Paymeny","In defaultadress switch");
                        tvAddress.setVisibility(View.GONE);
                        editTextAddress.setVisibility(View.GONE);
                        /*Toast.makeText(getBaseContext(), "You have Clicked fixed",
                                Toast.LENGTH_SHORT).show();
                        RelativeLayout mRlayout1 = new RelativeLayout(Audioactivity.this);
                        RelativeLayout.LayoutParams mRparams1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                        .
                        .
                        mRlayout1.addView(myEditText2);
                        linLayout.addView(mRlayout1);*/    //add the newly created elativeLayout to your container Layout
                        break;
                }

                Log.d("Paymeny","out of switch");

            }
        });

        buttonPayment.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(Payment.this,
                        "Payment Successful", Toast.LENGTH_SHORT)
                        .show();




                Order order = new Order();
                order.Order();
                DBMgr.insertOrder();
                /*final Dialog dialog = new Dialog(Payment.this);
                dialog.setContentView(R.layout.login);
                dialog.setTitle("PAYMENT SUCCESSFUL");
                dialog.show();
11-29 23:18:13.547 4607-4607/com.example.jaysh.snackbar W/ViewRootImpl: Cancelling event due to no window focus: MotionEvent { action=ACTION_CANCEL, actionButton=0, id[0]=0, x[0]=462.0, y[0]=640.0, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=474644509, downTime=474644426, deviceId=8, source=0x1002 }

                final Handler handler  = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                };

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        handler.removeCallbacks(runnable);
                    }
                });

                handler.postDelayed(runnable, 10000);*/



                Log.d("CartScreen.java", "User Login Role ADMIN");
                Intent intentPayment = new Intent(Payment.this, MenuActivity.class);
                startActivity(intentPayment);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.payment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Payment","In onOptionsItemSelected ");

        switch (item.getItemId()) {
            case R.id.payment_menu_viewmenu: {
                Log.d("Payment","ViewCart Selected ");

                Intent intentViewCart = new Intent(Payment.this, MenuActivity.class);
                //intentViewCart.putExtra("cartDishList", cartDishList);
                startActivity(intentViewCart);
                return true;
            }

            case R.id.payment_menu_logout: {
                Toast.makeText(Payment.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();
                Intent intentLogout = new Intent(Payment.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(Payment.this, CartScreen.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }


        }


    }
}
