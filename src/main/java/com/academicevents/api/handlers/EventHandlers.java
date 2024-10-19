package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.customerrors.EventAlreadyExistsError;
import com.academicevents.api.customerrors.EventNotExistsError;
import com.academicevents.api.models.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventHandlers {
    public static boolean saveEvent(Event event) {
        if (!EventDAO.searchEventByName(event.getNome())){
           return EventDAO.saveEvent(event);
       } else {
           throw new EventAlreadyExistsError("Evento ja cadastrado.");
       }
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
