package com.example.andersoneshop;

import java.util.ArrayList;

/** Holds the shop's products. Pointing to the new images in res/drawable. */
public class ProductCatalogue {

    private static ArrayList<Product> products = new ArrayList<>();

    static {
        // Generic placeholder for missing items
        int placeholder = android.R.drawable.ic_menu_gallery;

        // Produce (Fruit & Vegetables)
        products.add(new FruitVegetable(1, "Organic Hass Avocados", "Ripe & ready • 2 count", 4.49, "pack",  R.drawable.avocado));
        products.add(new FruitVegetable(2, "Heirloom Tomatoes", "Local harvest • 1 lb", 5.29, "lb", R.drawable.tomatoe));
        products.add(new FruitVegetable(3, "Fresh Bananas", "Sweet and yellow", 4.00, "kg", R.drawable.banana));
        products.add(new FruitVegetable(13, "Aibika", "Fresh local greens", 2.00, "bunch", R.drawable.aibika));
        products.add(new FruitVegetable(14, "Kaukau (Sweet Potato)", "Local staple", 3.50, "kg", R.drawable.kaukau));
        
        // Bakery
        products.add(new Bakery(4, "Sourdough Country Loaf", "Baked this morning • 24 oz", 6.50, R.drawable.sourdough));
        products.add(new Bakery(5, "White Bread Loaf", "Soft and fresh", 5.00, R.drawable.bread));
        products.add(new Bakery(6, "Dinner Rolls", "12 pack", 1.00, R.drawable.dinner_rolls));
        products.add(new Bakery(15, "Meat Pie", "Warm and flaky", 6.50, R.drawable.pie));
        
        // Dairy
        products.add(new Product(7, "Pasture-Raised Eggs", "Large brown eggs • dozen", 7.99, "Dairy",  R.drawable.egg));
        products.add(new Product(8, "Organic Whole Milk", "1 Gallon", 5.50, "Dairy",  R.drawable.milk));

        // Butchery
        products.add(new Butchery(9, "Chicken Pieces", "Fresh organic chicken", 22.00, 1.0, R.drawable.chicken_pieces));
        products.add(new Butchery(10, "Beef Mince", "90% Lean", 28.00, 1.0, R.drawable.beef_mince));
        products.add(new Butchery(16, "Lamb Flaps", "Tender local cut", 18.00, 1.0, R.drawable.lamb_flaps));
        
        // Drinks
        products.add(new Drink(11, "Coca-Cola", "Refreshing soda", 4.50, 600, R.drawable.cola));
        products.add(new Drink(12, "Orange Juice", "100% Pure", 7.00, 1000, R.drawable.orange_juice));
        products.add(new Drink(17, "TruTru Wara", "Pure PNG water", 2.50, 1500, R.drawable.water));
    }

    public static ArrayList<Product> getAllProducts() { return products; }

    public static ArrayList<Product> getByCategory(String category) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            } else if (category.equalsIgnoreCase("Produce") && p.getCategory().contains("Fruit")) {
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
