package com.academicevents.api;

import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.event.SubscribeEventDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EventCotrollerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createNewEventShouldReturnSuccess() throws Exception {
        EventDTO eventDTO = new EventDTO("codigoteste", "eventospringteste", "instit.teste", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()) , "banner teste", "miniatura teste", "teste", "teste", "teste", "teste", "teste");

        mockMvc.perform(post("/create/event")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(jsonPath( "success").value("Evento criado com sucesso!"))
                .andExpect(status().isOk());
    }

    @Test
    void createEventWithNameThatAlreadyExistsShouldReturnError() throws Exception {
        EventDTO eventDTO = new EventDTO("codigoteste", "eventospringteste", "instit.teste", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()) , "banner teste", "miniatura teste", "teste", "teste", "teste", "teste", "teste");

        mockMvc.perform(post("/create/event")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(jsonPath("error").value("Erro ao criar o evento. Evento já existente."))
                .andExpect(status().isConflict());
    }

    @Test
    void subscribeEventWithCorrectDataShouldReturnSuccess() throws Exception {
        SubscribeEventDTO subscribeEventDTO = new SubscribeEventDTO("Laires Pereira", "lairespereira@hotmail.com", "43394170841", "eventospringteste");

        mockMvc.perform(post("/subscribe/event")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(subscribeEventDTO)))
                .andExpect(jsonPath("success").value("Inscrição realizada com sucesso!"))
                .andExpect(status().isOk());
    }


}
