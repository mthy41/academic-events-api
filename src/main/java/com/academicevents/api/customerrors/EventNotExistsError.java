package com.academicevents.api.customerrors;

public class EventNotExistsError extends RuntimeException{
    public EventNotExistsError(String message) {
        super(message);
    }
}
