package com.academicevents.api.DTO.workshop;

import java.util.Date;

public class WorkshopInfoDTO {
    private String evento;
    private String titulo;
    private String descricao;
    private String banner;
    private Date datainicio;
    private Date datafim;
    private boolean status;
    private int vagas;

    public WorkshopInfoDTO(String titulo, String descricao, String banner, Date datainicio, Date datafim, boolean status, int vagas) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.banner = banner;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.status = status;
        this.vagas = vagas;
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

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public boolean isStatus() {
        return status;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public boolean getStatus() {
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
