# Day 2 — Spring Boot and REST APIs

**Outcome:** Turn the Day 1 Java domain model into a Spring Boot Device Service with REST endpoints and PostgreSQL persistence.

## Learning labels

Each section uses small labels so you can quickly identify the purpose and depth of a topic.

| Label | Meaning |
|---|---|
| `BASIC` | Core concepts and beginner-friendly foundations |
| `INTERMEDIATE` | Applying concepts together and understanding trade-offs |
| `ADVANCED` | Deeper internals, scaling and complex design decisions |
| `INTERVIEW` | Common interview questions and concise answers |
| `PRODUCTION` | Practices expected in maintainable, deployable services |
| `REAL USE CASE` | How the concept is used in our Industrial Monitoring Platform |

A section can have more than one label, for example `INTERMEDIATE` + `PRODUCTION`.

## Day plan

| Block | Topics |
|---|---|
| Hour 1 | Spring Boot, Maven project structure, annotations, application startup and dependency injection. |
| Hour 2 | HTTP methods, REST resources, controllers, DTOs, JSON and status codes. |
| Hour 3 | Service/repository layers, validation, exception handling and clean API boundaries. |
| Hour 4 | PostgreSQL, relational modelling, JPA entities, repositories and transactions. |
| Hour 5 | Implement device create/list/get/update endpoints and connect persistence. |
| Hour 6 | Test endpoints, add unit tests, debug common errors and revise interview questions. |

---

## 1. Spring Boot foundations

**Labels:** `BASIC` · `REAL USE CASE`

- What Spring and Spring Boot solve.
- Spring Boot project structure and Maven dependencies.
- Application entry point and `@SpringBootApplication`.
- Application configuration and profiles.
- Start and stop the application; understand startup logs.

**Practice:** Create the Device Service application and run it locally.

## 2. Dependency Injection and Spring container

**Labels:** `BASIC` → `INTERMEDIATE` · `INTERVIEW` · `PRODUCTION`

- What the Spring IoC container does.
- Inversion of Control (IoC) vs Dependency Injection (DI).
- Beans and bean lifecycle at a beginner level.
- `@Component`, `@Service`, `@Repository`, `@Controller` and `@RestController`.
- Constructor injection and why we prefer it over field injection.

**Practice:** Inject DeviceService into the REST controller using constructor injection.

## 3. HTTP and REST API design

**Labels:** `BASIC` · `REAL USE CASE` · `INTERVIEW`

- HTTP request/response, headers, body and JSON.
- Resource-oriented URLs.
- GET, POST, PUT, PATCH and DELETE.
- Status codes: 200, 201, 204, 400, 404 and 500.
- Idempotency and safe methods at a practical level.

**Practice:** Design endpoints for registering, listing, finding, updating and deactivating devices.

## 4. Controller, service and DTO layers

**Labels:** `INTERMEDIATE` · `PRODUCTION` · `REAL USE CASE`

- Controller: HTTP boundary; keep business logic out.
- Service: business rules and orchestration.
- DTO: request/response contract; don't expose persistence entities by default.
- Entity/model: represent persisted data.
- Map request DTOs to domain/entity objects and map results to response DTOs.

**Practice:** Implement a clean request flow:
`HTTP request → Controller → Service → Repository → Database`.

## 5. Validation and error handling

**Labels:** `INTERMEDIATE` · `PRODUCTION` · `INTERVIEW`

- Validate incoming request data.
- Understand common validation annotations.
- Return consistent error responses.
- Handle not-found and invalid-input cases.
- Use centralized exception handling with `@ControllerAdvice`.

**Practice:** Reject invalid device data and return a useful 400 response; return 404 for an unknown device.

## 6. PostgreSQL and Spring Data JPA

**Labels:** `INTERMEDIATE` · `PRODUCTION` · `REAL USE CASE`

- Relational tables, primary keys and constraints.
- Entity mapping and JPA annotations.
- Spring Data repositories.
- Basic CRUD persistence.
- Transactions and the purpose of `@Transactional`.
- Keep database access inside the service's repository boundary.

**Practice:** Persist devices and retrieve them after restarting the application.

## 7. Testing and API verification

**Labels:** `BASIC` → `INTERMEDIATE` · `PRODUCTION`

- Unit tests for service logic.
- Controller/API tests.
- Test success, validation, not-found and failure cases.
- Use curl or an API client to exercise endpoints.
- Keep tests repeatable and independent.

**Practice:** Verify the Device Service endpoints and add automated tests.

### JUnit vs Maven Surefire

**Labels:** `BASIC` · `INTERVIEW`

These tools have different responsibilities:

- **JUnit 5 (JUnit Jupiter):** The testing framework used to write tests with annotations such as `@Test` and assertions such as `assertEquals()`.
- **Maven Surefire:** A Maven plugin that discovers and launches tests during Maven's `test` lifecycle phase. It integrates Maven with the JUnit Platform.
- **JUnit Platform:** The test execution infrastructure that allows test engines, such as JUnit Jupiter, to run tests.
- **IDE test runner:** Tools in IDEs such as VS Code or IntelliJ can run JUnit tests directly, without invoking Maven Surefire.

**Important:** JUnit does not require Surefire in every execution environment. Surefire is the usual bridge when tests are run through Maven. For example, `mvn test` invokes Surefire; clicking **Run Test** in an IDE can use the IDE's own test runner.

**Maven commands:**

~~~powershell
# Run tests through Maven and Surefire.
mvn test

# Run tests and then continue through packaging.
mvn package
~~~

**Remember:** JUnit defines and executes test logic; Surefire integrates test execution into Maven's build lifecycle.

---

## Embedded web server — Tomcat and deployment model

**Labels:** `BASIC` · `INTERVIEW` · `PRODUCTION`

A Spring Boot application using `spring-boot-starter-web` (Spring MVC) normally uses **embedded Apache Tomcat** by default. Embedded means Tomcat is included in the application's dependencies and starts inside the Spring Boot process; a separate Tomcat installation is not required for the usual executable-JAR deployment.

### Typical startup flow

1. The `main()` method calls `SpringApplication.run(...)`.
2. Spring Boot creates the application context and configures the web server.
3. Embedded Tomcat starts and listens for HTTP requests (default port is usually 8080).
4. Tomcat's Servlet infrastructure passes requests into Spring MVC, where DispatcherServlet routes them to controller methods.

Example configuration:

~~~properties
server.port=8081
~~~

This changes the embedded server's listening port from the default 8080 to 8081.

### Two deployment models

| Model | Packaging and runtime |
|---|---|
| Embedded server | Usually an executable JAR; run with `java -jar device-service.jar`. The application starts its embedded Tomcat. |
| External server | Package as a WAR and deploy it to an independently installed/configured Tomcat. |

Spring Boot can use other embedded servers, such as Jetty or Undertow, depending on dependencies and configuration. Reactive Spring WebFlux commonly uses Reactor Netty by default; Netty is an asynchronous networking framework, not a Servlet container.

**Production note:** Do not confuse an application server/web container with a reverse proxy. NGINX or Apache HTTP Server may sit in front of Tomcat to handle TLS termination, routing or load balancing; they do not replace Spring MVC controllers.

### Interview answer

“For a typical Spring Boot MVC service using spring-boot-starter-web, embedded Tomcat is the default. We package it as an executable JAR and start it with java -jar; Tomcat starts within the application and handles HTTP requests. We can also package a WAR for an external Tomcat, or select alternatives such as Jetty or Undertow when required.”

### Checklist

- [ ] Explain embedded vs external Tomcat.
- [ ] Explain JAR vs WAR deployment.
- [ ] Identify Tomcat as the default embedded server for Spring MVC starter-web.
- [ ] Configure and verify a non-default port.
- [ ] Explain the difference between Tomcat and a reverse proxy such as NGINX.

---

## Day 2 completion checklist

- [ ] Explain dependency injection and common Spring annotations.
- [ ] Implement controller, service and repository layers.
- [ ] Return appropriate HTTP status codes.
- [ ] Validate requests and handle errors consistently.
- [ ] Persist and retrieve device records.
- [ ] Test endpoints using curl or an API client.
- [ ] Run the application and explain the request flow.

## Day 2 working agreement

For each topic, follow this sequence:

1. **Learn:** short explanation, with JavaScript/TypeScript comparisons where useful.
2. **Implement:** add the concept to the actual project using exact file paths.
3. **Test:** run focused tests and verify behaviour.
4. **Review:** discuss edge cases, production practices and interview questions.
5. **Document:** update this file and `PROGRESS.md` as milestones are verified.

We will cover the five-day sprint first. Advanced topics from the extended roadmap remain separate so they don't distract from completing this working service.
