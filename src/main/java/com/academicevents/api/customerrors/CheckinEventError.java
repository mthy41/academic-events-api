package com.academicevents.api.customerrors;

public class CheckinEventError extends RuntimeException {
    public CheckinEventError(String message) {
        super(message);
    }
}
