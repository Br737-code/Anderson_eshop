package com.example.andersoneshop;

/** One line in the cart: a product and how many the customer wants. */
public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /** Polymorphism: the correct calculateSubtotal() runs for each product type. */
    public double getSubtotal() {
        return product.calculateSubtotal(quantity);
    }
}