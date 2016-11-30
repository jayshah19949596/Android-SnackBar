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

public class EmployeeTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_tasks);

        Button addEmployeeButton = (Button) findViewById(R.id.button_add_employee);
        Button modifyEmployeeButton = (Button) findViewById(R.id.button_modify_employee);
        Button deleteEmpButton = (Button)findViewById(R.id.button_delete_employee);

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent goToAddEmployeeIntent = new Intent(EmployeeTasks.this, AddEmployee.class);
                startActivity(goToAddEmployeeIntent);

            }
        });

        modifyEmployeeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent goToModifyEmployeeIntent = new Intent(EmployeeTasks.this, ModifyEmployee.class);
                startActivity(goToModifyEmployeeIntent);

            }
        });


        deleteEmpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent goToDeleteEmployeeIntent = new Intent(EmployeeTasks.this, DeleteEmployee.class);
                startActivity(goToDeleteEmployeeIntent);

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
                Toast.makeText(EmployeeTasks.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(EmployeeTasks.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(EmployeeTasks.this, AdminHome.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }


        }

    }

}