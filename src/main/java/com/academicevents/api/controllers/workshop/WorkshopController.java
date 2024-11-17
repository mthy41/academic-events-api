package com.academicevents.api.controllers.workshop;

import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopListByEventName;
import com.academicevents.api.handlers.WorkshopHandlers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WorkshopController {

    @PostMapping("/create/workshop")
    public ResponseEntity<?> createWorkshop(WorkshopCreateDTO workshop) {
        return WorkshopHandlers.createWorkshop(workshop);
    }

    @PostMapping("list/workshop")
    public ResponseEntity<?> listWorkshop(WorkshopListByEventName workshopName) {
        return WorkshopHandlers.listWorkshop(workshopName);
    }

    @PostMapping("subscribe/workshop")
    public ResponseEntity<?> subscribeWorkshop(SubscribeWorkshopDTO workshop) {
        Map<String, String> response = new HashMap<>();
        if (!WorkshopHandlers.subscribeWorkshop(workshop)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao se inscrever no minicurso!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Inscrito com sucesso!");
    }
}
