# Day 2 — Spring Boot and REST APIs

**Outcome:** Build a persistent Device Service with HTTP endpoints.

| Block | Topics — one-line guide |
|---|---|
| Hour 1 | Spring Boot, Maven project structure, annotations, application startup and dependency injection. |
| Hour 2 | HTTP methods, REST resources, controllers, DTOs, JSON and status codes. |
| Hour 3 | Service/repository layers, validation, exception handling and clean API boundaries. |
| Hour 4 | PostgreSQL, relational modelling, JPA entities, repositories and transactions. |
| Hour 5 | Implement device create/list/get/update endpoints and connect persistence. |
| Hour 6 | Test endpoints, add unit tests, debug common errors and revise interview questions. |

## Deliverable
Device Service running locally with REST endpoints and PostgreSQL persistence.

## Completion checklist
- [ ] Explain dependency injection and common Spring annotations.
- [ ] Implement controller, service and repository layers.
- [ ] Return appropriate HTTP status codes.
- [ ] Persist and retrieve device records.
- [ ] Test endpoints using curl or an API client.


## Embedded web server — Tomcat and deployment model

A Spring Boot application using `spring-boot-starter-web` (Spring MVC) normally uses **embedded Apache Tomcat** by default. Embedded means Tomcat is included in the application's dependencies and starts inside the Spring Boot process; a separate Tomcat installation is not required for the usual executable-JAR deployment.

Typical startup flow:

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

Do not confuse an application server/web container with a reverse proxy. NGINX or Apache HTTP Server may sit in front of Tomcat to handle TLS termination, routing or load balancing; they do not replace Spring MVC controllers.

### Interview answer

“For a typical Spring Boot MVC service using spring-boot-starter-web, embedded Tomcat is the default. We package it as an executable JAR and start it with java -jar; Tomcat starts within the application and handles HTTP requests. We can also package a WAR for an external Tomcat, or select alternatives such as Jetty or Undertow when required.”

### Checklist

- [ ] Explain embedded vs external Tomcat.
- [ ] Explain JAR vs WAR deployment.
- [ ] Identify Tomcat as the default embedded server for Spring MVC starter-web.
- [ ] Configure and verify a non-default port.
- [ ] Explain the difference between Tomcat and a reverse proxy such as NGINX.
