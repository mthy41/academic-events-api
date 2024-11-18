package com.academicevents.api.DTO.workshop;

public class RemoveSubscriptionDTO {
    private String cpf;
    private String nomeWorkshop;

    public RemoveSubscriptionDTO(String cpf, String nomeWorkshop) {
        this.cpf = cpf;
        this.nomeWorkshop = nomeWorkshop;
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
}
