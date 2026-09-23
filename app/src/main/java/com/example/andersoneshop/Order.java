package com.example.andersoneshop;

import java.util.ArrayList;

public class Order {

    private static int nextOrderNumber = 1001;

    private int orderNumber;
    private Customer customer;
    private ArrayList<CartItem> items;
    private double total;

    /** Checkout: copies the cart contents into a new order. */
    public Order(Customer customer, Cart cart) {
        this.orderNumber = nextOrderNumber++;
        this.customer = customer;
        this.items = new ArrayList<>(cart.getItems());
        this.total = cart.calculateTotal();
    }

    public int getOrderNumber() { return orderNumber; }
    public Customer getCustomer() { return customer; }
    public double getTotal() { return total; }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderNumber).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Phone: ").append(customer.getPhone()).append("\n");
        sb.append("Deliver to: ").append(customer.getDeliveryLocation()).append("\n\n");
        for (CartItem item : items) {
            sb.append(item.getQuantity()).append(" x ")
                    .append(item.getProduct().getProductName())
                    .append("  K").append(String.format("%.2f", item.getSubtotal())).append("\n");
        }
        sb.append("\nTOTAL: K").append(String.format("%.2f", total));
        sb.append("\n(Prototype only - no real payment processed)");
        return sb.toString();
    }
}