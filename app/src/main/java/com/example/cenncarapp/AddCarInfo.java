package com.example.cenncarapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCarInfo extends AppCompatActivity {

    private CennCarAppManager appmanager;
    private EditText txtCarBrand,txtCarModel,txtPrice,txtCarColour,txtCarStatus,txtCarType;
    private final static String CAR_TABLE_NAME = "Car";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_info);
        appmanager = new CennCarAppManager(this);
        txtCarBrand = findViewById(R.id.carBrandEditText);
        txtCarModel = findViewById(R.id.carModelEditText);
        txtCarStatus = findViewById(R.id.carStatusEditText);
        txtCarColour = findViewById(R.id.carColourEditText);
        txtCarType = findViewById(R.id.carTypeEditText);
        txtPrice = findViewById(R.id.carPriceEditText);

    }

    public void addCar(View view){
        ContentValues contentValues = new ContentValues();

        contentValues.put("brandName",txtCarBrand.getText().toString());
        contentValues.put("modelName",txtCarModel.getText().toString());
        contentValues.put("price",txtPrice.getText().toString());
        contentValues.put("carColour",txtCarColour.getText().toString());
        contentValues.put("carStatus",txtCarStatus.getText().toString());
        contentValues.put("carType",txtCarType.getText().toString());


        try {
            appmanager.addRow(contentValues,CAR_TABLE_NAME);
            Toast.makeText(this,
                    "Your new car is created", Toast.LENGTH_SHORT).show();
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
