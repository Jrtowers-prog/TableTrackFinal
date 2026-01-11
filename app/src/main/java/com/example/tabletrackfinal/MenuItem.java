package com.example.tabletrackfinal;

public class MenuItem {
    private int id;             // Database ID
    private String name;
    private String description;
    private double price;
    private String imageUri;    // We will store the path to the image as a string

    // Constructor
    public MenuItem(int id, String name, String description, double price, String imageUri) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUri = imageUri;
    }

    // Constructor without ID (for creating new items before saving to DB)
    public MenuItem(String name, String description, double price, String imageUri) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUri = imageUri;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
}