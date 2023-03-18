package com.booksharing.apisystem.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("Could not find an account associated with the username: " + username);
    }
}
