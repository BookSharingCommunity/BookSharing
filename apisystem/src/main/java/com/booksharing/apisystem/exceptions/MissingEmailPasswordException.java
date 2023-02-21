package com.booksharing.apisystem.exceptions;

public class MissingEmailPasswordException extends RuntimeException {
    public MissingEmailPasswordException() {
        super("Error: Missing email or password!");
    }
}
