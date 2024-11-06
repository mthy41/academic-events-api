package com.academicevents.api.handlers;

import com.academicevents.api.customerrors.*;
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

    @ExceptionHandler()
    public ResponseEntity<Map<String, String>> userNotFoundError(UserNotFoundError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<Map<String, String>> invalidInputDataError(InvalidInputDataError a) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Entrada de dados inválida ou nula");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<Map<String, String>> handleException(EventAlreadyExistsError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongCredentialsError.class)
    public ResponseEntity<Map<String, String>> handleException(WrongCredentialsError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EventNotExistsError.class)
    public ResponseEntity<Map<String, String>> handleException(EventNotExistsError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadySubscribedError.class)
    public ResponseEntity<Map<String, String>> handleException(UserAlreadySubscribedError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SubscribeGeneralErrors.class)
    public ResponseEntity<Map<String, String>> handleException(SubscribeGeneralErrors e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
