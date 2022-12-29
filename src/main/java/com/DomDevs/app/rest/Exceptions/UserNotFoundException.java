package com.DomDevs.app.rest.Exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

    public UserNotFoundException(Long id) {
        super("Could Not Find User " + id);
    }
}
