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

### Database persistence foundations — concept lesson

**Labels:** `BASIC` · `INTERMEDIATE` · `INTERVIEW` · `REAL USE CASE`

This section records the concepts introduced before connecting the Device Service to PostgreSQL. PostgreSQL is running locally in Docker; application connectivity and JPA-backed persistence are the next implementation steps.

#### In-memory collection vs relational database

The current `DeviceRepository` stores devices in an `ArrayList`:

```java
// Store device objects in the running application's memory.
private final List<Device> devices = new ArrayList<>();
```

- **In-memory storage:** Data exists in the running application's memory. It is initially empty and is lost when the process stops or restarts.
- **PostgreSQL persistence:** Data is stored in database files managed by PostgreSQL. Records remain available after the Spring Boot application restarts, subject to database durability and operational configuration.

A relational database organises data into tables. A table contains rows (records) and columns (fields). For example, a `devices` table can have `id`, `name` and `type` columns. A primary key uniquely identifies each row.

#### JDBC — Java Database Connectivity

**BASIC | INTERVIEW**

JDBC is the standard Java API for connecting to relational databases and executing SQL. It provides lower-level building blocks for opening connections, sending SQL statements and reading results. An application can use JDBC directly, but it must handle more of the SQL execution and result mapping itself.

#### JPA and Hibernate

**BASIC | INTERMEDIATE | INTERVIEW**

- **JPA (Jakarta Persistence):** A Java persistence specification. It defines standard APIs and mapping rules for storing and retrieving Java objects in relational databases. JPA is not itself a database or an implementation.
- **Hibernate ORM:** A popular implementation of the JPA specification. It maps Java objects to relational tables and translates persistence operations into SQL. It also manages entity state and other ORM behaviour.
- **ORM (Object-Relational Mapping):** The technique of mapping objects in an object-oriented program to rows and tables in a relational database.

A useful mental model is: JPA defines the contract; Hibernate provides an implementation of that contract. JDBC is a lower-level database connectivity API that Hibernate can use underneath.

#### Entity mapping

**INTERMEDIATE | REAL USE CASE**

An Entity is a Java class mapped to a database table. JPA annotations describe the mapping. For example, this is an illustrative mapping for the Device model; do not replace the current project class until the implementation step:

```java
// Import the Jakarta Persistence annotations explicitly.
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Map this Java class to the devices table.
@Entity
@Table(name = "devices")
public class Device {

    // Identify the primary key column.
    @Id
    private Long id;

    // Map the device name to a table column.
    private String name;

    // Map the device type to a table column.
    private String type;

    // JPA requires a no-argument constructor for this entity.
    public Device() {
    }
}
```

The `@Entity` annotation marks the class as a persistent entity, `@Table` specifies its table name, and `@Id` identifies its primary key. We will later review generated IDs, column constraints, constructors and entity design in the actual service.

#### Where Spring Data JPA fits

**INTERMEDIATE | PRODUCTION**

Spring Data JPA builds on JPA and reduces repository boilerplate. Instead of manually implementing every common CRUD query, we can define a repository interface and let Spring Data provide the implementation at runtime. We will introduce this when replacing the current in-memory `DeviceRepository`.

#### Transactions — preview

**INTERMEDIATE | PRODUCTION**

A database transaction groups one or more database operations into a unit of work. The goal is to preserve consistency: a transaction should complete successfully as a whole or be rolled back when it fails. We will cover transaction boundaries and `@Transactional` during the persistence implementation.

#### Request-to-database flow

```text
HTTP request
    |
    v
DeviceController
    |
    v
DeviceService
    |
    v
Spring Data repository
    |
    v
JPA API
    |
    v
Hibernate ORM
    |
    v
JDBC
    |
    v
PostgreSQL
```

This is the conceptual path for the database-backed implementation. The current repository is still an in-memory `ArrayList`; the database-backed path is not implemented yet.

**JavaScript/TypeScript comparison:** PostgreSQL is the same database engine regardless of backend language. JPA/Hibernate serve an ORM role broadly comparable to tools such as Prisma or TypeORM in a Node.js application, although their APIs and runtime models differ.

**Interview takeaway:** “JPA is the persistence specification, while Hibernate is a common implementation of it. Hibernate maps Java entities to relational tables and translates persistence operations into SQL. JDBC provides the lower-level Java database connectivity.”

---

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

## Expanded Day 2 learning sequence and progress

The expanded sequence below is the working checklist for the current learning session. A checked item means the concept was covered and/or the implementation was run and verified; it does not imply that every production enhancement is complete.

| # | Topic | Status | Evidence / notes |
|---|---|---|---|
| 1 | Spring vs Spring Boot, IoC, DI, Beans and singleton scope | Complete | Covered constructor injection, Bean reuse, stateless services and thread-safety implications. |
| 2 | `@SpringBootApplication`, startup lifecycle and embedded server | Complete | Device Service starts with Spring Boot 4.1.2 and embedded Tomcat 11 on port 8080. |
| 3 | HTTP and REST fundamentals | Complete | Exercised GET, POST, PUT and DELETE, request/response bodies and status codes. |
| 4 | Controllers, service and repository responsibilities | Complete | Implemented separate controller, service and in-memory repository classes. |
| 5 | Path variables and request bodies | Complete | Implemented `/devices/{id}` and JSON request payloads. Explicit `@PathVariable("id")` avoids dependence on compiler parameter-name metadata. |
| 6 | DTOs, entities/models and JSON | Complete | Added `DeviceRequest` DTO and mapped it to the Device model. |
| 7 | Validation and exception handling | Complete | Added Jakarta validation and a global REST exception handler. |
| 8 | API verification with PowerShell | Complete | Verified create, list, get, update, delete, duplicate ID, invalid name and missing-device responses. |
| 9 | JUnit 5, Mockito and service tests | Complete | Six service tests pass. |
| 10 | Controller tests with MockMvc | Complete | Four `@WebMvcTest` controller tests pass. |
| 11 | Configuration, profiles, environment variables and logging | In progress | Separated common, dev, test and prod properties. Dev uses Docker PostgreSQL on localhost:5433; test uses a separate database; prod requires external credentials. Profile startup and database connectivity still need verification. |
| 12 | PostgreSQL, JPA, Hibernate and transactions | Concepts introduced | Covered in-memory vs persistent storage, JDBC, JPA as a specification, Hibernate as an implementation, ORM and Entity mapping. PostgreSQL setup, Spring Data JPA and transaction implementation remain pending. |
| 13 | Complete persistent Device CRUD API | In progress | CRUD endpoints work; persistence migration remains. |
| 14 | Debugging, production practices and interview review | In progress | Continue with configuration/logging, persistence and final review. |

## Verified Device Service implementation

**Labels:** `INTERMEDIATE` · `REAL USE CASE` · `PRODUCTION`

Current implementation lives under `services/device-service/src/main/java/com/monitoring/device/`:

| Area | File | Responsibility |
|---|---|---|
| Application entry point | `DeviceServiceApplication.java` | Boots the Spring application. |
| Controller | `controller/DeviceController.java` | Exposes the HTTP endpoints. |
| Request DTO | `dto/DeviceRequest.java` | Defines and validates incoming device data. |
| Model | `model/Device.java` | Represents a device in the current in-memory implementation. |
| Service | `service/DeviceService.java` | Applies device business rules. |
| Repository | `repository/DeviceRepository.java` | Stores and retrieves devices in memory. |
| Custom exception | `exception/DuplicateDeviceException.java` | Represents duplicate device IDs. |
| Global handler | `exception/GlobalExceptionHandler.java` | Converts known exceptions and validation failures into HTTP responses. |

### Implemented API contract

| Method | Endpoint | Expected success response |
|---|---|---|
| GET | `/devices` | `200 OK` with the device list |
| GET | `/devices/{id}` | `200 OK` with the device, or `404 Not Found` |
| POST | `/devices` | `201 Created` with the created device and a Location header |
| PUT | `/devices/{id}` | `200 OK` with the updated device, or `404 Not Found` |
| DELETE | `/devices/{id}` | `204 No Content`, or `404 Not Found` |

### Verified validation and error behaviour

- Duplicate device ID returns `409 Conflict`: the request is structurally valid, but conflicts with the existing resource state.
- Invalid request data, such as a blank device name, returns `400 Bad Request`.
- A request for a missing device returns `404 Not Found`.
- The controller delegates business rules to the service; the repository is not injected directly into the controller.

### Automated test results

The latest verified command was:

```powershell
# Run a clean test cycle for the Device Service module.
.\scripts\device.ps1 test
```

**Verified result:** 10 tests passed — 6 service tests and 4 controller tests, with 0 failures, 0 errors and 0 skipped tests.

- Service tests use JUnit 5 and Mockito to isolate `DeviceService` from the repository.
- Controller tests use `@WebMvcTest(DeviceController.class)`, MockMvc and `@MockitoBean` to exercise the HTTP layer without starting a real embedded HTTP server.
- `@SpringBootTest` loads the full application context; `@WebMvcTest` loads a focused MVC test slice.
- In-memory data is lost when the application restarts. PostgreSQL/JPA persistence is still pending.

### Current implementation limitations

- The repository is currently an in-memory `ArrayList`, not a database-backed repository.
- PostgreSQL, JPA/Hibernate mappings and transaction behaviour have not yet been implemented.
- Environment-specific profiles and externalised secrets/configuration are still to be covered.
- Production logging has not yet replaced all demonstration output.

## Day 2 completion checklist

- [x] Explain dependency injection, Beans, singleton scope and common Spring annotations.
- [x] Start the Spring Boot Device Service and explain the embedded Tomcat request flow.
- [x] Implement controller, service and repository layers.
- [x] Implement GET, POST, PUT and DELETE endpoints with appropriate status codes.
- [x] Validate requests and handle known errors centrally.
- [x] Verify successful and failure API scenarios.
- [x] Add service tests using JUnit 5 and Mockito.
- [x] Add controller tests using MockMvc.
- [ ] Finish and verify profiles, environment variables and application logging.
- [ ] Set up PostgreSQL and persist/retrieve device records using Spring Data JPA.
- [ ] Complete the remaining production/debugging and interview review.

## Day 2 working agreement

For each topic, follow this sequence:

1. **Learn:** short explanation, with JavaScript/TypeScript comparisons where useful.
2. **Implement:** add the concept to the actual project using exact file paths.
3. **Test:** run focused tests and verify behaviour.
4. **Review:** discuss edge cases, production practices and interview questions.
5. **Document:** update this file and `PROGRESS.md` as milestones are verified.

We will cover the five-day sprint first. Advanced topics from the extended roadmap remain separate so they don't distract from completing this working service.


---

## Application configuration and profiles

**Labels:** `BASIC` · `INTERMEDIATE` · `PRODUCTION`

Spring Boot loads `application.properties` for common settings. When a profile is active, it also loads the matching file and lets profile-specific values override common values.

| File | Purpose |
|---|---|
| `application.properties` | Shared application settings, including the application name and JPA open-in-view setting |
| `application-dev.properties` | Local development on port 8081 and the Docker PostgreSQL database |
| `application-test.properties` | Test configuration on port 8082 and a separate test database |
| `application-prod.properties` | Production port 8080 and externally supplied database credentials |

### Activate a profile

```powershell
# Use the local development profile in this PowerShell session.
$env:SPRING_PROFILES_ACTIVE = "dev"

# Supply the password used when creating the local PostgreSQL container.
$env:DB_PASSWORD = "<your local Docker PostgreSQL password>"

# Start the Device Service.
.\scripts\device.ps1 run
```

### Database settings by profile

- **Dev:** Defaults to `localhost:5433/industrial_monitoring`, username `monitoring`. The password comes from `DB_PASSWORD`. Hibernate uses `ddl-auto=update` for local learning.
- **Test:** Defaults to `localhost:5433/industrial_monitoring_test`. Create this separate database before activating the test profile. Set `TEST_DB_PASSWORD`. Hibernate uses `create-drop` for this disposable test schema.
- **Prod:** Requires `DB_URL`, `DB_USERNAME` and `DB_PASSWORD`. Hibernate uses `validate`; production schema changes must be handled by migrations.

The current Mockito and MockMvc tests do not activate the test profile or connect to PostgreSQL. A separate database-backed integration test will be added when persistence is implemented.

**Important:** Never commit real database passwords. Environment variables set in PowerShell apply only to that PowerShell session.

### Configuration verification checklist

- [ ] Start the Device Service with the `dev` profile and confirm it connects to Docker PostgreSQL.
- [ ] Create the separate `industrial_monitoring_test` database.
- [ ] Verify the `test` profile against that database.
- [ ] Supply production settings through the deployment environment.
- [ ] Replace Hibernate schema updates with Flyway migrations before production use.
