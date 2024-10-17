package com.academicevents.api.customerrors;

public class EventAlreadyExistsError extends RuntimeException {
    public EventAlreadyExistsError(String message) {
        super(message);
    }
}
