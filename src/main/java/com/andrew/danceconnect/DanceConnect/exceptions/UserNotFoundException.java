package com.andrew.danceconnect.DanceConnect.exceptions;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}