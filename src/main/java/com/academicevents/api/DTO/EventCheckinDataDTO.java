package com.academicevents.api.DTO;

public class EventCheckinDataDTO {
    private String nomeEvento;
    private String cpfParticipante;

    public EventCheckinDataDTO(String nomeEvento, String cpfParticipante) {
        this.nomeEvento = nomeEvento;
        this.cpfParticipante = cpfParticipante;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getCpfParticipante() {
        return cpfParticipante;
    }

    public void setCpfParticipante(String cpfParticipante) {
        this.cpfParticipante = cpfParticipante;
    }
}
