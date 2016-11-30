package com.example.jaysh.snackbar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderScreen extends AppCompatActivity {
    private Cursor c;
    DBMgr DBMgr;
    TextView tvUserIdValue,tvOrderIdValue,tvAddress,tvTimeStamp;
    static String[] cartItems,itemname,itemquantity;
    OrderAdapter orderAdapter;
    ListView orderDescription;
    Button bRemove;
    int i;
    int j=0,k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("OrderScreen","onCreate()");

        setContentView(R.layout.activity_order_screen);
        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();
        tvUserIdValue = (TextView)findViewById(R.id.orderUserIdValue);
        tvOrderIdValue = (TextView)findViewById(R.id.orderOrderIdValue);
        tvAddress = (TextView)findViewById(R.id.orderAddressValue);
        tvTimeStamp = (TextView)findViewById(R.id.orderTimeStampValue);
        bRemove = (Button) findViewById(R.id.buttonRemove);

        Log.d("OrderScreen","GettingIntent");


        Intent intent = getIntent();
        String orderID = intent.getStringExtra("OrderNumber");
        Log.d("OrderScreen","listview obtained in the variable "+orderID);

        c = DBMgr.getSingleOrder(orderID);

        c.moveToFirst();

        Log.d("OrderScreen","Cursor value "+c.getString(c.getColumnIndex(DBMgr.ORDER_DESCRIPTION)));
        orderDescription = (ListView)findViewById(R.id.listViewDescription);
        tvUserIdValue.setText(c.getString(c.getColumnIndex(com.example.jaysh.snackbar.DBMgr.ORDER_USERNAME)));
        tvOrderIdValue.setText(c.getString(c.getColumnIndex(com.example.jaysh.snackbar.DBMgr.ORDER_ID)));
        tvAddress.setText(c.getString(c.getColumnIndex(com.example.jaysh.snackbar.DBMgr.ORDER_ADDRESS)));
        tvTimeStamp.setText(c.getString(c.getColumnIndex(com.example.jaysh.snackbar.DBMgr.ORDER_TIMESTAMP)));
        cartItems = c.getString(c.getColumnIndex(com.example.jaysh.snackbar.DBMgr.ORDER_DESCRIPTION)).split(" ");
        Log.d("OrderScreen","cartItems "+cartItems);
        itemname = new String[cartItems.length/2];
        itemquantity= new String[cartItems.length/2];

        for(i=0;i<cartItems.length;i++){
            if(i%2==0){
                Log.d("OrderScreen","EVEn "+cartItems[i]);

                itemname[j]=cartItems[i];
                j++;}
            else
            {
                Log.d("OrderScreen","ODD "+cartItems[i]);
                itemquantity[k]=cartItems[i];
                k++;
            }
        }

        orderAdapter=new OrderAdapter(this,R.layout.order_description_list_view,itemname);
        orderDescription.setAdapter(orderAdapter);

        bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBMgr.removeOrder(c.getString(c.getColumnIndex(com.example.jaysh.snackbar.DBMgr.ORDER_ID)));
                Intent intentChefhome = new Intent(OrderScreen.this, ChefHome.class);
                startActivity(intentChefhome);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.cart_screen_logout: {
                Toast.makeText(OrderScreen.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(OrderScreen.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }


            case android.R.id.home : {



                Intent intentLogout = new Intent(OrderScreen.this, ChefHome.class);
                startActivity(intentLogout);
                return true;
            }



            default: {
                return true;
            }


        }

    }
}
