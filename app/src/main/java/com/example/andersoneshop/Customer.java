package com.example.andersoneshop;

public class Customer {

    private String name;
    private String phone;
    private String deliveryLocation;

    public Customer(String name, String phone, String deliveryLocation) {
        this.name = name;
        this.phone = phone;
        this.deliveryLocation = deliveryLocation;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getDeliveryLocation() { return deliveryLocation; }
}