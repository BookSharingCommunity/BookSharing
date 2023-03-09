package com.booksharing.apisystem.advice;

import com.booksharing.apisystem.exceptions.EmailNotFoundException;
import com.booksharing.apisystem.exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidPasswordAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalidPasswordHandler(InvalidPasswordException err) {
        return err.getMessage();
    }
}
