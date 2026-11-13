# Five-Day Full-Stack Learning Plan

**Schedule:** 6 hours/day · 30 hours total  
**Primary objective:** Prepare for a real-time monitoring/frontend-modernisation interview while beginning a longer-term transition to full-stack engineering.

## Daily time structure

| Time | Focus |
|---|---|
| Hour 1 | Concepts and terminology |
| Hour 2 | Guided examples and architecture |
| Hours 3–5 | Hands-on implementation and exercises |
| Hour 6 | Testing, debugging, revision and interview questions |

## Day-by-day plan

### Day 1 — Java fundamentals
- Hour 1: Java platform, JDK/JRE/JVM, syntax, variables, types and control flow.
- Hour 2: Classes, objects, constructors, encapsulation, inheritance, polymorphism, abstraction and interfaces.
- Hour 3: Collections — List, Set, Map, Queue, generics and JavaScript comparisons.
- Hour 4: Lambdas, functional interfaces, Streams and Optional.
- Hour 5: Build the initial device-domain model and in-memory repository.
- Hour 6: Exercises, basic tests, debugging and interview revision.

**Deliverable:** A small Java console application that registers, retrieves and filters devices.

### Day 2 — Spring Boot and REST
- Hour 1: Spring Boot overview, project structure, annotations and dependency injection.
- Hour 2: REST principles, controllers, request/response DTOs and HTTP status codes.
- Hour 3: Services, repositories, validation and exception handling.
- Hour 4: PostgreSQL, entities, Spring Data JPA and database configuration.
- Hour 5: Implement Device Service endpoints and persistence.
- Hour 6: API testing, tests and interview revision.

**Deliverable:** Device Service running locally with REST endpoints and persistence.

### Day 3 — Microservices and service communication
- Hour 1: Monolith vs microservices, service boundaries and independent ownership.
- Hour 2: Service-to-service REST, timeouts, errors and API contracts.
- Hour 3: Protocol Buffers and gRPC fundamentals; implement a small service contract.
- Hour 4: Server-Sent Events (SSE), event streams and when to use them.
- Hour 5: Create Telemetry Service and connect the services using the selected communication path.
- Hour 6: Test flows, inspect logs and revise trade-offs.

**Deliverable:** Two independently runnable services with a demonstrated service-to-service call and a basic telemetry flow.

### Day 4 — WebSockets and frontend integration
- Hour 1: WebSocket fundamentals, lifecycle, connection handling and reconnection.
- Hour 2: Implement live telemetry delivery from backend to browser.
- Hour 3: React integration, typed payloads and state management.
- Hour 4: Build a real-time dashboard with charts and device status.
- Hour 5: Three.js / React Three Fiber fundamentals and a simple 3D device/scene view.
- Hour 6: End-to-end debugging and interview revision.

**Deliverable:** React dashboard receiving and displaying live telemetry.

### Day 5 — Integration and production foundations
- Hour 1: Authentication, authorization and backend security fundamentals.
- Hour 2: Resilience — validation, timeouts, retries, error handling and observability.
- Hour 3: Unit/integration tests with JUnit and Mockito; frontend tests with RTL.
- Hour 4: Docker fundamentals and Docker Compose for local services.
- Hour 5: Integrate, document and run the end-to-end prototype.
- Hour 6: Mock interview, architecture explanation and next-learning roadmap.

**Deliverable:** A documented, locally runnable prototype with tests and a clear architecture explanation.

## Scope guardrails

To keep the five-day plan achievable:
- Start with two services only: Device Service and Telemetry Service.
- Use generated/simulated telemetry rather than physical hardware.
- Implement one primary real-time transport first: WebSocket. Learn SSE as a comparison/secondary demonstration.
- Treat gRPC as a focused service-to-service exercise; avoid building unnecessary infrastructure.
- Keep the first 3D view simple and optional if core integration takes longer.
- Prefer a working vertical slice over many unfinished technologies.

## Definition of done

By the end of Day 5, aim to:
- Explain Java OOP, collections, Streams and Optional.
- Build and explain a Spring Boot REST API.
- Explain why the application has multiple services and how they communicate.
- Explain REST vs gRPC vs SSE vs WebSocket and choose appropriately.
- Show a React dashboard consuming backend data and live updates.
- Run the core components locally and explain how they would be containerised.
