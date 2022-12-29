package com.DomDevs.app.rest.Exceptions;


public class AgeNotFoundException extends RuntimeException {

    public AgeNotFoundException(int age) {
        super("Could Not Find Users With Age: " + age);
    }
}