package org.alb9k.celle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CellaRequest {


    @NotBlank
    private String nome;


    @NotNull
    private Double latitudine;


    @NotNull
    private Double longitudine;


    @NotBlank
    private String tipoParametro;


    private Double raggioKm;


    private Double potenza;

    public CellaRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }

    public String getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public Double getRaggioKm() {
        return raggioKm;
    }

    public void setRaggioKm(Double raggioKm) {
        this.raggioKm = raggioKm;
    }

    public Double getPotenza() {
        return potenza;
    }

    public void setPotenza(Double potenza) {
        this.potenza = potenza;
    }
}