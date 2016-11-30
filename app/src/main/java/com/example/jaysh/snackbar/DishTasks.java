package com.example.jaysh.snackbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DishTasks extends AppCompatActivity {
    Button btnAddDish,btnDelDish,btnModifyDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_tasks);

        btnAddDish = (Button) findViewById(R.id.button_add_dish);
        btnDelDish = (Button) findViewById(R.id.button_delete_dish);
        btnModifyDish = (Button) findViewById(R.id.button_modify_dish);

        btnAddDish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentLogout = new Intent(DishTasks.this, AddDish.class);
                startActivity(intentLogout);

            }
        });

        btnDelDish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentLogout = new Intent(DishTasks.this, DeleteDish.class);
                startActivity(intentLogout);


            }
        });


        btnModifyDish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentLogout = new Intent(DishTasks.this, ModifyDish.class);
                startActivity(intentLogout);


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
                Toast.makeText(DishTasks.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(DishTasks.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(DishTasks.this, AdminHome.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }


        }

    }

}
