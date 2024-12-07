package com.academicevents.api.controllers.event;

import com.academicevents.api.DTO.SubscribeEventDTO;
import com.academicevents.api.DTO.event.*;
import com.academicevents.api.DTO.user.UserCpf;
import com.academicevents.api.customerrors.CheckinEventError;
import com.academicevents.api.handlers.EventHandlers;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EventController {
    @PostMapping("/event/create")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO event) {
        System.out.println(event);
        return EventHandlers.createEvent(event);
    }

    @PostMapping("event/getevent")
    public EventDTO getEventbyName(@RequestBody SearchEventDTO event){
        return EventHandlers.getEventbyName(event);
    }

    @GetMapping("/event/listevents")
    public ResponseEntity<?> listEvents() {
        return EventHandlers.listEvents();
    }

    @PostMapping("event/subscribe")
    public ResponseEntity<?> subscribeEvent(@RequestBody SubscribeEventDTO eventCheckinData) {
        Map<String, String> response = new HashMap<>();
        if (!EventHandlers.subscribeEvent(eventCheckinData)) {
            throw new CheckinEventError("Erro ao realizar checkin. Consulte os dados e tente novamente.");
        }
        response.put("success", "Inscrição realizada com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/event/checkin")
    public ResponseEntity<?> checkinEvent(@RequestBody SubscribeEventDTO eventCheckinData) {
        Map<String, String> response = new HashMap<>();
        if (!EventHandlers.checkinEvent(eventCheckinData)) {
            throw new CheckinEventError("Erro ao realizar checkin. Consulte os dados e tente novamente.");
        }
        response.put("success", "Inscrição realizada com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/event/removesubscription")
    public ResponseEntity<?> removeSubscription(@RequestBody SubscribeEventDTO eventCheckinData) {
        Map<String, String> response = new HashMap<>();
        if (!EventHandlers.removeSubscription(eventCheckinData)) {
            throw new CheckinEventError("Erro ao remover inscrição. Consulte os dados e tente novamente.");
        }
        response.put("success", "Inscrição removida com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/event/listsubscribed")
    public ResponseEntity<?> listSubscribedEvents(@RequestBody ListParticipantsByEventNameDTO event) {
        System.out.println("aquiiii");
        System.out.println(event.getNomeEvento());
        HashMap<String, ArrayList<User>> response = new HashMap<>();
        ArrayList<User> subscribedParticipantsEvent = EventHandlers.listSubscribedParticipansEvent(event.getNomeEvento());

        response.put("participantes", subscribedParticipantsEvent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/event")
    public ResponseEntity<?> deleteEvent(@RequestBody DeleteEventDTO event) {
        return EventHandlers.deleteEvent(event);
    }
}
