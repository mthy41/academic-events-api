package com.academicevents.api.DTO.user;

public class DeleteUserDTO {
    public String cpf;

    public DeleteUserDTO() { }
    public DeleteUserDTO(String cpf) { this.cpf = cpf; }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
