package com.example.andersoneshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private ListView listCart;
    private TextView txtTotal, txtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listCart = findViewById(R.id.listCart);
        txtTotal = findViewById(R.id.txtTotal);
        txtEmpty = findViewById(R.id.txtEmpty);

        findViewById(R.id.btnContinue).setOnClickListener(v -> finish());

        findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            if (ShopData.cart.isEmpty()) {
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, CheckoutActivity.class));
            }
        });

        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    /** Rebuilds the list and total from the shared cart. */
    private void refresh() {
        listCart.setAdapter(new CartAdapter(this));
        txtTotal.setText("Total: K" + String.format("%.2f", ShopData.cart.calculateTotal()));
        txtEmpty.setVisibility(ShopData.cart.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private class CartAdapter extends ArrayAdapter<CartItem> {

        CartAdapter(Context context) {
            super(context, 0, ShopData.cart.getItems());
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_cart, parent, false);
            }
            CartItem item = getItem(position);
            Product product = item.getProduct();

            ((TextView) convertView.findViewById(R.id.txtCartName)).setText(
                    product.getProductName() + "\nK" + String.format("%.2f", product.getPrice())
                            + " x " + item.getQuantity());
            ((TextView) convertView.findViewById(R.id.txtCartSubtotal)).setText(
                    "K" + String.format("%.2f", item.getSubtotal()));

            convertView.findViewById(R.id.btnPlus).setOnClickListener(v -> {
                ShopData.cart.addProduct(product);
                refresh();
            });
            convertView.findViewById(R.id.btnMinus).setOnClickListener(v -> {
                ShopData.cart.removeProduct(product);
                refresh();
            });
            return convertView;
        }
    }
}