package com.academicevents.api.controllers.event;

import com.academicevents.api.handlers.EventHandlers;
import com.academicevents.api.models.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EventController {
    @PostMapping("/create/event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        if(EventHandlers.saveEvent(event)) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "Evento criado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("Problema no cadastro.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
