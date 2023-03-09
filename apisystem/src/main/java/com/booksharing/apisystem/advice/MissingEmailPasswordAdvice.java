package com.booksharing.apisystem.advice;

import com.booksharing.apisystem.exceptions.MissingEmailPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MissingEmailPasswordAdvice {
    @ResponseBody
    @ExceptionHandler(MissingEmailPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String nullLoginAttemptHandler(MissingEmailPasswordException err) {
        return err.getMessage();
    }
}
