package org.alb9k.celle.service;

import org.alb9k.celle.dto.CellaRequest;
import org.alb9k.celle.dto.CellaResponse;
import org.alb9k.celle.dto.EventoCellaRequest;
import org.alb9k.celle.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CellaMapperService {

    public Cella toModel(CellaRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Cella non valorizzata");
        }

        String tipo = request.getTipoParametro();
        if (tipo == null) {
            throw new IllegalArgumentException("tipoParametro obbligatorio");
        }

        if ("RAGGIO".equalsIgnoreCase(tipo)) {
            if (request.getRaggioKm() == null) {
                throw new IllegalArgumentException("raggioKm obbligatorio per una cella di tipo RAGGIO");
            }
            return new CellaRaggio(
                    request.getNome(),
                    request.getLatitudine(),
                    request.getLongitudine(),
                    request.getRaggioKm()
            );
        }

        if ("POTENZA".equalsIgnoreCase(tipo)) {
            if (request.getPotenza() == null) {
                throw new IllegalArgumentException("potenza obbligatoria per una cella di tipo POTENZA");
            }
            return new CellaPotenza(
                    request.getNome(),
                    request.getLatitudine(),
                    request.getLongitudine(),
                    request.getPotenza()
            );
        }

        throw new IllegalArgumentException("tipoParametro non valido: usare RAGGIO oppure POTENZA");
    }

    public EventoCella toModel(EventoCellaRequest request) {
        return new EventoCella(
                LocalDateTime.parse(request.getTimestamp()),
                TipoEvento.valueOf(request.getTipoEvento().toUpperCase()),
                toModel(request.getCella())
        );
    }

    public CellaResponse toResponse(Cella cella, Punto punto) {
        CellaResponse response = new CellaResponse();
        response.setNome(cella.getNome());
        response.setLatitudine(cella.getLatitudine());
        response.setLongitudine(cella.getLongitudine());
        response.setTipoParametro(cella.getTipoParametro().name());

        if (cella instanceof CellaRaggio) {
            response.setRaggioKm(((CellaRaggio) cella).getRaggioKm());
        }
        if (cella instanceof CellaPotenza) {
            response.setPotenza(((CellaPotenza) cella).getPotenza());
        }
        if (punto != null) {
            response.setForza(cella.misuraForza(punto));
        }
        return response;
    }
}
