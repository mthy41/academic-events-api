package com.academicevents.api.customerrors;

public class UserAlreadySubscribedError extends RuntimeException{
    public UserAlreadySubscribedError(String message) {
        super(message);
    }
}
