package com.DomDevs.app.rest.Exceptions;

public class FirstNameNotFoundException extends RuntimeException {

    public FirstNameNotFoundException(String firstName) {
        super("Could Not Find User with first name: " + firstName);
    }
}
