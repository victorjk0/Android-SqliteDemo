package com.example.sqlite_test.CustomerDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    // Name of the database which will be created
    private static final String _DbName = "ManagementDB.db";
    // Current database version
    private static final int _DbVersion = 1;

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, _DbName, null, _DbVersion); // null is default Factory
    }


    // Fires whenever the class is instantiated.
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }


    // Fires whenever MAJOR changes has been made in the database (major enough for it to actually change the version).
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Creates 3 default tables
    // Customer, Address, Secret
    public void createTables(SQLiteDatabase db){

        db.execSQL("CREATE TABLE Customer(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customer_name TEXT," +
                "customer_description TEXT);");

        db.execSQL("CREATE TABLE Address(Id INTEGER PRIMARY KEY AUTOINCREMENT, street_name TEXT, zip_code INTEGER);");
        db.execSQL("CREATE TABLE Secret(Id INTEGER PRIMARY KEY AUTOINCREMENT, secret_code TEXT, flag INTEGER);");
    }
}

