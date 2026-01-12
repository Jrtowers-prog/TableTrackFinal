package com.example.tabletrackfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signUp extends AppCompatActivity {
    EditText etUser, etPass;
    // TODO: REPLACE THIS WITH YOUR ACTUAL STUDENT ID
    String STUDENT_ID = "10933286";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUser = findViewById(R.id.usernameInput);
        etPass = findViewById(R.id.passwordInput);
        Button btnCust = findViewById(R.id.customerSignUpButton);
        Button btnStaff = findViewById(R.id.staffSignUpButton);
        ImageView btnHome = findViewById(R.id.home);

        btnCust.setOnClickListener(v -> register("Customer"));
        btnStaff.setOnClickListener(v -> register("Staff"));
        if (btnHome != null) btnHome.setOnClickListener(v -> finish());
    }

    private void register(String type) {
        String username = etUser.getText().toString().trim();
        String password = etPass.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Random email to ensure uniqueness during testing
        int randomNum = new Random().nextInt(100000);
        String uniqueEmail = "user" + randomNum + "@test.com";

        User user = new User(username, password, "Test", "User", uniqueEmail, "000000", type);

        // Pass the STUDENT_ID to the API
        RetrofitClient.getService().registerUser(STUDENT_ID, user).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(signUp.this, "Account Created!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(signUp.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(signUp.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}