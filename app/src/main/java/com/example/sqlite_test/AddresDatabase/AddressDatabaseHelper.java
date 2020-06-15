package com.example.sqlite_test.AddresDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite_test.CustomerDatabase.Customer;
import com.example.sqlite_test.CustomerDatabase.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class AddressDatabaseHelper {
    private static SQLiteOpenHelper _openHelper;
    private static SQLiteDatabase _db;
    private static AddressDatabaseHelper _instance;

    // private to avoid creation outside this class
    private AddressDatabaseHelper(Context context){
        this._openHelper = new DatabaseOpenHelper(context);
    }

    // Returns singleton instance of AddressDatabaseHelper
    public static AddressDatabaseHelper getInstance(Context context){
        if(_instance == null){
            _instance = new AddressDatabaseHelper(context);
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

    // Inserts Address and returns boolean whether it was added or not.
    public boolean insertAddress(Address address){

        ContentValues cv = new ContentValues();
        cv.put("street_name", address.getStreetName());
        cv.put("zip_code", address.getZipCode());

        long insert = _db.insert("Address", null, cv);

        if(insert == -1){
            return false;
        }

        return true;
    }

    public static List<Address> getAddresses() {
        List<Address> retList = new ArrayList<>();
        String queryString = "SELECT * FROM Address";
        Cursor cursor = _db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do {
                int addId = cursor.getInt(0);
                String addStreet = cursor.getString(1);
                String addZip = cursor.getString(2);

                Address address = new Address(addId, addStreet, addZip);

                retList.add(address);

            } while (cursor.moveToNext());
        }

        // Close the result set
        cursor.close();

        return retList;
    }

    public void updateAddress(Address oldAddress, Address newAddress){
        ContentValues address = new ContentValues();
        address.put("street_name", newAddress.getStreetName());
        address.put("zip_code", newAddress.getZipCode());
        _db.update("Address", address, "street_name = ?", new String[]{oldAddress.getStreetName()});
    }

    public void deleteAddress(Address address){
        _db.delete("Address", "street_name = ?", new String[]{address.getStreetName()});
    }

}
