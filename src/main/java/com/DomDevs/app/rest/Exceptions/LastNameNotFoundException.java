package com.DomDevs.app.rest.Exceptions;

public class LastNameNotFoundException extends RuntimeException {

    public LastNameNotFoundException(String lastName) {
        super("Could Not Find User with last name: " + lastName);
    }
}
