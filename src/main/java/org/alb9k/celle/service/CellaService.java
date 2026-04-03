package org.alb9k.celle.service;

import org.alb9k.celle.model.Cella;
import org.alb9k.celle.model.EventoCella;
import org.alb9k.celle.model.Punto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CellaService {

    public List<Cella> filtraPerForzaMaggioreDi(List<Cella> celle, Punto punto, double soglia) {
        return celle.stream()
                .filter(cella -> cella.misuraForza(punto) > soglia)
                .sorted((c1, c2) -> Double.compare(c2.misuraForza(punto), c1.misuraForza(punto)))
                .collect(Collectors.toList());
    }

    public List<Cella> estraiCelleOrdinatePerFrequenza(List<EventoCella> eventi) {
        Map<String, Integer> frequenze = new HashMap<String, Integer>();
        Map<String, Cella> cellePerNome = new HashMap<String, Cella>();

        for (EventoCella evento : eventi) {
            if (evento != null && evento.getCella() != null && evento.getCella().getNome() != null) {
                String nome = evento.getCella().getNome();
                Integer frequenzaAttuale = frequenze.get(nome);
                frequenze.put(nome, frequenzaAttuale == null ? 1 : frequenzaAttuale + 1);
                cellePerNome.put(nome, evento.getCella());
            }
        }

        return frequenze.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(entry -> cellePerNome.get(entry.getKey()))
                .collect(Collectors.toList());
    }
}
