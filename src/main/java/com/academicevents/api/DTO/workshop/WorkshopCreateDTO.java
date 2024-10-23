package com.academicevents.api.DTO.workshop;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WorkshopCreateDTO {
    @JsonIgnore
    private String codigo;

    private String cpfAdmin;
    private String banner;
    private String codigoEvento;
    private String titulo;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private boolean status;
    private int vagas;

    public WorkshopCreateDTO() { }

    public WorkshopCreateDTO(String codigo, String cpfAdmin, String banner, String codigoEvento, String titulo, String descricao, String dataInicio, String dataFim, boolean status, int vagas) {
        this.codigo = codigo;
        this.cpfAdmin = cpfAdmin;
        this.banner = banner;
        this.codigoEvento = codigoEvento;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.vagas = vagas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCpfAdmin() {
        return cpfAdmin;
    }

    public void setCpfAdmin(String cpfAdmin) {
        this.cpfAdmin = cpfAdmin;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
}
