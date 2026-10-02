package com.example.andersoneshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Names must match the category text used in the Product classes
        findViewById(R.id.btnDrinks).setOnClickListener(v -> openCategory("Drinks"));
        findViewById(R.id.btnFruit).setOnClickListener(v -> openCategory("Fruit & Vegetables"));
        findViewById(R.id.btnBakery).setOnClickListener(v -> openCategory("Bakery"));
        findViewById(R.id.btnButchery).setOnClickListener(v -> openCategory("Butchery"));
        findViewById(R.id.btnViewCart).setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));
    }

    private void openCategory(String category) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
