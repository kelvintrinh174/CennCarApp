package com.example.cenncarapp;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private CennCarAppManager appmanager;
    private EditText txtUserName, txtPassword,txtFirstName,txtLastName,txtAddress,txtCity,txtPostalCode;
    private final static String USER_TABLE_NAME = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        appmanager = new CennCarAppManager(this);
        txtUserName = findViewById(R.id.userNameEditText);
        txtPassword = findViewById(R.id.passwordEditText);
        txtFirstName = findViewById(R.id.firstNameEditText);
        txtLastName = findViewById(R.id.lastNameEditText);
        txtAddress = findViewById(R.id.addressEditText);
        txtCity = findViewById(R.id.cityEditText);
        txtPostalCode = findViewById(R.id.postalCodeEditText);


    }

    public void addUser(View view)
    {
        //read values for text fields
        String userName = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();
        String firstName = txtFirstName.getText().toString();
        String lastName = txtLastName.getText().toString();
        String address = txtAddress.getText().toString();
        String city = txtCity.getText().toString();
        String postalCode = txtPostalCode.getText().toString();
        //initialize ContentValues object with the new student
        ContentValues contentValues = new ContentValues();

        contentValues.put("userName",userName);
        contentValues.put("passWord",password);
        contentValues.put("firstName",firstName);
        contentValues.put("lastName",lastName);
        contentValues.put("address",address);
        contentValues.put("city",city);
        contentValues.put("postalCode",postalCode);
        //
        try {
            appmanager.addRow(contentValues,USER_TABLE_NAME);
            Toast.makeText(this,
                    "Your account is created", Toast.LENGTH_SHORT).show();
        }
        catch(Exception exception)
        {
            //
            Toast.makeText(this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }


    }

}
