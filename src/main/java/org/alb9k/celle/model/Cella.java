package org.alb9k.celle.model;

public abstract class Cella {

    private String nome;
    private double latitudine;
    private double longitudine;

    public Cella() {
    }

    public Cella(String nome, double latitudine, double longitudine) {
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public abstract double misuraForza(Punto punto);

    public abstract TipoParametroCella getTipoParametro();

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
}
