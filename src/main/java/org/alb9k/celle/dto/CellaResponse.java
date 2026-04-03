package org.alb9k.celle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CellaResponse {

    private String nome;

    private double latitudine;

    private double longitudine;

    private String tipoParametro;

    private Double raggioKm;

    private Double potenza;

    private Double forza;

    public CellaResponse() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
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

    public Double getForza() {
        return forza;
    }

    public void setForza(Double forza) {
        this.forza = forza;
    }
}