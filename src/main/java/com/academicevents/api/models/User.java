package com.academicevents.api.models;

import com.academicevents.api.enums.ROLES;

public class User {
    public String nome;
    public String email;
    public String foto;
    public String telefone;
    public String password;
    public String cpf;
    public String rua;
    public String numero;
    public String bairro;
    public String cidade;
    public String estado;
    public ROLES role;

    public User(String nome, String foto, String email, String telefone, String password, String cpf, String rua, String numero, String bairro, String cidade, String estado, ROLES role) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.role = role;
        this.foto = foto;
    }

    public User(String cpfParticipante, String nome, String email, String telefone, String rua, String numero, String bairro, String cidade, String estado, String foto, String role) {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ROLES getRole() {
        return role;
    }

    public void setRole(ROLES role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", role=" + role +
                '}';
    }
}
