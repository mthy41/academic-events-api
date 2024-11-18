package com.academicevents.api.DTO.workshop;

public class RemoveSubscriptionDTO {
    private String cpf;
    private String nomeWorkshop;
    private String nomeEvento;

    public RemoveSubscriptionDTO(String cpf, String nomeWorkshop, String nomeEvento) {
        this.cpf = cpf;
        this.nomeWorkshop = nomeWorkshop;
        this.nomeEvento = nomeEvento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeWorkshop() {
        return nomeWorkshop;
    }

    public void setNomeWorkshop(String nomeWorkshop) {
        this.nomeWorkshop = nomeWorkshop;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
