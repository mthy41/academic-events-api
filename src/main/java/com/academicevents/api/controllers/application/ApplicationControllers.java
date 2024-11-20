package com.academicevents.api.controllers.application;

import com.academicevents.api.DTO.application.CertificateDataDTO;
import com.academicevents.api.DTO.user.UserCpf;
import com.academicevents.api.handlers.CertificateEmissionHandler;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationControllers {

    @Autowired
    private CertificateEmissionHandler certificateEmissionHandler;

    @PostMapping("/certificate/emission")
    public ResponseEntity<?> certificateEmission(CertificateDataDTO data) {
        Map<String, String> response = new HashMap<>();
        if (!certificateEmissionHandler.certificateEmission(data)) {
            response.put("error", "Erro ao emitir certificado. Tente novamente.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("success", "Certificado emitido com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
