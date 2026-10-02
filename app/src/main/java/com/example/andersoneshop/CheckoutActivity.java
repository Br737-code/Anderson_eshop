package com.example.andersoneshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private EditText edtName, edtPhone, edtLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtLocation = findViewById(R.id.edtLocation);

        // Order summary
        StringBuilder sb = new StringBuilder();
        for (CartItem item : ShopData.cart.getItems()) {
            sb.append(item.getQuantity()).append(" x ")
                    .append(item.getProduct().getProductName())
                    .append("   K").append(String.format("%.2f", item.getSubtotal())).append("\n");
        }
        sb.append("\nTOTAL: K").append(String.format("%.2f", ShopData.cart.calculateTotal()));
        ((TextView) findViewById(R.id.txtSummary)).setText(sb.toString());

        findViewById(R.id.btnPlaceOrder).setOnClickListener(v -> placeOrder());
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
        ShopData.cart.clear();

        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
