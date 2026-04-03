package org.alb9k.celle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.alb9k.celle.dto.CellaRequest;
import org.alb9k.celle.dto.CellaResponse;
import org.alb9k.celle.dto.EventoCellaRequest;
import org.alb9k.celle.dto.FiltroCelleRequest;
import org.alb9k.celle.model.Cella;
import org.alb9k.celle.model.EventoCella;
import org.alb9k.celle.model.Punto;
import org.alb9k.celle.service.CellaMapperService;
import org.alb9k.celle.service.CellaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/celle")
@Tag(name = "Celle API", description = "API per il calcolo della forza delle celle e la gestione degli eventi")
public class CellaController {

    private final CellaService cellaService;
    private final CellaMapperService mapperService;

    public CellaController(CellaService cellaService, CellaMapperService mapperService) {
        this.cellaService = cellaService;
        this.mapperService = mapperService;
    }

    @Operation(summary = "Calcola la forza di una cella")
    @PostMapping("/forza")
    public ResponseEntity<CellaResponse> misuraForza(
            @Valid @RequestBody CellaRequest cellaRequest,
            @RequestParam double latitudine,
            @RequestParam double longitudine
    ) {
        Cella cella = mapperService.toModel(cellaRequest);
        Punto punto = new Punto(latitudine, longitudine);
        return ResponseEntity.ok(mapperService.toResponse(cella, punto));
    }

    @Operation(summary = "Filtra celle per forza")
    @PostMapping("/filtra")
    public ResponseEntity<List<CellaResponse>> filtraPerForza(@Valid @RequestBody FiltroCelleRequest request) {
        List<Cella> celle = request.getCelle()
                .stream()
                .map(mapperService::toModel)
                .collect(Collectors.toList());

        List<CellaResponse> response = cellaService
                .filtraPerForzaMaggioreDi(celle, request.getPunto(), request.getSoglia())
                .stream()
                .map(cella -> mapperService.toResponse(cella, request.getPunto()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Ordina celle per frequenza eventi")
    @PostMapping("/eventi/frequenza")
    public ResponseEntity<List<CellaResponse>> ordinaPerFrequenza(@Valid @RequestBody List<EventoCellaRequest> request) {
        List<EventoCella> eventi = request.stream()
                .map(mapperService::toModel)
                .collect(Collectors.toList());

        List<CellaResponse> response = cellaService
                .estraiCelleOrdinatePerFrequenza(eventi)
                .stream()
                .map(cella -> mapperService.toResponse(cella, null))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}