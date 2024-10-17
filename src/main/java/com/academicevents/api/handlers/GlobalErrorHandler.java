package com.academicevents.api.handlers;

import com.academicevents.api.customerrors.UserAlreadyExistsError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(UserAlreadyExistsError.class)
    public ResponseEntity<?> handleUserAlreadyExistsError(UserAlreadyExistsError e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
