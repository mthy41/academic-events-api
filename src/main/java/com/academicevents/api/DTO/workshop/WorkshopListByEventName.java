package com.academicevents.api.DTO.workshop;

public class WorkshopListByEventName {
    private String nomeEvento;

    public WorkshopListByEventName() { }

    public WorkshopListByEventName(String nomeEvento) { }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
