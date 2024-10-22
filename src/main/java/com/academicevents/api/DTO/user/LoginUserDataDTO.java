package com.academicevents.api.DTO.user;

public class LoginUserDataDTO {

    public String cpf;
    public String password;

    public LoginUserDataDTO() { }

    public LoginUserDataDTO(String cpf, String password) {
        this.cpf = cpf;
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "cpf='" + cpf + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
