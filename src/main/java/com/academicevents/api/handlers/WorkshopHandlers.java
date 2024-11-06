package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.DAO.WorkshopDAO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopInfoDTO;
import com.academicevents.api.DTO.workshop.WorkshopListByEventName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WorkshopHandlers {
    public static ResponseEntity<?> createWorkshop(WorkshopCreateDTO workshop) {
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

    public static ResponseEntity<?> listWorkshop(WorkshopListByEventName workshopInfo) {
        Map<String, ArrayList<WorkshopInfoDTO>> response = new HashMap<>();
        String eventCode = EventDAO.searchCodeByName(workshopInfo.getNomeEvento());

        if (eventCode == null) {
            return new ResponseEntity<>("Erro ao listar os minicursos. Evento inexistente, verifique o nome do evento.", HttpStatus.BAD_REQUEST);
        }

        ArrayList<WorkshopInfoDTO> workshops = WorkshopDAO.listWorkshopsByEventCode(eventCode);

        if (workshops.isEmpty()) {
            return new ResponseEntity<>("Nenhum minicurso encontrado neste evento.", HttpStatus.BAD_REQUEST);
        }

        workshops.forEach(workshop -> workshop.setEvento(workshopInfo.getNomeEvento()));
        response.put("workshops", workshops);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
