package com.booksharing.apisystem.model;

import jakarta.persistence.*;

@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long threadId;

    @ManyToOne
    @JoinColumn(name = "buyerId")
    private User buyerId;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private User sellerId;

    public Thread() {}

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public User getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(User buyerId) {
        this.buyerId = buyerId;
    }

    public User getSellerId() {
        return sellerId;
    }

    public void setSellerId(User sellerId) {
        this.sellerId = sellerId;
    }
}
