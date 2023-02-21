package com.booksharing.apisystem.exceptions;

public class ExistingAccountException extends RuntimeException{
    public ExistingAccountException(String email) {
        super("An account already exists with this email!");
    }
}
