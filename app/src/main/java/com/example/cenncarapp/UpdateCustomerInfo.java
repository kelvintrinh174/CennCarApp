package com.example.cenncarapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateCustomerInfo extends AppCompatActivity {

    private CennCarAppManager appmanager;
    private EditText txtUserName, txtPassword,txtFirstName,txtLastName,txtAddress,txtCity,txtPostalCode;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer_info);

        appmanager = new CennCarAppManager(this);

        txtFirstName = findViewById(R.id.firstNameEditText);
        txtLastName = findViewById(R.id.lastNameEditText);
        txtAddress = findViewById(R.id.addressEditText);
        txtCity = findViewById(R.id.cityEditText);
        txtPostalCode = findViewById(R.id.postalCodeEditText);

        SharedPreferences myPref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String userFirstName = myPref.getString("FirstName","");
        String userLastName = myPref.getString("LastName","");
        userName = myPref.getString("UserName","");


        txtFirstName.setText(userFirstName);
        txtLastName.setText(userLastName);
        txtAddress.setText(myPref.getString("Address",""));
        txtCity.setText(myPref.getString("City",""));
        txtPostalCode.setText(myPref.getString("PostalCode",""));
    }

    public void Update(View view){
        //read values for text fields
        //String userName = txtUserName.getText().toString();
       // String password = txtPassword.getText().toString();
        String firstName = txtFirstName.getText().toString();
        String lastName = txtLastName.getText().toString();
        String address = txtAddress.getText().toString();
        String city = txtCity.getText().toString();
        String postalCode = txtPostalCode.getText().toString();
        //initialize ContentValues object with the new student

        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put("firstName",firstName);
            contentValues.put("lastName",lastName);
            contentValues.put("address",address);
            contentValues.put("city",city);
            contentValues.put("postalCode",postalCode);
            //edit the row
            boolean b = appmanager.editUser(userName, "userName", contentValues);
            Toast.makeText(this,"Update successfully", Toast.LENGTH_SHORT).show();

        }
        catch(Exception exception)
        {
            Toast.makeText(this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }
    }

    public void shoppingCar(View view){
        Intent intent = new Intent(this,CarShoppingActivity.class);
        startActivity(intent);
    }
}
