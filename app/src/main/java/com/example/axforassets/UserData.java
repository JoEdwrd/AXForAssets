package com.example.axforassets;

public class UserData {
    private static UserData instance;
    private String username;

    private UserData() {}

    public static synchronized UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
