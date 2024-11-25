package com.academicevents.api.DTO.workshop;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;

public class WorkshopCreateDTO {
    @JsonIgnore
    private String codigo;
    @JsonIgnore
    private String codigoEvento;


    private Date datainicio;
    private Date datafim;

    private String nomeEvento;
    private String banner;
    private String titulo;
    private String descricao;
    private boolean status;
    private int vagas;

    public WorkshopCreateDTO() { }

    public WorkshopCreateDTO(String codigo, String codigoEvento, Date datainicio, Date datafim, String nomeEvento, String banner, String titulo, String descricao, boolean status, int vagas) {
        this.codigo = codigo;
        this.codigoEvento = codigoEvento;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.nomeEvento = nomeEvento;
        this.banner = banner;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.vagas = vagas;
    }


    public WorkshopCreateDTO(Date datainicio, Date datafim, String nomeEvento, String banner, String titulo, String descricao, boolean status, int vagas) {
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.nomeEvento = nomeEvento;
        this.banner = banner;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.vagas = vagas;
    }

    public WorkshopCreateDTO(String nomeEvento, String banner, String titulo, String descricao, boolean status, int vagas) {
        this.nomeEvento = nomeEvento;
        this.banner = banner;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.vagas = vagas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }


    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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

    @Override
    public String toString() {
        return "WorkshopCreateDTO{" +
                "codigo='" + codigo + '\'' +
                ", codigoEvento='" + codigoEvento + '\'' +
                ", datainicio=" + datainicio +
                ", datafim=" + datafim +
                ", nomeEvento='" + nomeEvento + '\'' +
                ", banner='" + banner + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", vagas=" + vagas +
                '}';
    }
}
