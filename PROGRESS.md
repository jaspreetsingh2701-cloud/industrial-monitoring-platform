# Learning and Project Progress

Update this file as we complete each step. Do not mark an item complete until it has been run and verified.

## Overall progress

- [x] Setup verified — JDK 21, VS Code and Maven working
- [x] Day 1 complete
- [ ] Day 2 complete (in progress — approximately 65%)
- [ ] Day 3 complete
- [ ] Day 4 complete
- [ ] Day 5 complete
- [ ] End-to-end prototype verified

**Current status:** Day 1 is complete. Day 2 has progressed from Spring fundamentals to a running Spring Boot Device Service with CRUD REST endpoints, request validation, centralized exception handling and automated service/controller tests. The latest verified test run passed all 10 tests (6 service + 4 controller). The repository is still in-memory; configuration/logging and PostgreSQL/JPA persistence remain.

## Daily tracker

| Day | Planned focus | Status | Notes / blockers |
|---|---|---|---|
| Setup | JDK 21, VS Code, Maven, Git | Complete | Temurin JDK 21.0.13 and Maven 3.6.3 verified on Java 21. |
| Day 1 | Java fundamentals and device console app | Complete | Device and SensorReading implemented; Java 21 compilation succeeds; Spotless apply/check succeed; 8 JUnit tests pass (0 failures/errors). |
| Day 2 | Spring Boot REST and persistence | In progress — ~65% | Spring DI/Beans/startup covered; Device Service starts on port 8080 with embedded Tomcat 11; GET/POST/PUT/DELETE endpoints implemented; DTO validation and global exception handling verified; 6 Mockito service tests + 4 MockMvc controller tests pass. Next: configuration/profiles/logging, then PostgreSQL/JPA persistence and final review. |
| Day 3 | Microservices, gRPC and SSE | Not started | Separate services and service-owned data. |
| Day 4 | WebSocket and React dashboard | Not started | Real-time telemetry integration. |
| Day 5 | Security, tests, Docker and integration | Not started | End-to-end prototype verification. |

## Day 2 verified implementation

### Application and API

- Spring Boot 4.1.2 Device Service starts successfully with embedded Tomcat 11 on port 8080.
- Implemented controller, service, repository, model and request DTO layers.
- Implemented:
  - `GET /devices`
  - `GET /devices/{id}`
  - `POST /devices`
  - `PUT /devices/{id}`
  - `DELETE /devices/{id}`
- POST returns 201 Created and a Location header.
- Duplicate device IDs return 409 Conflict.
- Invalid request data returns 400 Bad Request.
- Missing device lookups/updates/deletes return 404 Not Found.
- The repository is currently in-memory; data does not survive an application restart.

### Automated tests

Latest verified command:

```powershell
# Run a clean test cycle for the Device Service.
.\scripts\device.ps1 test
```

Latest reported result:

| Test class | Tests | Failures | Errors | Skipped |
|---|---:|---:|---:|---:|
| DeviceServiceTest | 6 | 0 | 0 | 0 |
| DeviceControllerTest | 4 | 0 | 0 | 0 |
| **Total** | **10** | **0** | **0** | **0** |

- Service tests use JUnit 5 and Mockito.
- Controller tests use Spring MVC test support, MockMvc, `@WebMvcTest` and `@MockitoBean`.
- The controller slice test exercises MVC routing/validation without starting a real embedded HTTP server.
- Mockito emitted a dynamic-agent/self-attachment warning during tests; it was non-fatal and the build succeeded. Review as a later build-quality task rather than treating it as a test failure.

### Day 2 next steps

1. Application configuration: properties vs YAML, server port and configuration precedence.
2. Profiles: development vs production configuration.
3. Environment variables and externalised configuration; do not commit secrets.
4. Logging with SLF4J/Logback; replace ad-hoc console output with structured application logs.
5. PostgreSQL, JPA/Hibernate entities, repositories and transaction basics.
6. Replace the in-memory repository with database persistence and rerun the test suite.
7. Final debugging, production-practice and interview review.

## Decisions and notes

| Date | Decision / learning | Reason |
|---|---|---|
| 2026-11-14 | Keep the original five-day sprint as the immediate priority | Complete the practical project before moving to the extended backend curriculum. |
| 2026-11-14 | Use StringBuilder for independent per-thread string construction; StringBuffer only when a mutable buffer is shared across threads and synchronized methods are appropriate | Thread count alone does not require StringBuffer; shared mutable state is the key distinction. |
| 2026-11-15 | Keep service tests and controller slice tests separate | Service tests isolate business logic with mocked repositories; MVC slice tests focus on HTTP mapping, request validation and controller responses. |
| 2026-11-15 | Keep Device Service persistence in its own repository boundary | The current in-memory repository is a learning implementation; PostgreSQL/JPA will replace it without moving persistence logic into the controller. |

## Blocker log

| Date | Blocker | Resolution |
|---|---|---|
| | | |

## Daily review template

Copy this section at the end of each day:

### Day N review
- Completed:
- Not completed:
- Commands/run results:
- Concepts I can explain:
- Concepts to revise:
- Blockers:
- Next session:
