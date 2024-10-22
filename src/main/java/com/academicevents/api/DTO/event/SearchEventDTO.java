package com.academicevents.api.DTO.event;

public class SearchEventDTO {
    public String nome;

    public SearchEventDTO() { }

    public SearchEventDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
