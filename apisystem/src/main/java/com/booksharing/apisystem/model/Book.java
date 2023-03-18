package com.booksharing.apisystem.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column
    private float version;

    public Book(Long bookId, String isbn, String title, float version) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.version = version;
    }
    public Book(){}

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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
