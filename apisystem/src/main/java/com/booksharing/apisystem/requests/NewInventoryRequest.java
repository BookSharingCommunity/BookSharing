package com.booksharing.apisystem.requests;

public class NewInventoryRequest {
    private long bookId;
    private String username;
    private String cond;
    private float price;
    private String picture;
    private String isbn;
    private String title;

    private float version;

    public NewInventoryRequest(long bookId, String username, String cond, float price, String picture, String isbn, String title, float version) {
        this.bookId = bookId;
        this.username = username;
        this.cond = cond;
        this.price = price;
        this.picture = picture;
        this.isbn = isbn;
        this.title = title;
        this.version = version;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
