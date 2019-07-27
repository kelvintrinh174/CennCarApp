package com.example.cenncarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText passwordEditText;
    private CennCarAppManager appmanager;
    private final static String USER_TABLE_NAME = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        // initialize the tables
        try {
            appmanager = new CennCarAppManager(this);
        }
        catch(Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }

    public void logIn(View view){
        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Customer user = new Customer();
        String inputUserName = userNameEditText.getText().toString();
        String inputPassword = passwordEditText.getText().toString();

        try {
            user = appmanager.getUserByUserName(inputUserName,"userName");
            SharedPreferences myPreference =
                    getSharedPreferences("UserInfo", 0);

            SharedPreferences.Editor prefEditor = myPreference.edit();
            prefEditor.putInt("CustomerID",user.getCustomerID());
            prefEditor.putString("UserName",user.getUserName());
            prefEditor.putString("FirstName", user.getFirstName());
            prefEditor.putString("LastName", user.getLastName());
            prefEditor.putString("Address", user.getAddress());
            prefEditor.putString("City", user.getCity());
            prefEditor.putString("PostalCode", user.getPostalCode());
            prefEditor.commit();
        }
        catch(Exception exception)
        {
            //
            Toast.makeText(this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }


        if(user!= null)
        {
            if(inputPassword.equals(user.getPassword()))
            {
                Intent intent = new Intent(this,UpdateCustomerInfo.class);
                startActivity(intent);
                Toast.makeText(this,
                        "Login Successful", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(this,
                        "Wrong password", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,
                    "Wrong username or password", Toast.LENGTH_SHORT).show();
        }

    }






}
