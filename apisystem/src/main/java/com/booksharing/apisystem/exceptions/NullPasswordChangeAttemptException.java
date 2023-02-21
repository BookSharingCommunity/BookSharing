package com.booksharing.apisystem.exceptions;

public class NullPasswordChangeAttemptException extends RuntimeException {
    public NullPasswordChangeAttemptException(){
        super("Error: Attempted to change a password without an email or password!");
    }
}
