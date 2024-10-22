package com.academicevents.api.handlers;

import com.academicevents.api.customerrors.EventAlreadyExistsError;
import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.customerrors.UserNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsError(UserAlreadyExistsError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);  // Use CONFLICT (409) para um erro de duplicidade
    }

    public ResponseEntity<Map<String, String>> userNotFoundError(UserNotFoundError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, String>> handleException(EventAlreadyExistsError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
