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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

public class CartScreen extends AppCompatActivity {
    Button buttonCheckout;
    int quantity;
    DBMgr DBMgr;
    int totalPrice=0;
    TextView tvtTotal;
    ListView cartListView;
    CartAdapter cartAdapter;
    static int orderTotal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CartScreen","Inside Cart Screen");
        super.onCreate(savedInstanceState);
        Log.d("CartScreen","Setting layout of  Cart Screen");

        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();

        if(MenuActivity.cartDishList.size()==0) {
            Toast.makeText(CartScreen.this,
                    "Your cart is empty", Toast.LENGTH_SHORT)
                    .show();

            //setContentView(R.layout.empty_cartscreen);
        }

        else{
        setContentView(R.layout.activity_cart_screen);
        cartListView = (ListView) findViewById(R.id.listViewCart);
        buttonCheckout = (Button) findViewById(R.id.checkout) ;

        //Bundle extras = getIntent().getExtras();
        //ArrayList<Cart> cartDishList = extras.getParcelable("cartDishList");

        Log.d("CartScreen","Calling CartAdpater");

        cartAdapter=new CartAdapter(this, R.layout.cart_list_view, MenuActivity.cartDishList);
        cartListView.setAdapter(cartAdapter);

            //Iterator iterator = MenuActivity.cartDishList.iterator();
            for(Cart cart:MenuActivity.cartDishList){
                totalPrice = totalPrice + Integer.parseInt(cart.getDishPrice());
                }
            tvtTotal = (TextView) findViewById(R.id.textTotal);



            tvtTotal.setText("Total Amount = $ "+totalPrice);

        buttonCheckout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(CartScreen.this,
                        "Checkout Successful", Toast.LENGTH_SHORT)
                        .show();


                updateOrderTotalPrice(totalPrice);
                Log.d("CartScreen.java","User Login Role ADMIN");
                Intent intentCheckout = new Intent(CartScreen.this, Payment.class);
                startActivity(intentCheckout);



            }
        });



        cartListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view , int position, long id)
            {

                Log.d("CartScreen","Inside AdapteronClick");


                Button plus = (Button) view.findViewById(R.id.buttonPlus);
                Button minus = (Button) view.findViewById(R.id.buttonMinus);
                Button remove = (Button) view.findViewById(R.id.buttonRemove);
                TextView quantityOfItem = (TextView) view.findViewById(R.id.textQuantityOfItem);
                TextView dishPrice = (TextView) view.findViewById(R.id.textDishPrice);





            }


        });




        }}



    public void increment(View v)
    {

        //reset all the listView items background colours
        //before we set the clicked one..



        //get the row the clicked button is in

        LinearLayout linearParentRow = (LinearLayout)v.getParent();
        LinearLayout root = (LinearLayout) linearParentRow.getParent();
        RelativeLayout relativeParentRow = (RelativeLayout) root.getChildAt(0);

        TextView childDishName = (TextView) relativeParentRow.getChildAt(0);
        TextView childDishPrice = (TextView) relativeParentRow.getChildAt(2);

        String currentPrice = childDishPrice.getText().toString();
        String currentDishName = childDishName.getText().toString();
        Cursor c = DBMgr.getSingleMenu(currentDishName);


        int price = Integer.parseInt(c.getString(c.getColumnIndex(DBMgr.MENU_PRICE)));

        TextView childQuantityTextView = (TextView)linearParentRow.getChildAt(0);
        Button btnChild = (Button)linearParentRow.getChildAt(1);
        TextView childQuantityNumber = (TextView)linearParentRow.getChildAt(2);
        String quantityNumber = childQuantityNumber.getText().toString();

        quantity = Integer.parseInt(quantityNumber);

        if (quantity == 100) {
            return;
        }
        totalPrice = totalPrice-quantity*price;
        quantity = quantity + 1;
        childQuantityNumber.setText(""+quantity);
        childDishPrice.setText(""+quantity*price);
        totalPrice = totalPrice+quantity*price;
        tvtTotal.setText("Total Amount = $ "+totalPrice);
        for(Iterator<Cart> it = MenuActivity.cartDishList.iterator(); it.hasNext();) {
            Cart crt = it.next();
            if(crt.getDishName().equals(currentDishName)) {
                quantity = Integer.parseInt(crt.updateQuantity(quantity));
                crt.updatePrice(String.valueOf(quantity*price));
            }
        }

        return;

    }

    public void decrement(View v)
    {

        //reset all the listView items background colours
        //before we set the clicked one..



        //get the row the clicked button is in

        LinearLayout linearParentRow = (LinearLayout)v.getParent();
        LinearLayout root = (LinearLayout) linearParentRow.getParent();
        RelativeLayout relativeParentRow = (RelativeLayout) root.getChildAt(0);

        TextView childDishName = (TextView) relativeParentRow.getChildAt(0);
        TextView childDishPrice = (TextView) relativeParentRow.getChildAt(2);

        String currentPrice = childDishPrice.getText().toString();
        String currentDishName = childDishName.getText().toString();
        Cursor c = DBMgr.getSingleMenu(currentDishName);


        int price = Integer.parseInt(c.getString(c.getColumnIndex(DBMgr.MENU_PRICE)));

        TextView childQuantityTextView = (TextView)linearParentRow.getChildAt(0);
        Button btnChild = (Button)linearParentRow.getChildAt(1);
        TextView childQuantityNumber = (TextView)linearParentRow.getChildAt(2);
        String quantityNumber = childQuantityNumber.getText().toString();

        quantity = Integer.parseInt(quantityNumber);

        if (quantity == 1) {
            return;
        }
        totalPrice = totalPrice-quantity*price;
        quantity = quantity - 1;
        childQuantityNumber.setText(""+quantity);
        childDishPrice.setText(""+quantity*price);
        totalPrice = totalPrice+quantity*price;
        tvtTotal.setText("Total Amount = $ "+totalPrice);

        for(Iterator<Cart> it = MenuActivity.cartDishList.iterator(); it.hasNext();) {
            Cart crt = it.next();
            if(crt.getDishName().equals(currentDishName)) {
                quantity = Integer.parseInt(crt.updateQuantity(quantity));
                crt.updatePrice(String.valueOf(quantity*price));
            }
        }

        return;
    }


    public void remove(View v)
    {

        //reset all the listView items background colours
        //before we set the clicked one..



        //get the row the clicked button is in
        LinearLayout linearParentRow = (LinearLayout)v.getParent();
        LinearLayout root = (LinearLayout) linearParentRow.getParent();
        RelativeLayout relativeParentRow = (RelativeLayout) root.getChildAt(0);
        ListView parentListView = (ListView)root.getParent();

        TextView childDishName = (TextView) relativeParentRow.getChildAt(0);
        TextView childDishPrice = (TextView) relativeParentRow.getChildAt(2);

        String currentPrice = childDishPrice.getText().toString();

        Log.d("CartScreen","currentPrice = "+currentPrice);
        String currentDishName = childDishName.getText().toString();
        //Cursor c = DBMgr.getSingleMenu(currentDishName);

       /* for(Cart cart:MenuActivity.cartDishList){
            if(cart.getDishName().equals(currentDishName)){
                MenuActivity.cartDishList.remove(cart);}}*/
        //totalPrice = totalPrice - Integer.parseInt(currentPrice);
        for(Iterator<Cart> it = MenuActivity.cartDishList.iterator(); it.hasNext();) {
            Cart c = it.next();
            if(c.getDishName().equals(currentDishName)) {
                it.remove();
            }
        }

        if(MenuActivity.cartDishList.size()==0)
        {
            setContentView(R.layout.empty_cartscreen);
            Toast.makeText(CartScreen.this,
                    "Your cart is empty", Toast.LENGTH_SHORT)
                    .show();
        }
        cartListView.setAdapter(cartAdapter);
        totalPrice=0;
        for(Cart cart:MenuActivity.cartDishList){
            totalPrice = totalPrice + Integer.parseInt(cart.getDishPrice());
        }

        tvtTotal.setText("Total Amount = $ "+totalPrice);
        return;
    }


    public  static void updateOrderTotalPrice(int totalPrice)
    {
        orderTotal = totalPrice;
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
                Toast.makeText(CartScreen.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(CartScreen.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
                {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(CartScreen.this, MenuActivity.class);
                startActivity(intentMenu);
                return true;
            }


            default: {
                return true;
            }


        }

    }
}
