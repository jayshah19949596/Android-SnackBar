package com.example.jaysh.snackbar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChefHome extends AppCompatActivity {
    private Cursor c;
    DBMgr DBMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_home);
        Log.d("Chefhome","Firing query");
        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();

        c = DBMgr.getOrder();
        Log.d("Chefhome","query fired");

        int size = c.getCount();
        Log.d("Chefhome","size of cursor"+size);
        if(size==0)
        {
            Toast.makeText(ChefHome.this,"No Orders To Prepare", Toast.LENGTH_SHORT).show();
        }

        else {
            c.moveToFirst();
            Log.d("Chefhome", "size of cursor" + size);

            String[] order = new String[size];
            order[0] = "ORDER NUMBER : " + c.getString(c.getColumnIndex(DBMgr.ORDER_ID));
            int i = 1;
            while (c.moveToNext()) {

                order[i] = "ORDER NUMBER : " + c.getString(c.getColumnIndex(DBMgr.ORDER_ID));
                i++;
            }
            Log.d("Chefhome", "Orders = " + order[0]);


            ArrayAdapter<String> orderAdapter = new ArrayAdapter<>(this, R.layout.order_list_view, order);
            Log.d("Chefhome", "Array adapter refrence variable created = ");
            ListView listViewOrder = (ListView) findViewById(R.id.listview_order_number);

            Log.d("Chefhome", "listview obtained in the variable ");
            Log.d("Chefhome", "listview obtained in the variable " + String.valueOf(listViewOrder));
            listViewOrder.setAdapter(orderAdapter);

            listViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView clickedTextView = (TextView) view.findViewById(R.id.textView);
                    String text = clickedTextView.getText().toString();
                    String[] textword = text.split(" ");
                    String order_number = textword[3];
                    String Extra_TEXT = "OrderNumber";
                    Intent intentOrder = new Intent(ChefHome.this, OrderScreen.class);
                    intentOrder.putExtra(Extra_TEXT, order_number);
                    startActivity(intentOrder);


                }
            });

        }

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
                Toast.makeText(ChefHome.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(ChefHome.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }



            default: {
                return true;
            }


        }

    }
}
