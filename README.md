# Progetto Celle - org.alby9000.celle

## Avvio
```bash
mvn clean install
mvn spring-boot:run
```

Applicazione disponibile su `http://localhost:8080`.

## Package
`org.alby9000.celle`

## API REST

### 1. Calcolo forza di una cella in un punto
`POST /api/celle/forza?latitudine=45.47&longitudine=9.09`

Body esempio cella a potenza:
```json
{
  "nome": "cella1",
  "latitudine": 45.4616873,
  "longitudine": 9.0756298,
  "tipoParametro": "POTENZA",
  "potenza": 1.25
}
```

### 2. Filtro celle per soglia forza
`POST /api/celle/filtra`

```json
{
  "celle": [
    {
      "nome": "cella1",
      "latitudine": 45.4616873,
      "longitudine": 9.0756298,
      "tipoParametro": "POTENZA",
      "potenza": 1.25
    },
    {
      "nome": "cella2",
      "latitudine": 45.4732446,
      "longitudine": 9.0961433,
      "tipoParametro": "RAGGIO",
      "raggioKm": 10.0
    }
  ],
  "punto": {
    "latitudine": 45.47,
    "longitudine": 9.09
  },
  "soglia": 10.0
}
```

### 3. Celle ordinate per frequenza eventi
`POST /api/celle/eventi/frequenza`

```json
[
  {
    "timestamp": "2025-03-30T10:15:30",
    "tipoEvento": "CONNECTED",
    "cella": {
      "nome": "cella1",
      "latitudine": 45.4616873,
      "longitudine": 9.0756298,
      "tipoParametro": "POTENZA",
      "potenza": 1.25
    }
  },
  {
    "timestamp": "2025-03-30T10:16:30",
    "tipoEvento": "DISCONNECTED",
    "cella": {
      "nome": "cella1",
      "latitudine": 45.4616873,
      "longitudine": 9.0756298,
      "tipoParametro": "POTENZA",
      "potenza": 1.25
    }
  },
  {
    "timestamp": "2025-03-30T10:17:30",
    "tipoEvento": "CONNECTED",
    "cella": {
      "nome": "cella2",
      "latitudine": 45.4732446,
      "longitudine": 9.0961433,
      "tipoParametro": "RAGGIO",
      "raggioKm": 10.0
    }
  }
]

```

## 4. Note implementative
- Formula cella a raggio: forza lineare da 100% al centro a 0% al bordo del raggio.
- Formula cella a potenza: `forza = 100 / (potenza ^ distanzaKm)`.
- Vincolo consigliato: `potenza > 1`.


## Test CellaRaggio

| ID | Nome Test | Descrizione | Input | Output Atteso |
|----|-----------|-------------|------|----------------|
| CR1 | Forza al centro | Verifica forza nel centro | Punto = centro cella | Forza = 100 |
| CR2 | Fuori dal raggio | Verifica fuori area | Distanza > raggio | Forza = 0 |
| CR3 | Metà distanza | Decrescita lineare | Distanza ≈ raggio/2 | Forza ≈ 50 |
| CR4 | Raggio non valido | Validazione input | raggio ≤ 0 | Eccezione |

## Test CellaPotenza

| ID | Nome Test | Descrizione | Input | Output Atteso |
|----|-----------|-------------|------|----------------|
| CP1 | Forza al centro | Verifica forza centro | Distanza = 0 | Forza = 100 |
| CP2 | Distanza crescente | Forza decrescente | Due distanze diverse | ForzaVicino > ForzaLontano |
| CP3 | Potenza non valida | Validazione input | potenza ≤ 1 | Eccezione |

## Test Filtro Celle

| ID | Nome Test | Descrizione | Input | Output Atteso |
|----|-----------|-------------|------|----------------|
| FC1 | Celle sopra soglia | Filtro corretto | Lista + soglia | Solo celle valide |
| FC2 | Ordinamento filtro | Ordine corretto | Più celle | Ordine decrescente |
| FC3 | Nessun risultato | Lista vuota | Soglia alta | Lista vuota |

## Test Frequenza Eventi

| ID | Nome Test | Descrizione | Input | Output Atteso |
|----|-----------|-------------|------|----------------|
| FE1 | Frequenza base | Conteggio eventi | cella1 x2, cella2 x1 | cella1 prima |
| FE2 | Frequenza multipla | Conteggio completo | cella3 x3, cella1 x2, cella2 x1 | cella3, cella1, cella2 |
| FE3 | Frequenze uguali | Parità frequenze | cella1 x1, cella2 x1 | Ordine non garantito |

## Test CONNECTED / DISCONNECTED

| ID | Nome Test | Descrizione | Input | Output Atteso |
|----|-----------|-------------|------|----------------|
| CD1 | CONNECTED singolo | Evento CONNECTED | cella1 CONNECTED | cella1 presente |
| CD2 | DISCONNECTED singolo | Evento DISCONNECTED | cella1 DISCONNECTED | cella1 presente |
| CD3 | Misto eventi | Conteggio misto | CONNECTED + DISCONNECTED | Conteggio corretto |

## Test Casi Limite

| ID | Nome Test | Descrizione | Input | Output Atteso |
|----|-----------|-------------|------|----------------|
| CL1 | Lista vuota | Gestione lista vuota | [] | [] |
| CL2 | Singola cella | Caso minimo | 1 evento | 1 cella |
| CL3 | Test GOLD | Scenario completo | cella3 x5, cella1 x4, cella2 x2, cella4 x1 | cella3, cella1, cella2, cella4 |

```