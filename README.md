# Cell Project — org.alby9000.celle

## Getting Started

```bash
mvn clean install
mvn spring-boot:run
```

Application available at: `http://localhost:8080`.

OpenAPI documentation (Swagger UI): `http://localhost:8080/swagger-ui/index.html`.

## Package

`org.alby9000.celle`

## REST API

### 1. Calculate Cell Signal Strength at a Given Location

`POST /api/celle/forza?latitudine=45.47&longitudine=9.09`

Example request body for a power-based cell:

```json
{
  "nome": "cella1",
  "latitudine": 45.4616873,
  "longitudine": 9.0756298,
  "tipoParametro": "POTENZA",
  "potenza": 1.25
}
```

### 2. Filter Cells by Signal Strength Threshold

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

### 3. Sort Cells by Event Frequency

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

## 4. Implementation Notes

- **Radius-based cell formula:** Signal strength decreases linearly from 100% at the center to 0% at the boundary of the coverage radius.
- **Power-based cell formula:** `strength = 100 / (power ^ distanceKm)`.
- **Recommended constraint:** `potenza > 1`.

## Radius-Based Cell Tests (CellaRaggio)

| ID | Test Name | Description | Input | Expected Output |
|---|---|---|---|---|
| CR1 | Strength at Center | Verify signal strength at the cell center | Point = cell center | Strength = 100 |
| CR2 | Outside Coverage Radius | Verify behavior outside the coverage area | Distance > radius | Strength = 0 |
| CR3 | Half Radius Distance | Verify linear signal strength decay | Distance ≈ radius/2 | Strength ≈ 50 |
| CR4 | Invalid Radius | Validate radius input | Radius ≤ 0 | Exception |

## Power-Based Cell Tests (CellaPotenza)

| ID | Test Name | Description | Input | Expected Output |
|---|---|---|---|---|
| CP1 | Strength at Center | Verify signal strength at the center | Distance = 0 | Strength = 100 |
| CP2 | Increasing Distance | Verify decreasing signal strength | Two different distances | NearStrength > FarStrength |
| CP3 | Invalid Power | Validate power parameter | Power ≤ 1 | Exception |

## Cell Filtering Tests

| ID | Test Name | Description | Input | Expected Output |
|---|---|---|---|---|
| FC1 | Cells Above Threshold | Verify correct filtering | Cell list + threshold | Only qualifying cells |
| FC2 | Filter Sorting | Verify correct ordering | Multiple cells | Descending order |
| FC3 | No Matching Results | Verify empty result handling | High threshold | Empty list |

## Event Frequency Tests

| ID | Test Name | Description | Input | Expected Output |
|---|---|---|---|---|
| FE1 | Basic Frequency | Count events per cell | cella1 ×2, cella2 ×1 | cella1 first |
| FE2 | Multiple Frequencies | Verify complete frequency counting | cella3 ×3, cella1 ×2, cella2 ×1 | cella3, cella1, cella2 |
| FE3 | Equal Frequencies | Verify behavior when frequencies are equal | cella1 ×1, cella2 ×1 | Order not guaranteed |

## CONNECTED / DISCONNECTED Event Tests

| ID | Test Name | Description | Input | Expected Output |
|---|---|---|---|---|
| CD1 | Single CONNECTED Event | Process a CONNECTED event | cella1 CONNECTED | cella1 included |
| CD2 | Single DISCONNECTED Event | Process a DISCONNECTED event | cella1 DISCONNECTED | cella1 included |
| CD3 | Mixed Events | Count different event types | CONNECTED + DISCONNECTED | Correct event count |

## Edge Case Tests

| ID | Test Name | Description | Input | Expected Output |
|---|---|---|---|---|
| CL1 | Empty List | Handle an empty event list | `[]` | `[]` |
| CL2 | Single Cell | Verify minimum input scenario | 1 event | 1 cell |
| CL3 | GOLD Test | Verify a complete test scenario | cella3 ×5, cella1 ×4, cella2 ×2, cella4 ×1 | cella3, cella1, cella2, cella4 |