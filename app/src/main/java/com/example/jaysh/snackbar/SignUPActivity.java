package com.example.jaysh.snackbar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends AppCompatActivity {
    EditText editTextUserName,editTextUserId,editTextPassword,editTextConfirmPassword,editTextAddress,
            editTextCity,editTextZipCode,editTextCountry,editTextNumber;
    Button btnCreateAccount;
    Context context = this;
    DBMgr DBMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);
        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();

        editTextUserName = (EditText) findViewById(R.id.editTextUserNameToSignup);
        editTextUserId  = (EditText) findViewById(R.id.editTextUserIdToSignUp);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordToSignup);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPasswordToSignup);
        editTextAddress = (EditText) findViewById(R.id.editTextAddressToSignup) ;
        editTextCity = (EditText) findViewById(R.id.editTextCityToSignup) ;
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCodeToSignup) ;
        editTextCountry = (EditText) findViewById(R.id.editTextCountryToSignup) ;
        editTextNumber = (EditText) findViewById(R.id.editTextNumberToSignup) ;

        btnCreateAccount = (Button) findViewById(R.id.buttonSignup1);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String emailID = editTextUserId.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String address = editTextAddress.getText().toString();
                String city = editTextCity.getText().toString();
                String zipCode = editTextZipCode.getText().toString();
                String country = editTextCountry.getText().toString();
                String number = editTextNumber.getText().toString();
                String role = "CUSTOMER";
                if (userName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    return;
                } else {

                    DBMgr.insertEntry(userName,emailID,password,address,city,zipCode,country,number,role);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();

                    //Toast.makeText(getApplicationContext(),
                            //"Password is :"+DBMgr.getSinlgeUser(emailID), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(SignUPActivity.this,
                            LogIn.class);
                    startActivity(i);
                    finish();

                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
