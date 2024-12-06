package com.academicevents.api.DTO.workshop;

import java.time.LocalDate;

public class CreateMinicursoDTO {

    private LocalDate datainicio;
    private LocalDate datafim;

    private String nomeEvento;
    private String banner;
    private String titulo;
    private String descricao;
    private boolean status;
    private int vagas;

    public CreateMinicursoDTO(LocalDate datainicio, LocalDate datafim, String nomeEvento, String banner, String titulo, String descricao, boolean status, int vagas) {
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.nomeEvento = nomeEvento;
        this.banner = banner;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.vagas = vagas;
    }

    public LocalDate getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(LocalDate datainicio) {
        this.datainicio = datainicio;
    }

    public LocalDate getDatafim() {
        return datafim;
    }

    public void setDatafim(LocalDate datafim) {
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
        return "CreateMinicursoDTO{" +
                "datainicio=" + datainicio +
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
