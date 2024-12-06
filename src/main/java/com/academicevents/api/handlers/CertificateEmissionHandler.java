package com.academicevents.api.handlers;

import com.academicevents.api.DAO.EventDAO;
import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.DAO.WorkshopDAO;
import com.academicevents.api.DTO.application.CertificateDataDTO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.workshop.WorkshopInfoDTO;
import com.academicevents.api.customerrors.EventNotExistsError;
import com.academicevents.api.customerrors.UserNotFoundError;
import com.academicevents.api.models.User;
import com.academicevents.api.services.CertificateSend;
import com.academicevents.api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateEmissionHandler {

    @Autowired
    private EmailService emailService;

    public boolean certificateEmission(CertificateDataDTO data) {
        if (data == null) { return false; }

        if(!UserDAO.searchUserByCpf(data.getCpf())) {
            throw new UserNotFoundError("Usuário nao encontrado");
        }

        if (!EventDAO.checkIfEventExistsByName(data.getNomeEvento())) {
            throw new EventNotExistsError("Evento nao encontrado");
        }

        if (!WorkshopDAO.checkIfWorkshopExistsByName(data.getNomeMinicurso())) {
            throw new EventNotExistsError("Minicurso nao encontrado");
        }

        WorkshopInfoDTO workshop = WorkshopDAO.getWorkshopInfoByCode(WorkshopDAO.getWorkshopCodeByName(data.getNomeMinicurso()));
        EventDTO event = EventDAO.getEventByName(data.getNomeEvento());
        User user = UserDAO.getUserByCpf(data.getCpf());

        if (workshop == null || event == null) {
            return false;
        }
        try {
            emailService.sendEmail(user.getEmail(), "Certificado", "Olá, aqui está o seu certificado!", CertificateSend.generateCertificate(user.getNome(), workshop.getTitulo(), event.getNome()));
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
}
