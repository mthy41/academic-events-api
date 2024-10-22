package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.customerrors.EventNotExistsError;
import com.academicevents.api.models.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventHandlers {
    public static ResponseEntity<?> saveEvent(Event event) {
        if (EventDAO.searchEventByName(event.getNome())) {
            return new ResponseEntity<>("Erro ao criar o evento. Evento já existente.", HttpStatus.BAD_REQUEST);
        }
        if (EventDAO.saveEvent(event)) {
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

    public static Event getEventbyName(String nome) {
        if(EventDAO.searchEventByName(nome)) {
            return EventDAO.getEventByName(nome);
        } else {
            throw new EventNotExistsError("Evento inexistente.");
        }
    }

    public static ArrayList<Event> listEvents() {
        return EventDAO.listEvents();
    }
}
