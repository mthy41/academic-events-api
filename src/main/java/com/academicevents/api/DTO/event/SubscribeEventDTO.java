package com.academicevents.api.DTO.event;

public class SubscribeEventDTO {
    private String nomeParticipante;
    private String emailParticipante;
    private String cpfParticipante;
    private String nomeEvento;

    public SubscribeEventDTO() { }

    public SubscribeEventDTO(String nomeParticipante, String emailParticipante, String cpfParticipante, String nomeEvento) {
        this.nomeParticipante = nomeParticipante;
        this.emailParticipante = emailParticipante;
        this.cpfParticipante = cpfParticipante;
        this.nomeEvento = nomeEvento;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public void setEmailParticipante(String emailParticipante) {
        this.emailParticipante = emailParticipante;
    }

    public String getCpfParticipante() {
        return cpfParticipante;
    }

    public void setCpfParticipante(String cpfParticipante) {
        this.cpfParticipante = cpfParticipante;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
