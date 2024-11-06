package com.academicevents.api.customerrors;

public class SubscribeGeneralErrors extends RuntimeException {
    public SubscribeGeneralErrors(String message) {
        super(message);
    }
}
