package com.booksharing.apisystem.advice;

import com.booksharing.apisystem.exceptions.EmailNotFoundException;
import com.booksharing.apisystem.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsernameNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String usernameNotFoundHandler(UsernameNotFoundException err) {
        return err.getMessage();
    }
}
