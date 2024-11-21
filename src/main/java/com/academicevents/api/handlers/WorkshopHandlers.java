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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WorkshopHandlers {
    public static boolean createWorkshop(WorkshopCreateDTO workshop) {
        System.out.println(workshop);
        if (WorkshopDAO.checkWorkshopExistsByName(workshop)) {
            throw new EventNotExistsError("Minicurso com esse nome já existe.");
        }

        EventDTO evento = EventDAO.getEventByName(workshop.getNomeEvento());
        System.out.println(evento);
        if(evento == null) {
            throw new EventNotExistsError("Nome do evento inexistente: " + workshop.getNomeEvento());
        }

        workshop.setCodigoEvento(evento.getCodigo());
        workshop.setCodigo(UUID.randomUUID().toString());

        if(!WorkshopDAO.saveWorkshop(workshop)) {
            return false;
        }

        return true;
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
