package com.academicevents.api.controllers.event;

import com.academicevents.api.handlers.EventHandlers;
import com.academicevents.api.models.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("get/event")
    public ResponseEntity<?> getEventbyName(@RequestBody Map<String, String> event) {
        try {
            Map<String, Event> responseOK = new HashMap<>();
            responseOK.put("event", EventHandlers.getEventbyName(event.get("nome")));
            return new ResponseEntity<>(responseOK, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> responseError = new HashMap<>();
            responseError.put("error", e.getMessage());
            return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/event")
    public ResponseEntity<?> deleteEvent(@RequestBody Map<String, String> event) {
        Map<String, String> response = new HashMap<>();
        if(EventHandlers.deleteEvent(event.get("nome"))) {
            response.put("success", "Evento deletado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Erro ao deletar o evento");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
