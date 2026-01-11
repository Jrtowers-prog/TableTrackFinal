package com.example.tabletrackfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {
    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etUser = findViewById(R.id.usernameInput);
        etPass = findViewById(R.id.passwordInput);
        Button btnCust = findViewById(R.id.customerLoginButton);
        Button btnStaff = findViewById(R.id.staffLoginButton);
        ImageView btnHome = findViewById(R.id.home);

        btnCust.setOnClickListener(v -> login());
        btnStaff.setOnClickListener(v -> login());
        btnHome.setOnClickListener(v -> finish());
    }

    private void login() {
        String username = etUser.getText().toString();
        String password = etPass.getText().toString();

        RetrofitClient.getService().getUser(username).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body().getUser();
                    if (user != null && user.getPassword().equals(password)) {

                        // SAVE SESSION DATA
                        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("username", user.getUsername());
                        editor.putString("role", user.getUserType()); // "Staff" or "Customer"
                        editor.apply();

                        // Redirect based on Role
                        if ("Staff".equalsIgnoreCase(user.getUserType())) {
                            startActivity(new Intent(LogIn.this, staffhome.class));
                        } else {
                            startActivity(new Intent(LogIn.this, customerhome.class));
                        }
                    } else {
                        Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogIn.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LogIn.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}