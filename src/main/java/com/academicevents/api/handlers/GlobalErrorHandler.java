package com.academicevents.api.handlers;

import com.academicevents.api.customerrors.UserAlreadyExistsError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsError(UserAlreadyExistsError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);  // Use CONFLICT (409) para um erro de duplicidade
    }
}
