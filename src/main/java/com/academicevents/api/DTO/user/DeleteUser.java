package com.academicevents.api.DTO.user;

public class DeleteUser {
    public String cpf;

    public DeleteUser() { }
    public DeleteUser(String cpf) { this.cpf = cpf; }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
