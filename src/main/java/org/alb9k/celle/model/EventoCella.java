package org.alb9k.celle.model;

import java.time.LocalDateTime;

public class EventoCella {

    private LocalDateTime timestamp;
    private TipoEvento tipoEvento;
    private Cella cella;

    public EventoCella() {
    }

    public EventoCella(LocalDateTime timestamp, TipoEvento tipoEvento, Cella cella) {
        this.timestamp = timestamp;
        this.tipoEvento = tipoEvento;
        this.cella = cella;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Cella getCella() {
        return cella;
    }

    public void setCella(Cella cella) {
        this.cella = cella;
    }
}
