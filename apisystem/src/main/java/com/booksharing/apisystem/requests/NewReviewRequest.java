package com.booksharing.apisystem.requests;

public class NewReviewRequest {
    private String username;
    private float rating;
    private String service;

    public NewReviewRequest(String username, float rating, String service) {
        this.username = username;
        this.rating = rating;
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
