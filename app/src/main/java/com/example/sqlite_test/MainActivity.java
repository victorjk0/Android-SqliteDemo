package com.example.sqlite_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sqlite_test.AddresDatabase.Address;
import com.example.sqlite_test.AddresDatabase.AddressDatabaseHelper;
import com.example.sqlite_test.CustomerDatabase.Customer;
import com.example.sqlite_test.CustomerDatabase.CustomerDatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView custListView;
    private CustomerDatabaseHelper _customerDatabaseHelper;
    private AddressDatabaseHelper _addressDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custListView = findViewById(R.id.CustList);

        _customerDatabaseHelper = CustomerDatabaseHelper.getInstance(getApplicationContext());

        // Insert Customer Test data in SQlite
        CustomerDatabaseHelper.open();
        _customerDatabaseHelper.insertCustomer(new Customer(-1, "DingoDjango", "En vældig fin fella"));
        CustomerDatabaseHelper.close();

        //Insert Address Data in SQLite for Test
        AddressDatabaseHelper.open();
        _addressDatabaseHelper.insertAddress(new Address(-1, "BingBongvej 42", "4308"));
        AddressDatabaseHelper.close();

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

    private List<Address> getAddresses(){
        AddressDatabaseHelper.open();
        List<Address> addList = AddressDatabaseHelper.getAddresses();
        AddressDatabaseHelper.close();

        return addList;
    }
    public void SelectAddress(View view) {
        List<Address> addList = getAddresses();
        ArrayAdapter addressArrayAdapter = new ArrayAdapter<Address>(MainActivity.this, R.layout.cust_item, R.id.custToString,addList);
        custListView.setAdapter(addressArrayAdapter);
    }
}
