package com.booksharing.apisystem.model;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    private float rating;

    private String content;

    public Review() {}

    public Review(String serviceType, User userId, float rating, String content) {
        this.serviceType = serviceType;
        this.userId = userId;
        this.rating = rating;
        this.content = content;
    }

    public Review(String serviceType, User userId, float rating) {
        this.serviceType = serviceType;
        this.userId = userId;
        this.rating = rating;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
