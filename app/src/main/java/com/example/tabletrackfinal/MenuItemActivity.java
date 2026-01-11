package com.example.tabletrackfinal;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MenuItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_customer); // Connects to your existing XML

        // Setup Back Button
        ImageView btnBack = findViewById(R.id.home); // Assuming 'home' acts as back or home
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
    }
}
