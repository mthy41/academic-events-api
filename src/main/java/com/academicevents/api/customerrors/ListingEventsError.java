package com.academicevents.api.customerrors;

public class ListingEventsError extends RuntimeException{
    public ListingEventsError(String message) {
        super(message);
    }
}
