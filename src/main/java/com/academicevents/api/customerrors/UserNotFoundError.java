package com.academicevents.api.customerrors;

public class UserNotFoundError extends RuntimeException{
    public UserNotFoundError(String message) {
        super(message);
    }
}
