package com.academicevents.api.DTO.event;

public class DeleteEventDTO {
    public String nome;

    public DeleteEventDTO() { }
    public DeleteEventDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
