package com.example.cenncarapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CennCarAppManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CennCarAppDB";
    private static final int DATABASE_VERSION = 1;
    private static String tableName1;
    private static String tableName2;
    private static String tableName3;
    private static String tableCreatorString1;
    private static String tableCreatorString2;
    private static String tableCreatorString3;

    public CennCarAppManager(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        db.execSQL(tableCreatorString1);
        db.execSQL(tableCreatorString2);
        db.execSQL(tableCreatorString3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if existed
        db.execSQL("DROP TABLE IF EXISTS" + tableName1);
        db.execSQL("DROP TABLE IF EXISTS" + tableName2);
        db.execSQL("DROP TABLE IF EXISTS" + tableName3);
        //recreate the table
        onCreate(db);
    }

    public void dbInitialize(String tableName1, String tableCreatorString1,String tableName2,String tableCreatorString2,String tableName3,String tableCreatorString3)
    {
        this.tableName1 = tableName1;
        this.tableName2 = tableName2;
        this.tableName3 = tableName3;
        this.tableCreatorString1 = tableCreatorString1;
        this.tableCreatorString2 = tableCreatorString2;
        this.tableCreatorString3 = tableCreatorString3;
    }


    public boolean addRow(ContentValues values,String tableName) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the row
        long nr = db.insert(tableName, null, values);
        db.close(); //close database connection
        return nr > -1;
    }

    public Customer getUserByUserName(Object userName, String fieldName) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + tableName1 + " where "+ fieldName + "='"+String.valueOf(userName)+"'", null );
        Customer user = new Customer(); //create a new Student object

        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of task object
            user.setUserName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setFirstName(cursor.getString(3));
            user.setLastName(cursor.getString(4));
            user.setAddress(cursor.getString(5));
            user.setCity(cursor.getString(6));
            user.setPostalCode(cursor.getString(7));
            cursor.close();

        } else {
           user = null;
        }

        db.close();
        return user;

    }

    public Car getCarByBrandName(Object brandName, String fieldName) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + tableName2 + " where "+ fieldName + "='"+String.valueOf(brandName)+"'", null );
        Car car = new Car();
        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of task object
            car.setBrandName(cursor.getString(1));
            car.setModelName(cursor.getString(2));
            car.setPrice(cursor.getString(3));
            car.setCarColour(cursor.getString(4));
            car.setCarStatus(cursor.getString(5));
            car.setCarType(cursor.getString(6));

            cursor.close();

        } else {
            car = null;
        }

        db.close();

        return car;
    }

    public boolean editUser (Object userName, String fieldName, ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        //
        int nr = db.update(tableName1, values, fieldName + " = ? ", new String[]{String.valueOf(userName)});
        return nr > 0;
    }


}
