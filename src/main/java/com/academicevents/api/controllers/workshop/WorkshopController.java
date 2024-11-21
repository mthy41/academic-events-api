package com.academicevents.api.controllers.workshop;

import com.academicevents.api.DTO.workshop.ListSubscriptionsDTO;
import com.academicevents.api.DTO.workshop.RemoveSubscriptionDTO;
import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopListByEventName;
import com.academicevents.api.handlers.WorkshopHandlers;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WorkshopController {

    @PostMapping("/workshop/create")
    public ResponseEntity<?> createWorkshop(WorkshopCreateDTO workshop) {
        Map<String, String> response = new HashMap<>();
        if (!WorkshopHandlers.createWorkshop(workshop)) {
            response.put("error", "Erro ao criar o minicurso. Tente novamente.");
        }
        response.put("success", "Minicurso criado com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("workshop/listworkshops")
    public ResponseEntity<?> listWorkshop(WorkshopListByEventName workshopName) {
        return WorkshopHandlers.listWorkshop(workshopName);
    }

    @PostMapping("workshop/subscribe")
    public ResponseEntity<?> subscribeWorkshop(SubscribeWorkshopDTO workshop) {
        Map<String, String> response = new HashMap<>();
        if (!WorkshopHandlers.subscribeWorkshop(workshop)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao se inscrever no minicurso!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Inscrito com sucesso!");
    }

    @PostMapping("/workshop/listsubscriptions")
    public ResponseEntity<?> listSubscribedWorkshop(ListSubscriptionsDTO workshop) {
        Map<String, ArrayList<User>> response = new HashMap<>();
        response.put("subscriptions", WorkshopHandlers.listSubscribedWorkshop(workshop));
        if (response.get("subscriptions") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma inscrição encontrada!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/workshopsubscription/remove")
    public ResponseEntity<?> removeSubscription(RemoveSubscriptionDTO workshop) {
        Map<String, String> response = new HashMap<>();
        if (!WorkshopHandlers.removeSubscription(workshop)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover inscrição. Consulte os dados e tente novamente.");
        }
        response.put("success", "Inscrição removida com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
