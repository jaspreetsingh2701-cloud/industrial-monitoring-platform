# Learning and Project Progress

Update this file as we complete each step. Do not mark an item complete until it has been run and verified.

## Overall progress

- [x] Setup verified — JDK 21, VS Code and Maven working
- [x] Day 1 complete
- [ ] Day 2 complete (in progress)
- [ ] Day 3 complete
- [ ] Day 4 complete
- [ ] Day 5 complete
- [ ] End-to-end prototype verified

**Current status:** Day 1 complete. Day 2 has started with Spring vs Spring Boot, Dependency Injection, Spring Beans, constructor injection, singleton scope and stateless Bean/thread-safety fundamentals covered conceptually. Spring Boot application setup and implementation are still pending.

## Daily tracker

| Day | Planned focus | Status | Notes / blockers |
|---|---|---|---|
| Setup | JDK 21, VS Code, Maven, Git | Complete | Temurin JDK 21.0.13 and Maven 3.6.3 verified on Java 21 |
| Day 1 | Java fundamentals and device console app | Complete | Device and SensorReading implemented; Java 21 compilation succeeds; Spotless apply/check succeed; 8 JUnit tests pass (0 failures/errors). Ready for learner commit/push |
| Day 2 | Spring Boot REST and persistence | In progress | Covered Spring vs Spring Boot, DI, Bean relationships, constructor injection, singleton scope and shared-state/thread-safety basics. Next: `@SpringBootApplication` and create/run the Device Service. |
| Day 3 | Microservices, gRPC and SSE | Not started | Separate services and service-owned data |
| Day 4 | WebSocket and React dashboard | Not started | Real-time telemetry integration |
| Day 5 | Security, tests, Docker and integration | Not started | End-to-end prototype verification |

## Decisions and notes

| Date | Decision / learning | Reason |
|---|---|---|
| 2026-11-14 | Keep the original five-day sprint as the immediate priority | Complete the practical project before moving to the extended backend curriculum |
| 2026-11-14 | Use StringBuilder for independent per-thread string construction; StringBuffer only when a mutable buffer is shared across threads and synchronized methods are appropriate | Thread count alone does not require StringBuffer; shared mutable state is the key distinction |

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
