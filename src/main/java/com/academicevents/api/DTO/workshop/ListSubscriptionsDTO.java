package com.academicevents.api.DTO.workshop;

public class ListSubscriptionsDTO {
    private String nomeEvento;
    private String nomeWorkshop;

    public ListSubscriptionsDTO(String nomeEvento, String nomeWorkshop) {
        this.nomeEvento = nomeEvento;
        this.nomeWorkshop = nomeWorkshop;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getNomeWorkshop() {
        return nomeWorkshop;
    }

    public void setNomeWorkshop(String nomeWorkshop) {
        this.nomeWorkshop = nomeWorkshop;
    }
}
