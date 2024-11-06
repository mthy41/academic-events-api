package com.academicevents.api.customerrors;

public class PresenceListNotFoundError extends RuntimeException {
    public PresenceListNotFoundError(String message) {
        super(message);
    }
}
