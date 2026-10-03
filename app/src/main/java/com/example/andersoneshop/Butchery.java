package com.example.andersoneshop;

public class Butchery extends Product {

    private double packWeightKg;

    public Butchery(int id, String productName, String description, double price, double packWeightKg, int imageResId) {
        super(id, productName, description, price, "Butchery", imageResId);
        this.packWeightKg = packWeightKg;
    }

    public double getPackWeightKg() { return packWeightKg; }

    @Override
    public String getUnitLabel() { return "pack"; }

    @Override
    public String displayProduct() {
        return "Meat: " + productName + " (" + packWeightKg + "kg pack) - K"
                + String.format("%.2f", price);
    }
}
