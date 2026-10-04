package com.example.andersoneshop;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Locale;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private TextView txtItemsCount, txtCartTotal;
    private String currentCategory = "All";
    private String currentSearch = "";
    private Double minPrice = null, maxPrice = null;
    
    private MaterialButton chipAll, chipProduce, chipBakery, chipDairy, chipDrinks;
    private EditText edtSearch, edtMinPrice, edtMaxPrice;
    private LinearLayout layoutPriceFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        String categoryIntent = getIntent().getStringExtra("category");
        if (categoryIntent != null) currentCategory = categoryIntent;
        if (currentCategory.equals("Fruit & Vegetables")) currentCategory = "Produce";

        txtItemsCount = findViewById(R.id.txtItemsCount);
        txtCartTotal = findViewById(R.id.txtCartTotal);
        edtSearch = findViewById(R.id.edtSearch);
        edtMinPrice = findViewById(R.id.edtMinPrice);
        edtMaxPrice = findViewById(R.id.edtMaxPrice);
        layoutPriceFilter = findViewById(R.id.layoutPriceFilter);
        
        chipAll = findViewById(R.id.chipAll);
        chipProduce = findViewById(R.id.chipProduce);
        chipBakery = findViewById(R.id.chipBakery);
        chipDairy = findViewById(R.id.chipDairy);
        chipDrinks = findViewById(R.id.chipDrinks);
        
        setupCategoryChips();
        setupSearch();
        setupPriceFilter();
        
        updateProductList();
        updateChipStyles();

        findViewById(R.id.btnGoToCart).setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));

        updateBottomBar();
    }

    private void setupSearch() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearch = s.toString().toLowerCase().trim();
                updateProductList();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupPriceFilter() {
        findViewById(R.id.btnFilter).setOnClickListener(v -> {
            if (layoutPriceFilter.getVisibility() == View.VISIBLE) {
                layoutPriceFilter.setVisibility(View.GONE);
            } else {
                layoutPriceFilter.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.btnApplyPrice).setOnClickListener(v -> {
            String minStr = edtMinPrice.getText().toString();
            String maxStr = edtMaxPrice.getText().toString();
            
            try {
                minPrice = minStr.isEmpty() ? null : Double.parseDouble(minStr);
                maxPrice = maxStr.isEmpty() ? null : Double.parseDouble(maxStr);
            } catch (NumberFormatException e) {
                minPrice = null;
                maxPrice = null;
            }
            updateProductList();
        });
    }

    private void setupCategoryChips() {
        chipAll.setOnClickListener(v -> filterByCategory("All"));
        chipProduce.setOnClickListener(v -> filterByCategory("Produce"));
        chipBakery.setOnClickListener(v -> filterByCategory("Bakery"));
        chipDairy.setOnClickListener(v -> filterByCategory("Dairy"));
        chipDrinks.setOnClickListener(v -> filterByCategory("Drinks"));
    }

    private void filterByCategory(String category) {
        currentCategory = category;
        updateProductList();
        updateChipStyles();
    }

    private void updateChipStyles() {
        setChipSelected(chipAll, currentCategory.equals("All"));
        setChipSelected(chipProduce, currentCategory.equals("Produce"));
        setChipSelected(chipBakery, currentCategory.equals("Bakery"));
        setChipSelected(chipDairy, currentCategory.equals("Dairy"));
        setChipSelected(chipDrinks, currentCategory.equals("Drinks"));
    }

    private void setChipSelected(MaterialButton chip, boolean isSelected) {
        if (isSelected) {
            chip.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#356947")));
            chip.setTextColor(Color.WHITE);
            chip.setStrokeWidth(0);
        } else {
            chip.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            chip.setTextColor(Color.parseColor("#5C6059"));
            chip.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#E1E3DA")));
            chip.setStrokeWidth(1);
        }
    }

    private void updateProductList() {
        ArrayList<Product> products = ProductCatalogue.getAllProducts();
        ArrayList<Product> filtered = new ArrayList<>();

        for (Product p : products) {
            // Category Filter
            boolean matchCategory = currentCategory.equals("All") || 
                    p.getCategory().equalsIgnoreCase(currentCategory) ||
                    (currentCategory.equals("Produce") && p.getCategory().contains("Fruit"));
            
            // Search Filter
            boolean matchSearch = currentSearch.isEmpty() || 
                    p.getProductName().toLowerCase().contains(currentSearch) ||
                    p.getDescription().toLowerCase().contains(currentSearch);
            
            // Price Filter
            boolean matchPrice = (minPrice == null || p.getPrice() >= minPrice) &&
                                 (maxPrice == null || p.getPrice() <= maxPrice);

            if (matchCategory && matchSearch && matchPrice) {
                filtered.add(p);
            }
        }

        ListView listView = findViewById(R.id.listProducts);
        adapter = new ProductAdapter(this, filtered);
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
            if (product == null) return convertView;

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

            imgProduct.setImageResource(product.getImageResId());
            txtName.setText(product.getProductName());
            txtPrice.setText(String.format(Locale.getDefault(), "K%.2f", product.getPrice()));
            txtPerUnit.setText("per " + product.getUnitLabel());
            txtDesc.setText(product.getDescription());

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
