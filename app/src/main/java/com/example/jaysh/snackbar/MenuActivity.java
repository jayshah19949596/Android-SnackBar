package com.example.jaysh.snackbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MenuActivity extends AppCompatActivity {
    //private CursorAdapter cursorAdapter;

    private Cursor c;
    public SQLiteDatabase db;
    FoodItem cursorAdapter ;
    DBMgr DBMgr;
    Button buttonAddToCart;
    protected static ArrayList<String> positionArrayList;
    protected static ArrayList<String> selectedDishes;
    protected static ArrayList<String> selectedDishDescription;
    protected static ArrayList<String> selectedDishPrice;
    protected static ArrayList<String> selectedDishCusineType;
    protected static ArrayList<Cart> cartDishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();

        Log.d("MenuActivity","Querying to get menu");

        c = DBMgr.getMenu();

        Log.d("MenuActivity ","Description Value : /n"+c.getString(c.getColumnIndex(DBMgr.MENU_DESCRIPTION)));


        positionArrayList = new ArrayList<>();
        selectedDishes = new ArrayList<>();
        selectedDishDescription = new ArrayList<>();
        selectedDishPrice = new ArrayList<>();
        selectedDishCusineType = new ArrayList<>();
        cartDishList = new ArrayList<>();
        Log.d("MenuActivity","Calling CursorAdapter");
        ListView menu_list = (ListView) findViewById(R.id.listViewMenu);

        cursorAdapter = new FoodItem(this, c, 0);
        Log.d("MenuActivity ","Description Value : /n"+c.getString(c.getColumnIndex(DBMgr.MENU_DESCRIPTION)));




        Log.d("MenuActivity","Out of Cursor Adpater");



        menu_list.setAdapter(cursorAdapter);
        Log.d("MenuActivity","Setting the layout from menu.xml");

        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view , int position, long id)
            {
                Log.d("MenuActivity","In on list on click");
                Log.d("MenuActivity","Position value: "+String.valueOf(position));

                CheckBox clickedCB = (CheckBox)view.findViewById(R.id.dishCB);
                TextView clickedDish = (TextView) view.findViewById(R.id.tvdishName);
                TextView clickedDishDescription = (TextView) view.findViewById(R.id.tvdishDescription);
                TextView clickedDishPrice = (TextView) view.findViewById(R.id.tvdishPrice);
                TextView clcikedDishCusineType = (TextView) view.findViewById(R.id.tvcuisinenType);


                String stringClickedDish = clickedDish.getText().toString();
                String stringClickedDishDescription = clickedDishDescription.getText().toString();
                String stringClickedDishPrice  = clickedDishPrice.getText().toString();
                String stringClickedDishCusineType = clcikedDishCusineType.getText().toString();


                Log.d("MenuActivity","Dish name: "+clickedDish.getText().toString());
                //positionArrayList.add(position);
                Log.d("MenuActivity","The checkbox is checked ?: "+String.valueOf(clickedCB.isChecked()));

                if(clickedCB.isChecked())
                {
                    clickedCB.setChecked(false);
                    if(positionArrayList.contains(String.valueOf(position)))
                    {
                        positionArrayList.remove(String.valueOf(position));
                        selectedDishes.remove(stringClickedDish);
                        selectedDishDescription.remove(stringClickedDishDescription);
                        selectedDishPrice.remove(stringClickedDishPrice);
                        selectedDishCusineType.remove(stringClickedDishCusineType);
                       /* for(Cart cart:cartDishList){
                            if(cart.getDishName().equals(stringClickedDish)){
                                cartDishList.remove(cart);
                            }
                        }*/
                    }

                    Log.d("MenuActivity","ArrayList Values when unchecked "+String.valueOf(positionArrayList));
                    Log.d("MenuActivity","ArrayList Values when unchecked "+String.valueOf(selectedDishes));
                    Log.d("MenuActivity","ArrayList Values when unchecked "+String.valueOf(selectedDishDescription));

                    Log.d("MenuActivity","ArrayList Values when unchecked "+String.valueOf(cartDishList));

                }
                else
                {
                    clickedCB.setChecked(true);
                    if(!positionArrayList.contains(String.valueOf(position)))
                    {
                        positionArrayList.add(String.valueOf(position));
                        selectedDishes.add(stringClickedDish);
                        selectedDishDescription.add(stringClickedDishDescription);
                        selectedDishPrice.add(stringClickedDishPrice);
                        selectedDishCusineType.add(stringClickedDishCusineType);
                        //cartDishList.add( new Cart(stringClickedDish,stringClickedDishPrice));
                    }
                    Log.d("MenuActivity","ArrayList Values when checked"+String.valueOf(positionArrayList));
                    Log.d("MenuActivity","ArrayList Values when checked"+String.valueOf(selectedDishes));
                    Log.d("MenuActivity","ArrayList Values when unchecked "+String.valueOf(cartDishList));
                }



            }
        });




        buttonAddToCart = (Button) findViewById(R.id.button_addToCart);

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("MenuActivity",String.valueOf(selectedDishes));



                for(Cart cart:cartDishList){
                    if(selectedDishes.contains(cart.getDishName())){
                        Toast.makeText(MenuActivity.this,
                                "Item already present in the cart", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                }

                Iterator<String> itDish = selectedDishes.iterator();
                Iterator<String> itPrice = selectedDishPrice.iterator();
                while (itDish.hasNext()) {
                    String dishName = itDish.next();
                    String dishPrice = itPrice.next();
                    Log.d("MenuActivity","dishprice = "+dishPrice);
                    cartDishList.add( new Cart(dishName,dishPrice));
                }

                if(selectedDishes.size()==0)
                {
                    Toast.makeText(MenuActivity.this,
                            "No Items Selected", Toast.LENGTH_SHORT)
                            .show();
                }





                else {
                    //Cart c = new Cart(selectedDishes, selectedDishPrice);
                    Log.d("MenuActivity", "Cart object created");

                    Toast.makeText(MenuActivity.this,
                            "Selected dishes added to cart successfully", Toast.LENGTH_SHORT)
                            .show();
                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options_menu_viewcart: {

                Intent intentViewCart = new Intent(getApplicationContext(), CartScreen.class);
                //intentViewCart.putExtra("cartDishList", cartDishList);
                startActivity(intentViewCart);
                return true;
            }

            case R.id.options_menu_logout: {
                Intent intentLogout = new Intent(MenuActivity.this, LogIn.class);
                startActivity(intentLogout);
                Toast.makeText(MenuActivity.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                return true;
            }


            default: {
                return true;
            }


        }


    }

}