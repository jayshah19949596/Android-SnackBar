package com.example.jaysh.snackbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button buttonEmployee = (Button)findViewById(R.id.button_employee_changes);
        Button buttonDishes = (Button)findViewById(R.id.button_dish_changes);

        buttonEmployee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intentEmployee = new Intent(getApplicationContext(),
                        EmployeeTasks.class);
                startActivity(intentEmployee);

            }
        });

        buttonDishes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intentDishes = new Intent(getApplicationContext(),
                        DishTasks.class);
                startActivity(intentDishes);

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
                Toast.makeText(AdminHome.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(AdminHome.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }



            default: {
                return true;
            }


        }

    }
}


