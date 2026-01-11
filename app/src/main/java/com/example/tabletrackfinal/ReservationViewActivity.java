package com.example.tabletrackfinal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationViewActivity extends AppCompatActivity {
    DatabaseHelper db;
    String resId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_view_customer);
        // Note: You can reuse the customer XML for simplicity, or create a generic one

        db = new DatabaseHelper(this);
        resId = getIntent().getStringExtra("RES_ID");

        TextView tvInfo = findViewById(R.id.textView7); // Assuming this is an info textview in your XML
        Button btnCancel = findViewById(R.id.button2); // Assuming ID for cancel button

        tvInfo.setText("Reservation ID: " + resId + "\n(Details would be fetched here)");

        btnCancel.setOnClickListener(v -> {
            boolean deleted = db.cancelReservation(resId);
            if (deleted) {
                // Check notification setting
                SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                if (prefs.getBoolean("notifications", true)) {
                    Toast.makeText(this, "Notification: Booking Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                }
                finish(); // Close screen
                // Ensure view_Reservations refreshes when you go back (it won't automatically unless we recreate it,
                // but finishing this activity returns to the previous one. The previous one might need a refresh).
                // For simplicity, user can just go back and reopen.
            }
        });
    }
}
