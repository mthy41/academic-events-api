package com.academicevents.api.customerrors;

public class WrongCredentialsError extends RuntimeException{
    public WrongCredentialsError(String message) {
        super(message);
    }
}
