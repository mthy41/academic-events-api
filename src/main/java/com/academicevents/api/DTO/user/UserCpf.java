package com.academicevents.api.DTO.user;

public class UserCpf {
    private String cpf;

    public UserCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
