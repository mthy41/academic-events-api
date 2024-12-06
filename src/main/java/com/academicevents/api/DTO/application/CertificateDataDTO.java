package com.academicevents.api.DTO.application;

public class CertificateDataDTO {
    private String cpf;
    private String nomeEvento;
    private String nomeMinicurso;

    public CertificateDataDTO(String cpf, String nomeEvento, String nomeMinicurso) {
        this.cpf = cpf;
        this.nomeEvento = nomeEvento;
        this.nomeMinicurso = nomeMinicurso;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getNomeMinicurso() {
        return nomeMinicurso;
    }

    public void setNomeMinicurso(String nomeMinicurso) {
        this.nomeMinicurso = nomeMinicurso;
    }
}
