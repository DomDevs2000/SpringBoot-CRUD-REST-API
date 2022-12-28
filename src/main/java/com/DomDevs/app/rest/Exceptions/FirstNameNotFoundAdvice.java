package com.DomDevs.app.rest.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FirstNameNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(FirstNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String FirstNameNotFoundHandler(FirstNameNotFoundException ex) {
        return ex.getMessage();
    }
}
