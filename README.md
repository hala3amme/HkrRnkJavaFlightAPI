# HkrRnkJavaFlightAPI

A simple Spring Boot 3 (Java 17) REST API that manages flights and passes the provided acceptance tests.

## Requirements
- Java 17+
- Maven 3.8+

## Build and Test
Run the full test suite:

```bash
mvn -DskipTests=false test
```

## Run the App
Start the application locally:

```bash
mvn spring-boot:run
```

The app listens on http://localhost:8080.

## API

### Create flight
- POST /flight
- Body (application/json):
```json
{
  "id": null,
  "flight": "F1",
  "origin": "KTM",
  "destination": "DHL",
  "speedSeries": [240,215,240,195,255,255,240]
}
```
- Response: 201 Created with the persisted flight (auto-generated `id`).

Example:
```bash
curl -s -X POST http://localhost:8080/flight \
  -H 'Content-Type: application/json' \
  -d '{
    "id": null,
    "flight": "F1",
    "origin": "KTM",
    "destination": "DHL",
    "speedSeries": [240,215,240,195,255,255,240]
  }'
```

### List flights
- GET /flight
- Query params (optional):
  - `origin`: exact match filter; if `None`, returns empty list
  - `orderBy`: `destination` for A→Z (ties by id), `-destination` for Z→A (ties by id); default is by `id` ascending

Examples:
```bash
# Default sort by id
curl -s http://localhost:8080/flight | jq

# Filter by origin
curl -s "http://localhost:8080/flight?origin=KTM" | jq

# Sort by destination ascending
curl -s "http://localhost:8080/flight?orderBy=destination" | jq

# Sort by destination descending
curl -s "http://localhost:8080/flight?orderBy=-destination" | jq
```

### Get flight by id
- GET /flight/{id}
- Response: 200 with body if found; 404 if not found

Example:
```bash
curl -i http://localhost:8080/flight/1
```

## Implementation Notes
- Stack: Spring Boot 3.3, Spring MVC, Spring Data JPA, H2 (in-memory) for tests
- Entity: `com.hackerrank.api.model.Flight`
- Repository: `com.hackerrank.api.repository.FlightRepository`
- Controller: `com.example.hackerrank.project1.controller.FlightController`

## License
MIT