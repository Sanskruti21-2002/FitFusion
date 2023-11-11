package com.sanskruti.myapplication;

public class User {
    private String username;
    private String password;
    // Add other fields as needed for user information

    public User() {
        // Default constructor
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // Initialize other fields as needed
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Add getters and setters for other fields as needed
}
