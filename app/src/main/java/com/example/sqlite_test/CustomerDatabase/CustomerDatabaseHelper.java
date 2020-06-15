package com.example.sqlite_test.CustomerDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// Singleton
public class CustomerDatabaseHelper {

    private static SQLiteOpenHelper _openHelper;
    private static SQLiteDatabase _db;
    private static CustomerDatabaseHelper _instance;

    // private to avoid creation outside this class
    private CustomerDatabaseHelper(Context context){
        this._openHelper = new DatabaseOpenHelper(context);
    }


    // Returns singleton instance of CustomerDatabaseHelper
    public static CustomerDatabaseHelper getInstance(Context context){
        if(_instance == null){
            _instance = new CustomerDatabaseHelper(context);
        }
        return _instance;
    }

    // Open database connection
    public static void open() {
        _db = _openHelper.getWritableDatabase();
    }

    // Close database connection
    public static void close(){
        _db.close();
    }


    // Inserts Customer and returns boolean whether it was added or not.
    public boolean insertCustomer(Customer customer){

        ContentValues cv = new ContentValues();
        cv.put("customer_name", customer.getName());
        cv.put("customer_description", customer.getDescription());

        long insert = _db.insert("Customer", null, cv);

        if(insert == -1){
            return false;
        }

        return true;
    }

    public static List<Customer> getCustomers() {
        List<Customer> retList = new ArrayList<>();
        String queryString = "SELECT * FROM Customer";
        Cursor cursor = _db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do {
                int custId = cursor.getInt(0);
                String custName = cursor.getString(1);
                String custDesc = cursor.getString(2);

                Customer cust = new Customer(custId, custName, custDesc);

                retList.add(cust);

            } while (cursor.moveToNext());
        }

        // Close the result set
        cursor.close();

        return retList;
    }


    public void updateCustomer(Customer oldCustomer, Customer newCustomer){
    ContentValues cust = new ContentValues();
    cust.put("customer_name", newCustomer.getName());
    cust.put("customer_description", newCustomer.getDescription());
    _db.update("Customer", cust, "customer_name = ?", new String[]{oldCustomer.getName()});
    }

    public void deleteCustomer(Customer customer){
        _db.delete("Customer", "customer_name = ?", new String[]{customer.getName()});
    }

}
