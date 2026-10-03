package com.example.andersoneshop;

public class FruitVegetable extends Product {

    private String unit; // e.g. "kg", "bunch", "each"

    public FruitVegetable(int id, String productName, String description, double price, String unit, int imageResId) {
        super(id, productName, description, price, "Fruit & Vegetables", imageResId);
        this.unit = unit;
    }

    @Override
    public String getUnitLabel() { return unit; }

    @Override
    public String displayProduct() {
        return "Fresh: " + productName + " - K" + String.format("%.2f", price) + " per " + unit;
    }
}