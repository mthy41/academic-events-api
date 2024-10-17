package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.customerrors.EventAlreadyExistsError;
import com.academicevents.api.models.Event;
import org.springframework.stereotype.Service;

@Service
public class EventHandlers {
    public static boolean saveEvent(Event event) {
        if (!EventDAO.searchEventByName(event.getNome())){
           return EventDAO.saveEvent(event);
       } else {
           throw new EventAlreadyExistsError("Evento ja cadastrado.");
       }
    }


}
