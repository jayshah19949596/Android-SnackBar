package com.example.jaysh.snackbar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDish extends AppCompatActivity {
    EditText dishName,cuisineType,description,price;
    Button btnAddDishToMenu;
    DBMgr dataBaseAdapter;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_dish);

        dataBaseAdapter = DBMgr.getInstance(this);
        dataBaseAdapter.open();


        dishName = (EditText) findViewById(R.id.editTextAddDishName);
        cuisineType  = (EditText) findViewById(R.id.editTextAddCuisineType);
        description = (EditText) findViewById(R.id.editTextAddDescription);
        price = (EditText) findViewById(R.id.editTextAddPrice);

        Button btnAddDishToMenu = (Button) findViewById(R.id.btnAddDishToMenu);

        btnAddDishToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                insert();



                Toast.makeText(getApplicationContext(),
                        "Dish Added Successfully to the Menu ", Toast.LENGTH_LONG)
                        .show();

            }
        });

    }



    public void insert(){

        String stringDishName = dishName.getText().toString();
        String stringCuisineType = cuisineType.getText().toString();
        String stringDescription = description.getText().toString();
        String stringPrices = price.getText().toString();





        dataBaseAdapter.addDishToMenu(stringDishName,stringCuisineType,stringDescription,stringPrices);

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
                Toast.makeText(AddDish.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(AddDish.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(AddDish.this, DishTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }


        }

    }


}
