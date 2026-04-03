package org.alb9k.celle.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Punto geografico definito da latitudine e longitudine")
public class Punto {

    @Schema(description = "Latitudine del punto", example = "45.4700")
    private double latitudine;

    @Schema(description = "Longitudine del punto", example = "9.0900")
    private double longitudine;

    public Punto() {
    }

    public Punto(double latitudine, double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
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
}