package com.example.andersoneshop;
public class Butchery extends Product {

    private double packWeightKg;

    public Butchery(int id, String productName, double price, double packWeightKg) {
        super(id, productName, price, "Butchery");
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