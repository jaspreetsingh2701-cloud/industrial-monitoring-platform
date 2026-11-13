# System Architecture — Industrial Monitoring Platform

## 1. High-level architecture

```text
                       ┌─────────────────────────────┐
                       │       React Dashboard      │
                       │ React + TypeScript + Vite  │
                       └──────────────┬──────────────┘
                                      │
                       REST: devices │ WebSocket: live readings
                                      │
                ┌─────────────────────┴────────────────────┐
                │                                          │
       ┌────────▼─────────┐                       ┌────────▼──────────┐
       │  Device Service  │                       │ Telemetry Service │
       │ Java / Spring    │◄──── gRPC exercise ──►│ Java / Spring     │
       │ REST API         │                       │ REST + WebSocket  │
       └────────┬─────────┘                       └────────┬──────────┘
                │                                          │
       ┌────────▼─────────┐                       ┌────────▼──────────┐
       │ Device Database  │                       │ Telemetry Database│
       │ PostgreSQL       │                       │ PostgreSQL        │
       └──────────────────┘                       └───────────────────┘
```

This is a learning architecture, not a claim that every protocol must be used for every request. We will implement and compare the communication styles in focused steps.

## 2. Service responsibilities

### Device Service
Owns device metadata:
- Device ID, name, type and status.
- Create, read and update operations.
- Device validation and business rules.

It exposes REST endpoints for the dashboard.

### Telemetry Service
Owns sensor readings:
- Device ID, temperature, pressure and timestamp.
- Persist and query readings.
- Publish live updates to connected clients.

It exposes endpoints for telemetry operations and a WebSocket endpoint for live browser updates.

## 3. Why two microservices?

The responsibilities are different:
- Device Service manages relatively stable device metadata.
- Telemetry Service handles frequent time-series readings and live data delivery.

Separating them gives us a practical way to learn service boundaries and communication. In a real system, the decision to split services should be based on business ownership, scaling, deployment and operational needs—not simply because two classes exist.

## 4. Communication protocols

| Protocol | Typical direction | Use in this project |
|---|---|---|
| REST over HTTP | Request ↔ response | Dashboard loads devices; APIs create/query records |
| gRPC | Service ↔ service | Typed backend-to-backend communication exercise |
| WebSocket | Two-way persistent connection | Live telemetry between browser and Telemetry Service |
| SSE | Server → browser | Alternative for one-way server notifications/updates |

### REST
Use REST when a client requests a resource or performs an operation and expects a response. It is straightforward to inspect with browser tools, curl or an API client.

### gRPC
Use gRPC when backend services need an efficient, strongly typed RPC contract. Protocol Buffers define the messages and service methods. It is not the default choice for a browser-to-backend connection in this project.

### WebSocket
Use WebSocket when the browser needs a persistent connection for frequent updates or two-way interaction. The dashboard connects to Telemetry Service and receives new readings.

### SSE
Use SSE when the server only needs to push updates to the browser over an HTTP connection. It is simpler than WebSocket for one-way updates, but does not provide a two-way application channel.

## 5. Example request/data flows

### Load devices
1. Dashboard sends `GET /api/devices` to Device Service.
2. Device Service validates the request and reads its device store.
3. Device Service returns a JSON response.
4. Dashboard renders the device list.

### Receive live telemetry
1. Telemetry Service generates or accepts a sensor reading.
2. Telemetry Service persists the reading.
3. Telemetry Service publishes the reading to connected WebSocket clients.
4. Dashboard updates the chart and device summary.

### Backend service call
1. One backend service needs data or an operation from the other.
2. It calls a defined gRPC method using the `.proto` contract.
3. The receiving service handles the call and returns a typed response.

We will choose the exact gRPC method and direction during Day 3, based on the implemented use case.

## 6. Data ownership

Each service should own its data and schema. The Device Service should not directly read or write Telemetry Service tables, and vice versa.

For local development, both databases may run in one PostgreSQL server/container, but use separate databases or clearly separated schemas and credentials. Sharing one database server is not the same as sharing ownership of tables.

## 7. Initial API sketch

These are proposed routes; we will confirm names and payloads while implementing.

### Device Service
- `POST /api/devices` — register a device.
- `GET /api/devices` — list devices.
- `GET /api/devices/{id}` — get one device.
- `PATCH /api/devices/{id}/status` — update status.

### Telemetry Service
- `POST /api/telemetry/readings` — submit a reading.
- `GET /api/telemetry/devices/{deviceId}/readings` — retrieve recent readings.
- `WS /ws/telemetry` — receive live readings.

## 8. Architecture principles for this learning project

- Keep business logic in service classes, not controllers.
- Use DTOs at API boundaries; do not expose persistence entities by default.
- Validate incoming data.
- Return meaningful HTTP status codes and error responses.
- Set connection/read timeouts for inter-service calls.
- Avoid blind retries for non-idempotent operations.
- Keep each service responsible for its own data.
- Add authentication and production-grade observability as later steps.

## 9. Architecture decisions to revisit

- Whether to use REST or gRPC for each backend interaction.
- Whether telemetry is generated in the service or submitted by a simulator.
- How to handle reconnects and missed WebSocket updates.
- How to paginate telemetry history.
- How to secure service-to-service and browser connections.
