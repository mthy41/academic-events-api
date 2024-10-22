package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.DTO.event.Event;
import com.academicevents.api.DTO.event.SearchEvent;
import com.academicevents.api.customerrors.EventNotExistsError;
import com.academicevents.api.models.PresenceList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EventHandlers {
    public static ResponseEntity<?> saveEvent(Event event) {
        if (EventDAO.searchEventByName(event.getNome())) {
            return new ResponseEntity<>("Erro ao criar o evento. Evento já existente.", HttpStatus.BAD_REQUEST);
        }
        if (EventDAO.saveEvent(event)) {
            String presenceListCode = UUID.randomUUID().toString();
            String eventCode = EventDAO.searchCodeByName(event.getNome());
            PresenceList presenceList = new PresenceList(presenceListCode, eventCode);
            return new ResponseEntity<>("Evento criado com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Erro ao criar o evento.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static boolean deleteEvent(String nome) {
        if(EventDAO.searchEventByName(nome)) {
            return EventDAO.deleteEvent(nome);
        } else {
            throw new EventNotExistsError("Evento inexistente.");
        }
    }

    public static Event getEventbyName(SearchEvent event) {
        if(EventDAO.searchEventByName(event.getNome())) {
            return EventDAO.getEventByName(event.getNome());
        } else {
            throw new EventNotExistsError("Evento inexistente.");
        }
    }

    public static ResponseEntity<?> listEvents() {
        try {
            Map<String, ArrayList<Event>> responseOK = new HashMap<>();
            responseOK.put("events", EventDAO.listEvents());
            return new ResponseEntity<>(responseOK, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> responseError = new HashMap<>();
            responseError.put("error", e.getMessage());
            return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
