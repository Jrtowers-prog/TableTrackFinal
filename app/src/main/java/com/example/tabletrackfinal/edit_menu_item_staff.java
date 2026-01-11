package com.example.tabletrackfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class edit_menu_item_staff extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_item_staff);

        db = new DatabaseHelper(this);
        EditText etName = findViewById(R.id.etName);
        EditText etDesc = findViewById(R.id.etDesc);
        EditText etPrice = findViewById(R.id.etPrice);
        Button btnSave = findViewById(R.id.btnSaveItem);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String desc = etDesc.getText().toString();
            String priceStr = etPrice.getText().toString();

            if (name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);
            // Default image URI for simplicity
            boolean success = db.addMenuItem(name, desc, price, "android.resource://default");

            if (success) {
                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                finish(); // Go back to Menu
            } else {
                Toast.makeText(this, "Error Adding Item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}