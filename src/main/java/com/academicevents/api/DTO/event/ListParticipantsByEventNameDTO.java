package com.academicevents.api.DTO.event;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ListParticipantsByEventNameDTO {
    public String nomeEvento;

    @JsonCreator
    public ListParticipantsByEventNameDTO(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
