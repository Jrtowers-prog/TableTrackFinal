package com.example.tabletrackfinal;

import com.google.gson.annotations.SerializedName;

public class User {
    // These @SerializedName values MUST match the API PDF exactly
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("contact")
    private String contact;

    @SerializedName("usertype")
    private String userType; // This will store "staff" or "guest" (or "student" as per API example)

    // Empty Constructor (needed for some converters)
    public User() {}

    // Constructor for creating a new user
    public User(String username, String password, String firstName, String lastName, String email, String contact, String userType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.userType = userType;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public String getUserType() { return userType; }
}
