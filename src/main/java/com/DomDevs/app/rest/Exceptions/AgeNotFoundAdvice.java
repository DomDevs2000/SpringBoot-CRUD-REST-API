package com.DomDevs.app.rest.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AgeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AgeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String AgeNotFoundHandler(AgeNotFoundException ex) {
        return ex.getMessage();
    }
}