package com.booksharing.apisystem.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super("Could not find an account associated with the email: " + email);
    }
}
