package com.booksharing.apisystem.requests;

public class UpdateUserRequest {
    private String email;
    private String userYear;

    private String username;

    private String password;

    public UpdateUserRequest(String email, String userYear, String username, String password) {
        this.email = email;
        this.userYear = userYear;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserYear() {
        return userYear;
    }

    public void setUserYear(String userYear) {
        this.userYear = userYear;
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
}
