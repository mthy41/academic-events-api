package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.DAO.WorkshopDAO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.workshop.*;
import com.academicevents.api.controllers.workshop.SubscribeWorkshopDTO;
import com.academicevents.api.customerrors.EventNotExistsError;
import com.academicevents.api.customerrors.UserAlreadySubscribedError;
import com.academicevents.api.customerrors.UserNotFoundError;
import com.academicevents.api.models.User;
import jdk.jfr.Event;
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

    public static boolean subscribeWorkshop(SubscribeWorkshopDTO workshop) {
        if(!UserDAO.searchUserByCpf(workshop.getCpf())) {
            throw new UserNotFoundError("Usuário nao encontrado");
        }

        if (!EventDAO.searchEventByName(workshop.getNomeEvento())) {
            throw new EventNotExistsError("Evento inexistente! Verifique o nome do evento e tente novamente.");
        }

        if (!WorkshopDAO.checkIfWorkshopExistsByName(workshop.getNomeWorkshop())) {
            throw new EventNotExistsError("Minicurso inexistente! Verifique o nome do minicurso e tente novamente.");
        }

        String workshopCode = WorkshopDAO.getWorkshopCodeByName(workshop.getNomeWorkshop());

        if (WorkshopDAO.checkIfUserIsAlreadySubscribed(workshop.getCpf(), workshopCode)) {
            throw new UserAlreadySubscribedError("Usuário ja inscrito no minicurso");
        }

        String eventCode = WorkshopDAO.getEventCodeByWorkshopName(workshop.getNomeWorkshop());
        if (!WorkshopDAO.subscribeWorkshop(workshop, workshopCode, eventCode)) {
            return false;
        }

        WorkshopDAO.updateVagasDecrease(workshopCode);
        WorkshopDAO.validateStatusByNumberOfSubscribers(workshopCode);

        return true;
    }

    public static boolean removeSubscription(RemoveSubscriptionDTO workshop) {
        if(!UserDAO.searchUserByCpf(workshop.getCpf())) {
            throw new UserNotFoundError("Usuário nao encontrado");
        }

        if (!EventDAO.searchEventByName(workshop.getNomeEvento())) {
            throw new EventNotExistsError("Evento inexistente! Verifique o nome do evento e tente novamente.");
        }

        if (!WorkshopDAO.checkIfWorkshopExistsByName(workshop.getNomeWorkshop())) {
            throw new EventNotExistsError("Minicurso inexistente! Verifique o nome do minicurso e tente novamente.");
        }

        String workshopCode = WorkshopDAO.getWorkshopCodeByName(workshop.getNomeWorkshop());

        if (!WorkshopDAO.checkIfUserIsAlreadySubscribed(workshop.getCpf(), workshopCode)) {
           throw new UserNotFoundError("Usuário não inscrito no minicurso");
        }

        if (!WorkshopDAO.removeSubscription(workshopCode, workshop.getCpf())) {
            return false;
        }

        WorkshopDAO.updateVagasIncrease(workshopCode);
        WorkshopDAO.validateStatusByNumberOfSubscribers(workshopCode);
        return true;
    }

    public static ArrayList<User> listSubscribedWorkshop(ListSubscriptionsDTO workshop) {
        if (!EventDAO.searchEventByName(workshop.getNomeEvento())) {
            throw new EventNotExistsError("Evento inexistente! Verifique o nome do evento e tente novamente.");
        }

        if (!WorkshopDAO.searchWorkshopByName(workshop.getNomeWorkshop())) {
            throw new EventNotExistsError("Minicurso inexistente! Verifique o nome do minicurso e tente novamente.");
        }

        String workshopCode = WorkshopDAO.getWorkshopCodeByName(workshop.getNomeWorkshop());
        String eventCode = WorkshopDAO.getEventCodeByWorkshopName(workshop.getNomeWorkshop());
        return WorkshopDAO.listSubscribedWorkshop(eventCode, workshopCode);
    }
}
