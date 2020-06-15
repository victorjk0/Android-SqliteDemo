package com.example.sqlite_test.CustomerDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomerDatabaseHelper extends SQLiteOpenHelper {


    public CustomerDatabaseHelper(@Nullable Context context) {
        super(context, "CustomerDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCustomerTable = "CREATE TABLE Customer(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customer_name TEXT," +
                "customer_description TEXT);";

        sqLiteDatabase.execSQL(createCustomerTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("customer_name", customer.getName());
        cv.put("customer_description", customer.getDescription());

        long insert = db.insert("Customer", null, cv);

        if(insert == -1){
            return false;
        }

        return true;
    }

    public List<Customer> getCustomers() {
        List<Customer> retList = new ArrayList<>();

        String queryString = "SELECT * FROM Customer";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                int custId = cursor.getInt(0);
                String custName = cursor.getString(1);
                String custDesc = cursor.getString(2);

                Customer cust = new Customer(custId, custName, custDesc);

                retList.add(cust);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return retList;
    }
}
