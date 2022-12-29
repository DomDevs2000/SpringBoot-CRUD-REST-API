package com.DomDevs.app.rest.Exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could Not Find User " + id);
    }
}
