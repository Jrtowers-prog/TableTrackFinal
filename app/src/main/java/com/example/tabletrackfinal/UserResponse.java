package com.example.tabletrackfinal;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    // Sometimes the API returns a simple message (like for creation)
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}