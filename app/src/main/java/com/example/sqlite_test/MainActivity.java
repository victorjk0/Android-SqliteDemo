package com.example.sqlite_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite_test.CustomerDatabase.Customer;
import com.example.sqlite_test.CustomerDatabase.CustomerDatabaseHelper;
import com.example.sqlite_test.CustomerDatabase.OldCustomerDatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView custListView;
    private CustomerDatabaseHelper _customerDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custListView = findViewById(R.id.CustList);

        //OldCustomerDatabaseHelper databaseHelper = new OldCustomerDatabaseHelper(MainActivity.this);

        //Customer cust = new Customer(-1,"Long John", "A wild Cowboy from the wild wild west");

        //boolean success = databaseHelper.addOne(cust);

       // Toast.makeText(MainActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();


        this._customerDatabaseHelper = CustomerDatabaseHelper.getInstance(getApplicationContext());

        this._customerDatabaseHelper.insertCustomer(new Customer(-1, "DingoDjango", "En vældig fin fella"));



    }

    private List<Customer> getCustomers(){
        CustomerDatabaseHelper.open();
        List<Customer> custList = CustomerDatabaseHelper.getCustomers();
        CustomerDatabaseHelper.close();
        return custList;
    }

    public void SelectCust(View view) {
       // OldCustomerDatabaseHelper databaseHelperNew = new OldCustomerDatabaseHelper(MainActivity.this);

       // List<Customer> customerList = databaseHelperNew.getCustomers();

       List<Customer> custList =  getCustomers();

        ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(MainActivity.this, R.layout.cust_item, R.id.custToString,custList);

        custListView.setAdapter(customerArrayAdapter);

    }
}
