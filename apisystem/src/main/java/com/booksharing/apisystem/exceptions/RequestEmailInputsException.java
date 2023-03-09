package com.booksharing.apisystem.exceptions;

public class RequestEmailInputsException extends RuntimeException{
    public RequestEmailInputsException() {
        super("Error: The email provided in the path and the body do not match!");
    }
}
