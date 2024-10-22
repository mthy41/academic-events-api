package com.academicevents.api.controllers.event;

import com.academicevents.api.handlers.EventHandlers;
import com.academicevents.api.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EventController {
    @PostMapping("/create/event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        return EventHandlers.saveEvent(event);
    }

    @PostMapping("get/event")
    public ResponseEntity<?> getEventbyName(@RequestBody
                                                @Schema(description = "Nome do evento", example = "{\"nome\": \"Nome do evento\"}")
                                                Map<String, String> event) {
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

    @GetMapping("/get/listevents")
    public ResponseEntity<?> listEvents() {
        try {
            Map<String, ArrayList<Event>> responseOK = new HashMap<>();
            responseOK.put("events", EventHandlers.listEvents());
            return new ResponseEntity<>(responseOK, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> responseError = new HashMap<>();
            responseError.put("error", e.getMessage());
            return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/event")
    public ResponseEntity<?> deleteEvent(@RequestBody
                                             @Schema(description = "Nome do evento", example = "{\"nome\": \"Nome do evento\"}")
                                             Map<String, String> event) {
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
