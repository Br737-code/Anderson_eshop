package com.example.andersoneshop;
public class Drink extends Product {

    private int sizeMl;

    public Drink(int id, String productName, String description, double price, int sizeMl, int imageResId) {
        super(id, productName, description, price, "Drinks", imageResId);
        this.sizeMl = sizeMl;
    }

    public int getSizeMl() { return sizeMl; }

    @Override
    public String getUnitLabel() { return "bottle/can"; }

    @Override
    public String displayProduct() {
        return "Drink: " + productName + " (" + sizeMl + "ml) - K" + String.format("%.2f", price);
    }
}