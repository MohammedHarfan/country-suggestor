# County Suggest API

Small Spring Boot application implementing the `/suggest` endpoint per provided OpenAPI spec.

## Requirements
- Java 17+
- Maven

## Setup & run
1. Place `data.json` in `src/main/resources/data.json`. (Use the provided `data.json` from the assessment. I used the provided uploaded file: :contentReference[oaicite:3]{index=3}.)
2. Build:
   mvn clean package
3. Run:
   mvn spring-boot:run
   The app listens on port 3000.

## API
GET /suggest?q={q}
- q (required): county fragment, state fragment, or "name, state".
- Returns up to 5 county suggestion objects: `{ fips, state, name }`.

### Examples
- `GET http://localhost:3000/suggest?q=cowl`
    - returns Cowley (KS) and Cowlitz (WA) etc.
- `GET http://localhost:3000/suggest?q=cowlitz, wa`
    - returns Cowlitz in WA.
- `GET http://localhost:3000/suggest?q=wa`
    - returns counties in WA (up to 5).

Response content is JSON array of objects as specified in the `spec.yaml`. Example spec: :contentReference[oaicite:4]{index=4}

## Tests
Run:
