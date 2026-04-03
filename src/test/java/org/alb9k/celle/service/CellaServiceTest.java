package org.alb9k.celle.service;

import org.alb9k.celle.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CellaServiceTest {

    private final CellaService cellaService = new CellaService();

    @Test
    void forzaRaggioAlCentro() {
        CellaRaggio cella = new CellaRaggio("cella2", 45.4732446, 9.0961433, 10.0);
        Punto punto = new Punto(45.4732446, 9.0961433);
        Assertions.assertEquals(100.0, cella.misuraForza(punto), 0.0001);
    }

    @Test
    void forzaPotenzaAlCentro() {
        CellaPotenza cella = new CellaPotenza("cella1", 45.4616873, 9.0756298, 1.25);
        Punto punto = new Punto(45.4616873, 9.0756298);
        Assertions.assertEquals(100.0, cella.misuraForza(punto), 0.0001);
    }

    @Test
    void filtroPerForza() {
        Cella c1 = new CellaPotenza("cella1", 45.4616873, 9.0756298, 1.25);
        Cella c2 = new CellaRaggio("cella2", 45.4732446, 9.0961433, 10.0);

        Punto punto = new Punto(45.4732446, 9.0961433);
        List<Cella> risultato = cellaService.filtraPerForzaMaggioreDi(Arrays.asList(c1, c2), punto, 10.0);

        Assertions.assertFalse(risultato.isEmpty());
        Assertions.assertEquals("cella2", risultato.get(0).getNome());
    }

    @Test
    void ordinePerFrequenza() {
        Cella c1 = new CellaPotenza("cella1", 45.4616873, 9.0756298, 1.25);
        Cella c2 = new CellaRaggio("cella2", 45.4732446, 9.0961433, 10.0);

        List<EventoCella> eventi = Arrays.asList(
                new EventoCella(LocalDateTime.now(), TipoEvento.CONNECTED, c1),
                new EventoCella(LocalDateTime.now(), TipoEvento.DISCONNECTED, c1),
                new EventoCella(LocalDateTime.now(), TipoEvento.CONNECTED, c2)
        );

        List<Cella> risultato = cellaService.estraiCelleOrdinatePerFrequenza(eventi);
        Assertions.assertEquals("cella1", risultato.get(0).getNome());
        Assertions.assertEquals("cella2", risultato.get(1).getNome());
    }
}
