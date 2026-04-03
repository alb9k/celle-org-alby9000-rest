package org.alb9k.celle.model;

import org.alb9k.celle.service.GeoService;

public class CellaRaggio extends Cella {

    private double raggioKm;

    public CellaRaggio() {
    }

    public CellaRaggio(String nome, double latitudine, double longitudine, double raggioKm) {
        super(nome, latitudine, longitudine);
        this.raggioKm = raggioKm;
    }

    @Override
    public double misuraForza(Punto punto) {
        if (raggioKm <= 0.0) {
            throw new IllegalArgumentException("Il raggio deve essere maggiore di 0");
        }

        double distanzaKm = GeoService.distanzaKm(
                getLatitudine(),
                getLongitudine(),
                punto.getLatitudine(),
                punto.getLongitudine()
        );

        if (distanzaKm >= raggioKm) {
            return 0.0;
        }

        double forza = 100.0 * (1.0 - (distanzaKm / raggioKm));
        return Math.max(0.0, Math.min(100.0, forza));
    }

    @Override
    public TipoParametroCella getTipoParametro() {
        return TipoParametroCella.RAGGIO;
    }

    public double getRaggioKm() {
        return raggioKm;
    }

    public void setRaggioKm(double raggioKm) {
        this.raggioKm = raggioKm;
    }
}
