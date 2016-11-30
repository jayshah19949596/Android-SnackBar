package com.example.jaysh.snackbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}
