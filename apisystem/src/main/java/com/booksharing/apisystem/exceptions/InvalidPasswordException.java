package com.booksharing.apisystem.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String email) {
        super("An incorrect password was provided for the account: " + email);
    }
}
