package com.academicevents.api.controllers.workshop;

import com.academicevents.api.DTO.workshop.RemoveSubscriptionDTO;
import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopListByEventName;
import com.academicevents.api.handlers.WorkshopHandlers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @DeleteMapping("/delete/workshopsubscription")
    public ResponseEntity<?> removeSubscription(RemoveSubscriptionDTO workshop) {
        Map<String, String> response = new HashMap<>();
        if (!WorkshopHandlers.removeSubscription(workshop)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover inscrição. Consulte os dados e tente novamente.");
        }
        response.put("success", "Inscrição removida com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
