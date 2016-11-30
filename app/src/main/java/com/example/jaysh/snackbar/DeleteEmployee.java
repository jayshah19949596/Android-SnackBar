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
import android.widget.EditText;
import android.widget.Toast;

public class DeleteEmployee extends AppCompatActivity {
    EditText emailID;
    DBMgr dataBaseAdapter;
    Button delEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_employee);
        dataBaseAdapter = DBMgr.getInstance(this);
        dataBaseAdapter.open();
        emailID = (EditText) findViewById(R.id.editTextDeleteEmployee);
        delEmp = (Button) findViewById(R.id.buttonDeleteEmployee);

        delEmp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                callDelete();
                Toast.makeText(DeleteEmployee.this,"Employee account deleted successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void callDelete(){
        if(emailID.getText().toString().length()==0){
            Toast.makeText(DeleteEmployee.this,"Please Enter EmailID",Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("Modify_Employee", "Nothing");

            Cursor userCursor = dataBaseAdapter.getSinlgeUser(emailID.getText().toString());

            if(userCursor.getCount()==0) {
                Toast.makeText(DeleteEmployee.this,"No such Employee to Delete",Toast.LENGTH_SHORT).show();

            }

            else {
                    dataBaseAdapter.deleteUSER(emailID.getText().toString());
            }

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
                Toast.makeText(DeleteEmployee.this,
                        "Logout Successful", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(DeleteEmployee.this, LogIn.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(DeleteEmployee.this, EmployeeTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }


        }

    }
}
