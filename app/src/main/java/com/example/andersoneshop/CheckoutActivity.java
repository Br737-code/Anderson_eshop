package com.example.andersoneshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private EditText edtName, edtPhone, edtLocation;
    private TextView txtSummary, txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtLocation = findViewById(R.id.edtLocation);
        txtSummary = findViewById(R.id.txtSummary);
        txtTotal = findViewById(R.id.txtTotal);

        updateOrderSummary();

        findViewById(R.id.btnPlaceOrder).setOnClickListener(v -> placeOrder());
    }

    private void updateOrderSummary() {
        if (ShopData.cart.isEmpty()) {
            txtSummary.setText("Your cart is empty");
            txtTotal.setText("K0.00");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (CartItem item : ShopData.cart.getItems()) {
            sb.append(String.format(Locale.US, "%d x %s\n",
                    item.getQuantity(), item.getProduct().getProductName()));
            sb.append(String.format(Locale.US, "   K%.2f\n", item.getSubtotal()));
        }
        
        txtSummary.setText(sb.toString().trim());
        txtTotal.setText(String.format(Locale.US, "K%.2f", ShopData.cart.calculateTotal()));
    }

    private void placeOrder() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String location = edtLocation.getText().toString().trim();

        // Input validation
        if (name.isEmpty()) { edtName.setError("Enter your name"); return; }
        if (phone.isEmpty() || phone.length() < 7) { edtPhone.setError("Enter a valid phone number"); return; }
        if (location.isEmpty()) { edtLocation.setError("Enter delivery location"); return; }
        
        if (ShopData.cart.isEmpty()) {
            Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Customer customer = new Customer(name, phone, location);
        ShopData.lastOrder = new Order(customer, ShopData.cart);
        
        // Cart will be cleared in the confirmation screen or after successful transition
        // to allow the confirmation screen to show what was ordered.

        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
