package com.example.sqlite_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sqlite_test.CustomerDatabase.Customer;
import com.example.sqlite_test.CustomerDatabase.CustomerDatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView custListView;
    private CustomerDatabaseHelper _customerDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custListView = findViewById(R.id.CustList);

        this._customerDatabaseHelper = CustomerDatabaseHelper.getInstance(getApplicationContext());

        // Insert Test data in SQlite Database
        this._customerDatabaseHelper.open();
        this._customerDatabaseHelper.insertCustomer(new Customer(-1, "DingoDjango", "En vældig fin fella"));
        this._customerDatabaseHelper.close();

    }

    private List<Customer> getCustomers(){

        CustomerDatabaseHelper.open();
        List<Customer> custList = CustomerDatabaseHelper.getCustomers();
        CustomerDatabaseHelper.close();

        return custList;
    }

    public void SelectCust(View view) {

       List<Customer> custList =  getCustomers();
        ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(MainActivity.this, R.layout.cust_item, R.id.custToString,custList);
        custListView.setAdapter(customerArrayAdapter);
    }
}
