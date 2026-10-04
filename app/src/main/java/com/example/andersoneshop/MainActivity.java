package com.example.andersoneshop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // No need for EdgeToEdge.enable(this) if it's causing issues with simple layouts
        setContentView(R.layout.activity_main);

        // View Products -> product list showing everything
        findViewById(R.id.btnProducts).setOnClickListener(v -> {
            Intent intent = new Intent(this, ProductActivity.class);
            intent.putExtra("category", "All");
            startActivity(intent);
        });

        findViewById(R.id.btnCategories).setOnClickListener(v ->
                startActivity(new Intent(this, CategoryActivity.class)));

        findViewById(R.id.btnCart).setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));

        CheckBox cbDarkTheme = findViewById(R.id.cbDarkTheme);
        
        // Initialize checkbox state based on current theme
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        cbDarkTheme.setChecked(currentMode == AppCompatDelegate.MODE_NIGHT_YES);

        cbDarkTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }
}