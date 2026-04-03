package org.alb9k.celle.model;

import org.alb9k.celle.service.GeoService;

public class CellaPotenza extends Cella {

    private double potenza;

    public CellaPotenza() {
    }

    public CellaPotenza(String nome, double latitudine, double longitudine, double potenza) {
        super(nome, latitudine, longitudine);
        this.potenza = potenza;
    }

    @Override
    public double misuraForza(Punto punto) {
        if (punto == null) {
            throw new IllegalArgumentException("Il punto non può essere nullo");
        }

        if (potenza <= 1.0) {
            throw new IllegalArgumentException("Il parametro potenza deve essere maggiore di 1");
        }

        double distanzaKm = GeoService.distanzaKm(
                getLatitudine(),
                getLongitudine(),
                punto.getLatitudine(),
                punto.getLongitudine()
        );

        double forza = 100.0 / Math.pow(potenza, distanzaKm);
        return Math.max(0.0, Math.min(100.0, forza));
    }

    @Override
    public TipoParametroCella getTipoParametro() {
        return TipoParametroCella.POTENZA;
    }

    public double getPotenza() {
        return potenza;
    }

    public void setPotenza(double potenza) {
        this.potenza = potenza;
    }
}