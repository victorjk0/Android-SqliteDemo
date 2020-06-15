package com.example.sqlite_test.CustomerDatabase;

public class Customer {
    private int id;
    private String name;
    private String description;

    //Constructor
    public Customer(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Customer\n" +
                "ID= " + id +
                "\nName= "+name +'\'' +
                "\nDescription= " + description;
    }
}


