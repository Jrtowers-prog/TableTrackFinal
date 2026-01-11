package com.example.tabletrackfinal;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    // Used when fetching a user
    @SerializedName("user")
    private User user;

    // Used for success messages
    @SerializedName("message")
    private String message;

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}