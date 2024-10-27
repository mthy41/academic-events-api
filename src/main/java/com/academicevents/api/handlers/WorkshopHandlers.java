package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.DAO.WorkshopDAO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WorkshopHandlers {
    public static ResponseEntity<?> saveWorkshop(WorkshopCreateDTO workshop) {
        Map<String, String> response = new HashMap<>();

        if (WorkshopDAO.checkWorkshopExistsByName(workshop)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Minicurso já existente!");
        }

        EventDTO evento = EventDAO.getEventByName(workshop.getNomeEvento());

        if(evento == null) {
            return new ResponseEntity<>("Erro ao criar o minicurso. Evento inexistente, verifique o nome do evento.", HttpStatus.BAD_REQUEST);
        }

        workshop.setCodigoEvento(evento.getCodigo());
        workshop.setCodigo(UUID.randomUUID().toString());

        if(!WorkshopDAO.saveWorkshop(workshop)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar minicurso!");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Minicurso criado com sucesso!");
    }
}
