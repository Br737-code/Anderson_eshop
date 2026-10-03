package com.example.andersoneshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private TextView txtItemsCount, txtCartTotal;
    private String currentCategory = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        String categoryIntent = getIntent().getStringExtra("category");
        if (categoryIntent != null) currentCategory = categoryIntent;

        txtItemsCount = findViewById(R.id.txtItemsCount);
        txtCartTotal = findViewById(R.id.txtCartTotal);
        
        setupCategoryChips();
        updateProductList();

        findViewById(R.id.btnGoToCart).setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));

        updateBottomBar();
    }

    private void setupCategoryChips() {
        findViewById(R.id.chipAll).setOnClickListener(v -> filterByCategory("All"));
        findViewById(R.id.chipProduce).setOnClickListener(v -> filterByCategory("Produce"));
        findViewById(R.id.chipBakery).setOnClickListener(v -> filterByCategory("Bakery"));
        findViewById(R.id.chipDairy).setOnClickListener(v -> filterByCategory("Dairy"));
    }

    private void filterByCategory(String category) {
        currentCategory = category;
        updateProductList();
    }

    private void updateProductList() {
        ArrayList<Product> products;
        if (currentCategory.equals("All")) {
            products = ProductCatalogue.getAllProducts();
        } else {
            products = ProductCatalogue.getByCategory(currentCategory);
        }

        ListView listView = findViewById(R.id.listProducts);
        adapter = new ProductAdapter(this, products);
        listView.setAdapter(adapter);
    }

    public void updateBottomBar() {
        int totalItems = 0;
        for (CartItem item : ShopData.cart.getItems()) {
            totalItems += item.getQuantity();
        }
        txtItemsCount.setText(String.format(Locale.getDefault(), "%d items", totalItems));
        txtCartTotal.setText(String.format(Locale.getDefault(), "K%.2f", ShopData.cart.calculateTotal()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBottomBar();
        if (adapter != null) adapter.notifyDataSetChanged();
    }

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

            ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
            TextView txtName = convertView.findViewById(R.id.txtProductName);
            TextView txtPrice = convertView.findViewById(R.id.txtPrice);
            TextView txtPerUnit = convertView.findViewById(R.id.txtPerUnit);
            TextView txtDesc = convertView.findViewById(R.id.txtDescription);
            Button btnAdd = convertView.findViewById(R.id.btnAddToCart);
            LinearLayout layoutQty = convertView.findViewById(R.id.layoutQuantity);
            TextView txtQtyValue = convertView.findViewById(R.id.txtQuantity);
            ImageButton btnPlus = convertView.findViewById(R.id.btnPlus);
            ImageButton btnMinus = convertView.findViewById(R.id.btnMinus);

            // Set Data
            imgProduct.setImageResource(product.getImageResId());
            txtName.setText(product.getProductName());
            txtPrice.setText(String.format(Locale.getDefault(), "K%.2f", product.getPrice()));
            txtPerUnit.setText("per " + product.getUnitLabel());
            txtDesc.setText(product.getDescription());

            // Cart Logic for UI State
            CartItem cartItem = null;
            for (CartItem item : ShopData.cart.getItems()) {
                if (item.getProduct().getId() == product.getId()) {
                    cartItem = item;
                    break;
                }
            }

            if (cartItem != null && cartItem.getQuantity() > 0) {
                btnAdd.setVisibility(View.GONE);
                layoutQty.setVisibility(View.VISIBLE);
                txtQtyValue.setText(String.valueOf(cartItem.getQuantity()));
            } else {
                btnAdd.setVisibility(View.VISIBLE);
                layoutQty.setVisibility(View.GONE);
            }

            btnAdd.setOnClickListener(v -> {
                ShopData.cart.addProduct(product);
                notifyDataSetChanged();
                updateBottomBar();
            });

            btnPlus.setOnClickListener(v -> {
                ShopData.cart.addProduct(product);
                notifyDataSetChanged();
                updateBottomBar();
            });

            btnMinus.setOnClickListener(v -> {
                ShopData.cart.removeProduct(product);
                notifyDataSetChanged();
                updateBottomBar();
            });

            return convertView;
        }
    }
}