package org.alb9k.celle.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.alb9k.celle.model.Punto;

public class FiltroCelleRequest {

    @Valid
    @NotNull
    private List<CellaRequest> celle;

    @NotNull
    private Punto punto;

    @NotNull
    private Double soglia;

    public FiltroCelleRequest() {
    }

    public List<CellaRequest> getCelle() {
        return celle;
    }

    public void setCelle(List<CellaRequest> celle) {
        this.celle = celle;
    }

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public Double getSoglia() {
        return soglia;
    }

    public void setSoglia(Double soglia) {
        this.soglia = soglia;
    }
}