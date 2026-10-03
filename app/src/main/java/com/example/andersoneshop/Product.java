package com.example.andersoneshop;

/** Parent class for every item sold at Anderson's Shop. */
public class Product {

    protected int id;
    protected String productName;
    protected String description; // Added for more detail
    protected double price;
    protected String category;
    protected int imageResId;

    public Product(int id, String productName, String description, double price, String category, int imageResId) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageResId = imageResId;
    }

    public int getId() { return id; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getImageResId() { return imageResId; }

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
