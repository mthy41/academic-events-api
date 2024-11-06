package com.academicevents.api.DTO.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;

public class EventDTO {
    @JsonIgnore
    private String codigo;

    public String nome;
    public String instituicao;
    public Date datainicio;
    public Date datafim;
    public String banner;
    public String miniatura;
    public String rua;
    public String numero;
    public String bairro;
    public String cidade;
    public String estado;

    public EventDTO() { }

    public EventDTO(String codigo, String nome, String instituicao, Date datainicio, Date datafim, String banner, String miniatura, String rua, String numero, String bairro, String cidade, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.instituicao = instituicao;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.banner = banner;
        this.miniatura = miniatura;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public EventDTO(String nome, String instituicao, Date datainicio, Date datafim, String banner, String miniatura, String rua, String numero, String bairro, String cidade, String estado) {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
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

    @Override
    public String toString() {
        return "EventDTO{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", instituicao='" + instituicao + '\'' +
                ", datainicio=" + datainicio +
                ", datafim=" + datafim +
                ", banner='" + banner + '\'' +
                ", miniatura='" + miniatura + '\'' +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
