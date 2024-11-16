package com.academicevents.api.DTO.event;

import java.sql.Date;

public class EventListDTO {
    private String nome;
    private String instituicap;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private String banner;
    private String miniatura;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    public EventListDTO() {}

    public EventListDTO(String nome, String instituicap, Date dataInicio, Date dataFim, String banner, String miniatura, String rua, String numero, String bairro, String cidade, String estado) {
        this.nome = nome;
        this.instituicap = instituicap;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.banner = banner;
        this.miniatura = miniatura;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public EventListDTO(String nome, String instituicap, String descricao, Date dataInicio, Date dataFim, String banner, String miniatura, String rua, String numero, String bairro, String cidade, String estado) {
        this.nome = nome;
        this.instituicap = instituicap;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.banner = banner;
        this.miniatura = miniatura;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EventListDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstituicap() {
        return instituicap;
    }

    public void setInstituicap(String instituicap) {
        this.instituicap = instituicap;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(String miniatura) {
        this.miniatura = miniatura;
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
}
