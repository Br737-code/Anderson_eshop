package com.example.andersoneshop;

/** Parent class for every item sold at Anderson's Shop. */
public class Product {

    // protected: children can use these directly; outside classes use getters (encapsulation)
    protected int id;
    protected String productName;
    protected double price;
    protected String category;

    public Product(int id, String productName, double price, String category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    /** Unit the product is sold in. Children override this. */
    public String getUnitLabel() { return "each"; }

    /** Subtotal for a given quantity. Children may override with special pricing. */
    public double calculateSubtotal(int quantity) {
        return price * quantity;
    }

    /** Text shown in the product list. Children override to add specialised detail. */
    public String displayProduct() {
        return productName + " - K" + String.format("%.2f", price);
    }
}
