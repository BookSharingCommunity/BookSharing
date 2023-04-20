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
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book bookId;
    @Column(nullable = false)
    private String cond;
    @Column(nullable = false)
    private float price;
    @Column
    private String picture;

    public Inventory(Long invId, User userId, Book bookId, String cond, float price, String picture) {
        this.invId = invId;
        this.userId = userId;
        this.bookId = bookId;
        this.cond = cond;
        this.price = price;
        this.picture = picture;
    }

    public Inventory(User userId, Book bookId, String cond, float price, String picture) {
        this.userId = userId;
        this.bookId = bookId;
        this.cond = cond;
        this.price = price;
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

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
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
}
