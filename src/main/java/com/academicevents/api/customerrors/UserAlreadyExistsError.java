package com.academicevents.api.customerrors;

public class UserAlreadyExistsError extends RuntimeException{
    public UserAlreadyExistsError(String message) {
        super(message);
    }
}
