package com.example.tabletrackfinal;

public class Reservation {
    private int id;
    private String guestName;   // Name of the person booking
    private String date;        // Format: YYYY-MM-DD
    private String time;        // Format: HH:MM
    private int tableSize;      // How many people?
    private String status;      // "Confirmed", "Cancelled"

    public Reservation(int id, String guestName, String date, String time, int tableSize, String status) {
        this.id = id;
        this.guestName = guestName;
        this.date = date;
        this.time = time;
        this.tableSize = tableSize;
        this.status = status;
    }

    // Constructor for new reservation
    public Reservation(String guestName, String date, String time, int tableSize) {
        this.guestName = guestName;
        this.date = date;
        this.time = time;
        this.tableSize = tableSize;
        this.status = "Confirmed";
    }

    // Getters
    public int getId() { return id; }
    public String getGuestName() { return guestName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getTableSize() { return tableSize; }
    public String getStatus() { return status; }

    // Setters
    public void setStatus(String status) { this.status = status; }
}
