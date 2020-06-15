package com.example.sqlite_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite_test.CustomerDatabase.Customer;
import com.example.sqlite_test.CustomerDatabase.CustomerDatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView custListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custListView = findViewById(R.id.CustList);

        CustomerDatabaseHelper databaseHelper = new CustomerDatabaseHelper(MainActivity.this);

        Customer cust = new Customer(-1,"Long John", "A wild Cowboy from the wild wild west");

        boolean success = databaseHelper.addOne(cust);

        Toast.makeText(MainActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();



    }

    public void SelectCust(View view) {
        CustomerDatabaseHelper databaseHelperNew = new CustomerDatabaseHelper(MainActivity.this);

        List<Customer> customerList = databaseHelperNew.getCustomers();

        ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(MainActivity.this, R.layout.cust_item, R.id.custToString,customerList);

        custListView.setAdapter(customerArrayAdapter);

    }
}
