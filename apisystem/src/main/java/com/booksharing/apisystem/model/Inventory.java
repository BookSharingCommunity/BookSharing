package com.booksharing.apisystem.model;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
    @Column(nullable = false)
    private String cond;
    @Column(nullable = false)
    private float price;
    @Column
    private String picture;

    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column
    private float version;

    public Inventory(User userId, String cond, float price, String picture, String isbn, String title, float version) {
        this.userId = userId;
        this.cond = cond;
        this.price = price;
        this.picture = picture;
        this.isbn = isbn;
        this.title = title;
        this.version = version;
    }

    public Inventory(){}

    public Long getInvId() {
        return invId;
    }

    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }
}
