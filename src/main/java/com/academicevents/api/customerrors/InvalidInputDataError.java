package com.academicevents.api.customerrors;

public class InvalidInputDataError extends RuntimeException {
    public InvalidInputDataError(String message) {
        super(message);
    }
}
