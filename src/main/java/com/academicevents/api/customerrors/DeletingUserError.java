package com.academicevents.api.customerrors;

public class DeletingUserError extends RuntimeException{
    public DeletingUserError(String message) {
        super(message);
    }
}
