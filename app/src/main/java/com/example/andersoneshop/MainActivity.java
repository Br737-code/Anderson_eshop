package com.example.andersoneshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1. Get saved theme preference (default to Light)
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("dark_mode", false);

        // 2. Apply theme before super.onCreate to ensure it opens correctly
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
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
        
        // 3. Initialize checkbox state correctly
        cbDarkTheme.setChecked(isDark);

        cbDarkTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save preference and apply immediately
            prefs.edit().putBoolean("dark_mode", isChecked).apply();
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }
}