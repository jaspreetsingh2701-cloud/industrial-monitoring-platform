# Project Overview — Industrial Monitoring Platform

## 1. What are we building?

We are building a small industrial monitoring platform that represents devices in a factory and displays their live sensor readings.

Imagine a factory containing machines with temperature and pressure sensors. The backend manages the devices, receives or generates sensor readings, stores them, and streams updates to a React dashboard.

The project is deliberately chosen to combine frontend strengths with backend concepts commonly used in enterprise systems.

## 2. Main user experience

An operator opens the dashboard and can:
- View registered industrial devices.
- See whether each device is online, offline or in an alert state.
- Inspect recent temperature and pressure readings.
- Watch readings update live without refreshing the page.
- Open a device detail view and inspect its telemetry history.
- See a simple visual representation of a machine/device.

## 3. Core components

| Component | Responsibility | Technology |
|---|---|---|
| Dashboard | Device list, charts, live status and visualisation | React, TypeScript, Vite |
| Device Service | Register, update and retrieve devices | Java, Spring Boot, REST |
| Telemetry Service | Accept, store and publish sensor readings | Java, Spring Boot |
| Database | Persist device and telemetry data | PostgreSQL |
| Service contract | Define a typed service-to-service API | Protocol Buffers / gRPC |
| Local infrastructure | Run dependencies and services locally | Docker Compose |

## 4. Functional scope

### Device management
- Create a device with an ID, name, type and status.
- Retrieve all devices or one device by ID.
- Update device status.
- Filter devices by type or status.

### Telemetry
- Represent a reading with device ID, temperature, pressure and timestamp.
- Generate sample readings for development.
- Persist readings and retrieve recent history.
- Publish new readings to connected dashboard clients.

### Dashboard
- Show device status and summary values.
- Show temperature/pressure charts.
- Receive live readings.
- Display a simple 3D scene as a learning extension.

## 5. Non-functional learning goals

- Clear separation of responsibilities.
- Typed API contracts and DTOs.
- Input validation and useful error responses.
- Basic automated tests.
- Understandable logs and failure handling.
- Local reproducibility and containerisation.

## 6. What is intentionally out of scope initially?

- Real factory hardware and IoT gateway integration.
- Kubernetes and cloud deployment.
- Complex event brokers such as Kafka.
- Multi-tenant enterprise permissions.
- High availability, distributed tracing and production scaling.
- A sophisticated 3D digital twin.

These can be future extensions after the core application works.

## 7. Expected learning outcome

At the end, we should be able to explain the full request path:

1. A user opens the React dashboard.
2. The dashboard calls Device Service over REST to load devices.
3. Telemetry Service receives or generates readings and stores them.
4. Telemetry Service streams updates to the dashboard over WebSocket.
5. Backend services can communicate through a clearly defined service contract, including a gRPC exercise.

The aim is not just to copy code; it is to understand why each component exists and how to debug it.
