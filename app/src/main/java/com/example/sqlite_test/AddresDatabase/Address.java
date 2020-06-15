package com.example.sqlite_test.AddresDatabase;

import androidx.annotation.NonNull;

public class Address {
    private int id;
    private String streetName;
    private String zipCode;

    public Address(int id, String streetName, String zipCode){
        this.id = id;
        this.streetName = streetName;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @NonNull
    @Override
    public String toString() {
        return "Address \'" +
                "ID: " + id + '\'' +
                "Street Name: " + streetName + '\'' +
                "Zip Code: " + zipCode + '\'';
    }
}
