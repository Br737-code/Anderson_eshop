package com.example.andersoneshop;

import java.util.ArrayList;

/** Holds the shop's products. Prices are SAMPLE values: replace with Anderson's real stock. */
public class ProductCatalogue {

    private static ArrayList<Product> products = new ArrayList<>();

    static {
        // Drinks
        products.add(new Drink(1, "Coca-Cola", 4.50, 600));
        products.add(new Drink(2, "Mount Hagen Water", 2.50, 1500));
        products.add(new Drink(3, "Orange Juice", 7.00, 1000));
        // Fruit & Vegetables
        products.add(new FruitVegetable(4, "Bananas", 4.00, "kg"));
        products.add(new FruitVegetable(5, "Tomatoes", 8.00, "kg"));
        products.add(new FruitVegetable(6, "Kaukau (Sweet Potato)", 3.50, "kg"));
        products.add(new FruitVegetable(7, "Aibika", 2.00, "bunch"));
        // Bakery
        products.add(new Bakery(8, "White Bread Loaf", 5.00));
        products.add(new Bakery(9, "Dinner Rolls", 1.00));
        products.add(new Bakery(10, "Meat Pie", 6.50));
        // Butchery
        products.add(new Butchery(11, "Chicken Pieces", 22.00, 1.0));
        products.add(new Butchery(12, "Beef Mince", 28.00, 1.0));
        products.add(new Butchery(13, "Lamb Flaps", 18.00, 1.0));
    }

    public static ArrayList<Product> getAllProducts() { return products; }

    public static ArrayList<Product> getByCategory(String category) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equals(category)) {
                result.add(p);
            }
        }
        return result;
    }

    public static Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}
