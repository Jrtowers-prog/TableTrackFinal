package com.example.tabletrackfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> menuList;
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.menuListView);
        ImageView btnHome = findViewById(R.id.home);
        ImageView btnSettings = findViewById(R.id.settings);
        FloatingActionButton fabAdd = findViewById(R.id.fabAddMenu);

        // Check Role
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String role = prefs.getString("role", "Guest");

        if ("Staff".equalsIgnoreCase(role)) {
            fabAdd.setVisibility(View.VISIBLE);
        } else {
            fabAdd.setVisibility(View.GONE);
        }

        fabAdd.setOnClickListener(v -> startActivity(new Intent(this, edit_menu_item_staff.class)));
        btnHome.setOnClickListener(v -> finish());
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));

        loadMenu();
    }

    private void loadMenu() {
        menuList = new ArrayList<>();
        Cursor cursor = db.getAllMenuItems();

        if (cursor.getCount() == 0) {
            menuList.add("No items found");
        } else {
            while (cursor.moveToNext()) {
                // Fetch columns 1 (Name) and 3 (Price) from DatabaseHelper
                String name = cursor.getString(1);
                double price = cursor.getDouble(3);
                menuList.add(name + " - £" + price);
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMenu(); // Refresh list if we added an item and came back
    }
}
