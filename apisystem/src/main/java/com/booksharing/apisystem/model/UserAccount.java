package com.booksharing.apisystem.model;

import jakarta.persistence.*;

@Entity
public class UserAccount {
    //Holds the email which is also PK
    @Id @Column(length = 50)
    private String email;
    //Holds the account password
    @Column(length = 50, nullable = false)
    private String pass;
    //Holds the account's academic year i.e Senior, Junior, etc...
    @Column(length = 10)
    private String year;

    //Constructors
    public UserAccount(String email, String pass, String year) {
        this.email = email;
        this.pass = pass;
        this.year = year;
    }
    public UserAccount() {
    }

    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
