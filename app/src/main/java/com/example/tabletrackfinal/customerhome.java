package com.example.tabletrackfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class customerhome extends AppCompatActivity {

    // 1. Declare the buttons
    private Button viewMenuButton;
    private Button viewReservationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customerhome); // Make sure this matches your XML filename

        // Handle Window Insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. Initialize the buttons using IDs from your XML
        viewMenuButton = findViewById(R.id.viewMenuButton);
        viewReservationButton = findViewById(R.id.viewReservationButton);

        // 3. Set Click Listener for Menu
        viewMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change "MenuActivity.class" to the actual name of your Menu java file
                Intent intent = new Intent(customerhome.this, Menu.class);
                startActivity(intent);
            }
        });

        // 4. Set Click Listener for Reservations
        viewReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change "ReservationActivity.class" to the actual name of your Reservation java file
                Intent intent = new Intent(customerhome.this, view_Reservations.class);
                startActivity(intent);
            }
        });
    } 
}

