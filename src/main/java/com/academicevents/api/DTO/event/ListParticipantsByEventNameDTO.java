package com.academicevents.api.DTO.event;

public class ListParticipantsByEventNameDTO {
    private String nomeEvento;

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
