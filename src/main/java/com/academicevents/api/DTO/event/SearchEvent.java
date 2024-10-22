package com.academicevents.api.DTO.event;

public class SearchEvent {
    public String nome;

    public SearchEvent() { }

    public SearchEvent(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
