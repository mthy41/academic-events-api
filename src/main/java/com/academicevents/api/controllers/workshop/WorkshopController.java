package com.academicevents.api.controllers.workshop;

import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopListByEventName;
import com.academicevents.api.handlers.WorkshopHandlers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkshopController {

    @PostMapping("/create/workshop")
    public ResponseEntity<?> createWorkshop(WorkshopCreateDTO workshop) {
        return WorkshopHandlers.saveWorkshop(workshop);
    }

    @PostMapping("list/workshop")
    public ResponseEntity<?> listWorkshop(WorkshopListByEventName workshopName) {
        return WorkshopHandlers.listWorkshop(workshopName);
    }
}
