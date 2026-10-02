package com.example.andersoneshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        String category = getIntent().getStringExtra("category");
        if (category == null) category = "All";

        ArrayList<Product> products;
        if (category.equals("All")) {
            products = ProductCatalogue.getAllProducts();
        } else {
            products = ProductCatalogue.getByCategory(category);
        }

        ((TextView) findViewById(R.id.txtCategoryTitle)).setText(category);
        ListView listView = findViewById(R.id.listProducts);
        listView.setAdapter(new ProductAdapter(this, products));

        findViewById(R.id.btnGoToCart).setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));
    }

    /** Draws one row per product. */
    private class ProductAdapter extends ArrayAdapter<Product> {

        ProductAdapter(Context context, ArrayList<Product> items) {
            super(context, 0, items);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_product, parent, false);
            }
            Product product = getItem(position);

            TextView txtProduct = convertView.findViewById(R.id.txtProduct);
            Button btnAdd = convertView.findViewById(R.id.btnAdd);

            // Polymorphism: each product type builds its own display text
            txtProduct.setText(product.displayProduct());

            btnAdd.setOnClickListener(v -> {
                ShopData.cart.addProduct(product);
                Toast.makeText(getContext(),
                        product.getProductName() + " added to cart",
                        Toast.LENGTH_SHORT).show();
            });
            return convertView;
        }
    }
}