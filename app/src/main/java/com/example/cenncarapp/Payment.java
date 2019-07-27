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

public class Payment extends AppCompatActivity {
    EditText txtfullName,txtFullAddress,txtAmountPaid,txtPaymentDate;
    TextView viewCarInfo,viewPrice;
    SharedPreferences myPref;
    String processStatus;
    private CennCarAppManager appmanager;
    private final static String ORDER_TABLE_NAME = "CarSale";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        myPref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        appmanager = new CennCarAppManager(this);
        String firstName = myPref.getString("FirstName","");
        String lastName = myPref.getString("LastName","");
        txtfullName = findViewById(R.id.fullNameEditText);
        txtfullName.setText(firstName+" "+lastName);
        String address = myPref.getString("Address","");
        String city = myPref.getString("City","");
        String postalCode = myPref.getString("PostalCode","");
        txtFullAddress = findViewById(R.id.addressEditText);
        txtFullAddress.setText(address+", "+city+", "+postalCode);

        Intent intent = getIntent();
        String carBrand = intent.getStringExtra("carBrand");
        String carModel= intent.getStringExtra("carModel");
        viewCarInfo = findViewById(R.id.carInfotextView);
        viewCarInfo.setText(carBrand+" "+carModel);
        String carPrice = intent.getStringExtra("price");
        viewPrice = findViewById(R.id.carPriceTextView);
        viewPrice.setText("Price: "+carPrice+"$");
        txtAmountPaid = findViewById(R.id.amountEditText);
        txtPaymentDate = findViewById(R.id.paymentDateEditText);
        processStatus="InProcess";

    }

    public void addOrder(View view){

        String paymentDate = txtPaymentDate.getText().toString();
        String amountPaid = txtAmountPaid.getText().toString();
        ContentValues contentValues = new ContentValues();

        contentValues.put("paymentDate",paymentDate);
        contentValues.put("processStatus",processStatus);
        contentValues.put("amountPaid",amountPaid);


        //
        try {
            appmanager.addRow(contentValues,ORDER_TABLE_NAME);
            Toast.makeText(this,
                    "Your order is created", Toast.LENGTH_SHORT).show();
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
