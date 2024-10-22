package com.academicevents.api.controllers.event;

import com.academicevents.api.DTO.event.DeleteEventDTO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.event.SearchEventDTO;
import com.academicevents.api.handlers.EventHandlers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
    @PostMapping("/create/event")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO event) {
        return EventHandlers.saveEvent(event);
    }

    @PostMapping("get/event")
    public EventDTO getEventbyName(@RequestBody SearchEventDTO event){
        return EventHandlers.getEventbyName(event);
    }

    @GetMapping("/get/listevents")
    public ResponseEntity<?> listEvents() {
        return EventHandlers.listEvents();
    }

    @DeleteMapping("/delete/event")
    public ResponseEntity<?> deleteEvent(@RequestBody DeleteEventDTO event) {
        return EventHandlers.deleteEvent(event);
    }
}
