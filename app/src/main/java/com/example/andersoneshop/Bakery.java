package com.example.andersoneshop;
public class Bakery extends Product {

    private static final int BULK_QUANTITY = 12;
    private static final double BULK_DISCOUNT = 0.10; // 10% off when buying 12 or more

    public Bakery(int id, String productName, double price) {
        super(id, productName, price, "Bakery");
    }

    @Override
    public String getUnitLabel() { return "item"; }

    /** Specialised pricing: bulk discount for large bakery orders. */
    @Override
    public double calculateSubtotal(int quantity) {
        double subtotal = super.calculateSubtotal(quantity);
        if (quantity >= BULK_QUANTITY) {
            subtotal = subtotal * (1 - BULK_DISCOUNT);
        }
        return subtotal;
    }

    @Override
    public String displayProduct() {
        return "Bakery: " + productName + " - K" + String.format("%.2f", price)
                + " (baked fresh daily)";
    }
}