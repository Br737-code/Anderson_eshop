package com.example.andersoneshop;

/** Plain Java test (Phase 4): run this BEFORE building the Android screens. */
public class ShopDemo {

    public static void main(String[] args) {

        // Polymorphism: one loop, four different displayProduct() behaviours
        System.out.println("=== PRODUCT LIST ===");
        for (Product p : ProductCatalogue.getAllProducts()) {
            System.out.println(p.displayProduct());
        }

        Cart cart = new Cart();
        cart.addProduct(ProductCatalogue.findById(1));  // Coca-Cola
        cart.addProduct(ProductCatalogue.findById(1));  // Coca-Cola again -> qty 2
        cart.addProduct(ProductCatalogue.findById(11)); // Chicken
        for (int i = 0; i < 12; i++) {
            cart.addProduct(ProductCatalogue.findById(9)); // 12 rolls -> 10% off
        }

        System.out.println("\nCart total: K" + String.format("%.2f", cart.calculateTotal()));
        // Expected: 2x4.50 + 22.00 + (12x1.00 x 0.9 = 10.80) = K41.80

        Customer c = new Customer("Test Customer", "70000000", "Eriku, Lae");
        Order order = new Order(c, cart);
        System.out.println("\n" + order.getSummary());
    }
}