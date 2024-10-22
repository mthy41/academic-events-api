package com.academicevents.api.controllers.event;

import com.academicevents.api.DTO.event.SearchEvent;
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
    public Event getEventbyName(@RequestBody SearchEvent event){
        return EventHandlers.getEventbyName(event);
    }

    @GetMapping("/get/listevents")
    public ResponseEntity<?> listEvents() {
        return EventHandlers.listEvents();
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
