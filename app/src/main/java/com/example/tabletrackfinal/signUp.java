package com.example.tabletrackfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signUp extends AppCompatActivity {
    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUser = findViewById(R.id.usernameInput);
        etPass = findViewById(R.id.passwordInput);
        Button btnCust = findViewById(R.id.customerSignUpButton);
        Button btnStaff = findViewById(R.id.staffSignUpButton);
        ImageView btnHome = findViewById(R.id.home); // Navigation

        btnCust.setOnClickListener(v -> register("Customer"));
        btnStaff.setOnClickListener(v -> register("Staff"));
        btnHome.setOnClickListener(v -> finish()); // Go back
    }

    private void register(String type) {
        String username = etUser.getText().toString();
        String password = etPass.getText().toString(); // In real app, hash this!

        // Create user with basic info (Student ID placeholders used for name/email)
        User user = new User(username, password, "Test", "User", "email@test.com", "000", type);

        RetrofitClient.getService().registerUser(user).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(signUp.this, "Account Created!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(signUp.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(signUp.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}