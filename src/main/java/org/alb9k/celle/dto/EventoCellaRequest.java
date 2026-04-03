package org.alb9k.celle.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventoCellaRequest {


    @NotBlank
    private String timestamp;

    @NotBlank
    private String tipoEvento;

    @Valid
    @NotNull
    private CellaRequest cella;

    public EventoCellaRequest() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public CellaRequest getCella() {
        return cella;
    }

    public void setCella(CellaRequest cella) {
        this.cella = cella;
    }
}