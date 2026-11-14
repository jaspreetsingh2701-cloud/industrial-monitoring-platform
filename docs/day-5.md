# Day 5 — Integration and Production Foundations

**Outcome:** Run, test and explain the complete learning prototype.

| Block | Topics — one-line guide |
|---|---|
| Hour 1 | Authentication, authorization, secrets, CORS and backend security basics. |
| Hour 2 | Resilience: validation, timeouts, bounded retries, error handling and observability. |
| Hour 3 | JUnit 5, Mockito, integration tests, React Testing Library and test boundaries. |
| Hour 4 | Docker images, containers, environment variables, networks and Docker Compose. |
| Hour 5 | Integrate services and dashboard; document setup, APIs, architecture and run commands. |
| Hour 6 | End-to-end verification, mock interview, architecture walkthrough and next-step roadmap. |

## Deliverable
A documented, locally runnable prototype with core tests and an architecture walkthrough.

## Completion checklist
- [ ] Explain authentication vs authorization.
- [ ] Explain timeout, retry and failure-handling trade-offs.
- [ ] Run backend tests and frontend tests.
- [ ] Start the local stack using documented commands.
- [ ] Explain the complete data flow and the role of each protocol.
- [ ] List what remains before production readiness.


## Deployment runtime and container boundary

For our Spring Boot services, the expected local deployment model is an executable JAR with embedded Tomcat (for Spring MVC / `spring-boot-starter-web`). In Docker, the JAR and its JVM run inside the service container; embedded Tomcat listens on the container's configured application port. Docker port publishing maps a host port to that container port.

A reverse proxy such as NGINX can be placed in front of services for routing or TLS termination, but it is not the Spring Servlet container.

- [ ] Explain the process boundary: JVM + Spring Boot + embedded Tomcat.
- [ ] Identify the application listening port and Docker host-to-container port mapping.
- [ ] Explain when an external Tomcat/WAR deployment is used.
