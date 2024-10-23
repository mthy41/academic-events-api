package com.academicevents.api.handlers;

import com.academicevents.api.DAO.WorkshotDAO;
import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WorkshopHandlers {
    public static ResponseEntity<?> saveWorkshop(WorkshopCreateDTO workshop) {
        if (WorkshotDAO.searchWorkshopByName(workshop)) {
            System.out.println("aqui");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Workshop already exists");
        } else {
            WorkshotDAO.createWorkshop(workshop);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workshop created successfully");
        }
    }
}
