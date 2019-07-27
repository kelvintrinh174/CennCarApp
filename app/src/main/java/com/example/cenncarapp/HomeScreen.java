package com.example.cenncarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    private Intent intent;
    private CennCarAppManager appmanager;

    private final static String USER_TABLE_NAME = "User";
    private final static String CAR_TABLE_NAME = "Car";
    private final static String ORDER_TABLE_NAME = "CarSale";

    //sql string to create the table
    private static final String tableCreatorString =
            "CREATE TABLE "+ USER_TABLE_NAME + " (customerID integer primary key AUTOINCREMENT, " +
                    "userName text,password text, firstName text, lastName text, address text, city text," +
                    "postalCode text);";

    private static final String carTableCreatorString =
            "CREATE TABLE "+ CAR_TABLE_NAME + " (carID integer primary key AUTOINCREMENT, " +
            "brandName text,modelName text, price text,carColour text,carStatus text, carType text);";

    private static final String orderTableCreatorString =
            "CREATE TABLE "+ ORDER_TABLE_NAME + " (orderID integer primary key AUTOINCREMENT, " +
                    "paymentDate text, processStatus text,amountPaid text);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        // initialize the tables
        try {
             appmanager = new CennCarAppManager(this);

            //create the table
            appmanager.dbInitialize(USER_TABLE_NAME, tableCreatorString,CAR_TABLE_NAME, carTableCreatorString,ORDER_TABLE_NAME,orderTableCreatorString);

            Toast.makeText(this,
                    "Table was created", Toast.LENGTH_SHORT).show();
        }
        catch(Exception exception)
        {
            Toast.makeText(this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }

    public void signup(View view)
    {
        intent = new Intent(this,SignUp.class);

        startActivity(intent);
    }

    public void login(View view)
    {
        intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }

    public void addCar(View view){
        intent = new Intent(this,AddCarInfo.class);
        startActivity(intent);
    }
}
