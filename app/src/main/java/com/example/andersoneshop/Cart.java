package com.example.andersoneshop;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> items = new ArrayList<>();

    /** Adds one unit; if the product is already in the cart, increases its quantity. */
    public void addProduct(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }

    /** Reduces quantity by one; removes the line when it reaches zero. */
    public void removeProduct(Product product) {
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            if (item.getProduct().getId() == product.getId()) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    items.remove(i);
                }
                return;
            }
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public ArrayList<CartItem> getItems() { return items; }
    public boolean isEmpty() { return items.isEmpty(); }
    public void clear() { items.clear(); }
}