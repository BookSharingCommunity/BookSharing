package com.booksharing.apisystem.requests;

public class NewInventoryRequest {
    private long bookId;
    private long userId;
    private String cond;
    private float price;
    private byte[] pfp;

    public NewInventoryRequest(long userId, long bookId, String cond, float price, byte[] pfp){
        this.bookId = bookId;
        this.userId = userId;
        this.cond = cond;
        this.price = price;
        this.pfp = pfp;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public byte[] getPfp() {
        return pfp;
    }

    public void setPfp(byte[] pfp) {
        this.pfp = pfp;
    }
}
