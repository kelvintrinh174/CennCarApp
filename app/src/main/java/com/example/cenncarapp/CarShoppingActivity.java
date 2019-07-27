package com.example.cenncarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CarShoppingActivity extends AppCompatActivity {

    //TextView carModel;
    TextView text_sel;
    Intent intent;
    private CennCarAppManager appmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_shopping);
        final Spinner spinner = findViewById(R.id.spinner);
        final TextView carModel = findViewById(R.id.carModelTextView);
        final TextView carPrice = findViewById(R.id.priceTextView);
        final TextView carColour = findViewById(R.id.colourTextView);
        final TextView carStatus = findViewById(R.id.carStatusTextView);
        final TextView carType = findViewById(R.id.carTypeTextView);

        try {
            appmanager = new CennCarAppManager(this);
        }
        catch(Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

        intent = new Intent(this,Payment.class);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text_sel = (TextView)spinner.getSelectedView();
                Car car;
                if(text_sel.getText().equals("Honda"))
                {
                    car = getInfor("Honda");
                    intent.putExtra("carBrand",text_sel.getText());
                    intent.putExtra("price",car.getPrice());
                    intent.putExtra("carModel",car.getModelName());
                    updateInfor(car);
                }
                else if(text_sel.getText().equals("Toyota"))
                {
                    car = getInfor("Toyota");
                    intent.putExtra("carBrand",text_sel.getText());
                    intent.putExtra("price",car.getPrice());
                    intent.putExtra("carModel",car.getModelName());
                    updateInfor(car);
                }
                else if(text_sel.getText().equals("Mercedes"))
                {
                    car = getInfor("Mercedes");
                    intent.putExtra("carBrand",text_sel.getText());
                    intent.putExtra("price",car.getPrice());
                    intent.putExtra("carModel",car.getModelName());
                    updateInfor(car);

                }else if(text_sel.getText().equals("Ford")){
                    car = getInfor("Ford");
                    intent.putExtra("carBrand",text_sel.getText());
                    intent.putExtra("price",car.getPrice());
                    intent.putExtra("carModel",car.getModelName());
                    updateInfor(car);
                } else if (text_sel.getText().equals("Lexus")){
                    car = getInfor("Lexus");
                    intent.putExtra("carBrand",text_sel.getText());
                    intent.putExtra("price",car.getPrice());
                    intent.putExtra("carModel",car.getModelName());
                    updateInfor(car);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public Car getInfor(String brandName){
        Car car = new Car();
        try{
             car = appmanager.getCarByBrandName(text_sel.getText(),"brandName");
        }
        catch(Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
        return car;
    }

    public void updateInfor(Car car){
        final TextView carModel = findViewById(R.id.carModelTextView);
        final TextView carPrice = findViewById(R.id.priceTextView);
        final TextView carColour = findViewById(R.id.colourTextView);
        final TextView carStatus = findViewById(R.id.carStatusTextView);
        final TextView carType = findViewById(R.id.carTypeTextView);
        carModel.setText(car.getModelName());
        carPrice.setText(car.getPrice());
        carStatus.setText(car.getCarStatus());
        carType.setText(car.getCarType());
        carColour.setText(car.getCarColour());
    }

    public void checkOut(View view){
        startActivity(intent);
    }


}
