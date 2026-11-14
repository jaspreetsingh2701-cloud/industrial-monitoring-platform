# Extended Backend and Interview Roadmap — Days 6–18

This roadmap extends the five-day Industrial Monitoring Platform sprint. It incorporates the additional three-phase Java/Spring/microservices interview syllabus that was reviewed separately.

**Important scope distinction:** Days 1–5 build the working prototype. Days 6–18 broaden the knowledge base for interviews and deeper backend engineering. A topic appearing here means it is scheduled, not already mastered. For advanced topics, aim first for a clear explanation, a small example and awareness of trade-offs; implement the important ones in the project.

## Day 6 — Core Java interview essentials

**Outcome:** Explain frequently asked Java language and API distinctions and solve basic collection/string coding exercises.

| Block | Topics |
|---|---|
| Hour 1 | Method overloading vs overriding; compile-time vs runtime polymorphism; `==` vs `.equals()`; object equality |
| Hour 2 | String immutability, String pool, StringBuilder vs StringBuffer; `final`, `finally`, `finalize()` and why finalization is deprecated |
| Hour 3 | Checked vs unchecked exceptions; try/catch/finally; custom exceptions; `static`; wrapper classes and autoboxing |
| Hour 4 | ArrayList vs LinkedList; List vs Set vs Map; HashMap vs legacy Hashtable; HashSet implementation concept |
| Hour 5 | Comparable vs Comparator; sorting objects; reverse a string; find duplicates in a list |
| Hour 6 | Word-frequency count with a Map/Streams; implement a small CRUD `@RestController` walkthrough; interview review |

**Practice:** Implement Device equality correctly using a stable identity field; sort devices by name and by ID using Comparable/Comparator.

## Day 7 — Collections internals, generics and advanced Streams

**Outcome:** Understand collection behaviour, equality contracts and useful Stream collectors.

| Block | Topics |
|---|---|
| Hour 1 | HashMap buckets, hashing, collisions, resizing and load factor; tree bins and when they are used |
| Hour 2 | `equals()`/`hashCode()` contract; ConcurrentHashMap vs synchronizedMap; fail-fast iterators and concurrent collection alternatives |
| Hour 3 | Generics, type erasure, bounded types, wildcards, `? extends`, `? super` and PECS |
| Hour 4 | Stream `flatMap`, `groupingBy`, `partitioningBy`, `Collectors.toMap`, sorting and duplicate-key handling |
| Hour 5 | Parallel streams: when they help, ordering, shared state and common-pool caveats; try-with-resources and AutoCloseable |
| Hour 6 | Serialization basics: Serializable, serialVersionUID and transient; collection coding exercises and review |

**Practice:** Group telemetry readings by device ID, partition devices by active state and build a Map keyed by device ID.

**Implementation note:** HashMap treeification is conditional, not simply “eight entries always means a tree”: capacity and collision-bin thresholds both matter. The default load factor is 0.75.

## Day 8 — Java concurrency and asynchronous programming

**Outcome:** Explain Java threading primitives and build a small safe producer/consumer example.

| Block | Topics |
|---|---|
| Hour 1 | Thread vs Runnable vs Callable; thread lifecycle; why ExecutorService is preferred over manually creating many threads |
| Hour 2 | synchronized vs volatile; visibility vs atomicity; race conditions and thread safety |
| Hour 3 | Deadlocks, lock ordering, wait/notify vs sleep; why wait releases the monitor and sleep does not |
| Hour 4 | Concurrent collections and BlockingQueue; producer-consumer pattern |
| Hour 5 | CompletableFuture: thenApply, thenCompose, thenCombine, exceptionally; composing async operations |
| Hour 6 | Virtual threads in Java 21; blocking I/O use cases, limitations and practical concurrency review |

**Practice:** Build a bounded producer-consumer telemetry queue and demonstrate safe shutdown and error handling.

## Day 9 — JVM, memory and performance diagnostics

**Outcome:** Explain how Java code runs and identify common causes of memory/CPU problems.

| Block | Topics |
|---|---|
| Hour 1 | JVM memory areas: heap, stack, Metaspace and PC register; object allocation and references |
| Hour 2 | Garbage collection concepts; young/old generations; minor/major terminology; G1 overview |
| Hour 3 | Memory leaks: static collections, listeners, unclosed resources and retained references |
| Hour 4 | Class loading: bootstrap, platform and application class loaders; parent delegation |
| Hour 5 | JIT compilation, C1/C2, escape analysis, string deduplication and off-heap memory overview |
| Hour 6 | GC logs, heap/thread dumps, jcmd, jstack, Java Flight Recorder and a troubleshooting walkthrough |

**Awareness topics:** G1 vs ZGC vs Shenandoah; know the use case and trade-offs before attempting JVM tuning.

## Day 10 — Spring Core and Spring Boot

**Outcome:** Build a clear mental model of the Spring container and explain how a Spring Boot application starts.

| Block | Topics |
|---|---|
| Hour 1 | Spring Framework vs Spring Boot; IoC container; dependency injection; constructor, setter and field injection |
| Hour 2 | Stereotypes: @Component, @Service, @Repository, @Controller, @RestController; @SpringBootApplication composition |
| Hour 3 | Starters, auto-configuration, @Autowired, @Value, @Bean; component scanning and configuration classes |
| Hour 4 | application.properties / application.yml; profiles, @Profile, environment-specific configuration and secrets |
| Hour 5 | Bean scopes, lifecycle, @PostConstruct / @PreDestroy, @Qualifier / @Primary, circular dependencies |
| Hour 6 | Validation, @ControllerAdvice / @ExceptionHandler, Actuator health/metrics, AOP concepts and API exercise |

**Practice:** Refactor the Device Service to use constructor injection, DTO validation and centralized exception responses.

**Modern Boot note:** Auto-configuration registration uses `AutoConfiguration.imports` in modern Spring Boot versions; older material may refer to spring.factories. Learn the version-appropriate mechanism.

## Day 11 — Spring Data JPA, Hibernate and SQL

**Outcome:** Persist device and telemetry data and explain ORM behaviour and common query problems.

| Block | Topics |
|---|---|
| Hour 1 | SQL foundations: joins, indexes, GROUP BY/HAVING, normalization and ACID |
| Hour 2 | JPA specification vs Hibernate implementation; entities, IDs, repositories and derived queries |
| Hour 3 | @OneToMany, @ManyToOne, @ManyToMany, owning side and mappedBy |
| Hour 4 | Lazy vs eager loading; N+1 problem; JOIN FETCH and EntityGraph |
| Hour 5 | @Transactional, propagation, isolation, rollback defaults, self-invocation/proxy caveat |
| Hour 6 | Pagination with Pageable/Page; entity states; optimistic @Version vs pessimistic locking overview |

**Practice:** Add device persistence and paginated device retrieval. Inspect SQL generated by a relationship query.

## Day 12 — Spring Security and API security

**Outcome:** Explain authentication vs authorization and secure a REST API using a modern Spring Security configuration.

| Block | Topics |
|---|---|
| Hour 1 | Authentication vs authorization; principal, roles and authorities |
| Hour 2 | Security filter chain, SecurityFilterChain bean, UserDetailsService and PasswordEncoder (BCrypt) |
| Hour 3 | JWT structure, signature validation, stateless authentication and request filter flow |
| Hour 4 | CORS vs CSRF; browser security model; when CSRF protection is required and when it may be disabled for stateless APIs |
| Hour 5 | OAuth2/OIDC overview, Authorization Code + PKCE and Client Credentials |
| Hour 6 | Resource server, method security (@PreAuthorize), refresh/revocation concepts and security review |

**Practice:** Secure device endpoints by role and test unauthenticated, forbidden and authorized requests.

## Day 13 — Microservices architecture and resilience

**Outcome:** Explain service boundaries and design for partial failures.

| Block | Topics |
|---|---|
| Hour 1 | Monolith vs microservices; benefits and costs; bounded contexts; database per service |
| Hour 2 | API Gateway, routing, authentication and rate limiting; service discovery with Eureka/Consul concepts |
| Hour 3 | Central configuration; synchronous REST clients (WebClient/Feign; RestTemplate legacy context) and asynchronous messaging |
| Hour 4 | Timeouts, retries, exponential backoff, jitter, idempotency, circuit breaker states, bulkhead and rate limiter |
| Hour 5 | Correlation IDs, centralized logs, distributed tracing, Micrometer/Zipkin and ELK concepts |
| Hour 6 | API versioning, pagination, OpenAPI, gRPC vs REST vs GraphQL; service boundary review |

**Practice:** Add explicit timeouts and bounded retry behaviour to a service call. Explain why retries need idempotency.

## Day 14 — Kafka, messaging, databases and caching

**Outcome:** Understand asynchronous event processing and the data infrastructure around services.

| Block | Topics |
|---|---|
| Hour 1 | Kafka topics, partitions, producers, consumers, consumer groups and offsets |
| Hour 2 | Delivery semantics: at-most-once, at-least-once and exactly-once concepts; ordering within a partition |
| Hour 3 | Replication factor, acks, min.insync.replicas, offset commits, consumer lag, retry topics and dead-letter topics |
| Hour 4 | Schema Registry and Avro overview; Kafka vs RabbitMQ and when to choose each |
| Hour 5 | Redis and caching: @Cacheable/@CacheEvict; cache-aside, write-through, write-behind, TTL and eviction |
| Hour 6 | SQL vs NoSQL; MongoDB/Cassandra/DynamoDB use cases; read replicas, sharding, partitioning and connection pools |

**Practice:** Sketch a telemetry event flow and explain partition key choice, duplicate handling and consumer restart behaviour.

## Day 15 — Advanced Spring

**Outcome:** Understand key Spring internals and advanced backend implementation trade-offs.

| Block | Topics |
|---|---|
| Hour 1 | SpringApplication.run lifecycle; BeanFactoryPostProcessor vs BeanPostProcessor |
| Hour 2 | Spring proxies: JDK dynamic proxies vs CGLIB; self-invocation and AOP implications |
| Hour 3 | Custom auto-configuration and starter concepts with @ConditionalOnProperty / @ConditionalOnMissingBean |
| Hour 4 | Transaction edge cases, readOnly, propagation, isolation, transaction synchronization and locking |
| Hour 5 | WebFlux/Reactor: Mono, Flux, backpressure; reactive vs blocking I/O |
| Hour 6 | HikariCP, batch inserts, second-level cache, DTO projections, open-in-view and GraalVM native-image trade-offs |

**Practice:** Explain why a @Transactional method called from another method in the same object may bypass proxy behaviour.

## Day 16 — Distributed systems and advanced API patterns

**Outcome:** Reason about consistency, retries, multi-service workflows and failure recovery.

| Block | Topics |
|---|---|
| Hour 1 | CAP theorem, PACELC and eventual consistency |
| Hour 2 | Saga pattern: choreography vs orchestration; compensating transactions |
| Hour 3 | Transactional outbox, CDC/Debezium, CQRS and Event Sourcing |
| Hour 4 | Idempotency keys, deduplication, distributed transactions and why 2PC is often avoided |
| Hour 5 | Distributed locks, Redis lock trade-offs, ZooKeeper and database locks; service mesh, mTLS and traffic shaping |
| Hour 6 | Strangler Fig migration, HATEOAS, gRPC vs REST vs GraphQL, token bucket/leaky bucket/sliding-window rate limiting |

**Practice:** Design a device provisioning workflow with retries, duplicate messages and a compensating action.

## Day 17 — Testing, delivery, cloud and observability

**Outcome:** Test and package the platform and understand a production delivery pipeline.

| Block | Topics |
|---|---|
| Hour 1 | JUnit 5, Mockito, @SpringBootTest, @WebMvcTest, @DataJpaTest and test boundaries |
| Hour 2 | Testcontainers; integration testing with real dependencies; React Testing Library and end-to-end test strategy |
| Hour 3 | Dockerfile, image vs container, volumes, networks, environment variables and Docker Compose |
| Hour 4 | CI/CD pipelines, GitHub Actions, preview/staging/production environments, blue-green/canary releases and feature flags |
| Hour 5 | Kubernetes: Pod, Deployment, Service, Ingress, ConfigMap, Secret, HPA, liveness/readiness probes |
| Hour 6 | Observability: Prometheus/Grafana, logs, traces, OpenTelemetry, SLIs/SLOs; secrets management, OWASP Top 10 and zero trust |

**Practice:** Containerize the local services, run tests in CI and document health/readiness checks.

## Day 18 — Design patterns, system design and behavioral interviews

**Outcome:** Structure senior-level design discussions and communicate engineering decisions clearly.

| Block | Topics |
|---|---|
| Hour 1 | SOLID, DRY, KISS, YAGNI; cohesion, coupling and clean/hexagonal architecture |
| Hour 2 | Creational patterns: Singleton (including enum approach), Factory and Builder |
| Hour 3 | Structural patterns: Adapter, Decorator, Proxy and Facade |
| Hour 4 | Behavioral patterns: Strategy, Observer, Template Method and Chain of Responsibility; DDD bounded contexts and aggregates |
| Hour 5 | System design: URL shortener, rate limiter, notification service, chat/news feed, distributed cache/job scheduler |
| Hour 6 | Order/payment flow (saga, outbox, inventory consistency, idempotency); STAR stories for incidents, performance, trade-offs and mentoring |

**System design answer framework:** Clarify requirements → estimate scale → high-level design → data model → deep dive on bottlenecks → trade-offs and failure handling.

**Advanced coding practice backlog:**
- Thread-safe singleton and custom thread pool.
- Token-bucket rate limiter.
- LRU/LFU cache.
- Deadlock demonstration and fix.
- Retry with exponential backoff.
- Producer-consumer with BlockingQueue.
- Custom transaction-like AOP aspect.
- Debug scenarios: high CPU, out-of-memory, slow query and thread starvation.

---

## Coverage mapping from the three-phase reference syllabus

The reference syllabus is incorporated as follows. This is a mapping, not a claim that every topic has already been taught.

| Reference area | Scheduled in our curriculum |
|---|---|
| Phase 1: Core Java | Days 1 and 6 |
| Phase 1: Collections | Days 1, 6 and 7 |
| Phase 1: Java 8 lambdas, Streams, Optional | Day 1; advanced collectors on Day 7 |
| Phase 1: Spring / Spring Boot | Days 2 and 10 |
| Phase 1: REST and microservices | Days 2 and 3; deeper architecture on Day 13 |
| Phase 1: coding practice | Days 6 and 10 |
| Phase 2: Java collections internals, concurrency, JVM, generics, serialization | Days 7–9 |
| Phase 2: Spring internals, validation, AOP, Actuator | Days 10 and 15 |
| Phase 2: JPA/Hibernate | Day 11 |
| Phase 2: Spring Security/JWT | Day 12 |
| Phase 2: microservices, resilience, tracing, Docker and tests | Days 13 and 17 |
| Phase 2: Kafka, SQL and caching | Day 14 |
| Phase 3: advanced Java/JVM | Days 8–9 |
| Phase 3: advanced Spring | Day 15 |
| Phase 3: distributed systems and API patterns | Day 16 |
| Phase 3: advanced Kafka and data infrastructure | Day 14 |
| Phase 3: DevOps, Kubernetes and observability | Day 17 |
| Phase 3: design patterns and system design | Day 18 |

## Priority guidance

1. **Must implement:** Core Java, Streams, Spring Boot REST, DI, JPA basics, validation, exception handling, testing, WebSocket integration, Docker Compose and the two-service project flow.
2. **Must explain confidently:** Java equality/collections, concurrency fundamentals, Spring lifecycle, transactions, security basics, Kafka fundamentals, resilience, SQL/indexing and observability.
3. **Advanced awareness first:** JVM tuning, reactive internals, custom Spring starters, service mesh, saga/outbox/CQRS, Kubernetes operations and advanced distributed locking. Deepen these through later focused projects.

Do not compress all 18 days into the original 30-hour sprint. Finish the working vertical slice first, then follow this roadmap based on interview priorities and available time.


## Tomcat and Java web runtime — interview reference

For Spring MVC applications using `spring-boot-starter-web`, Spring Boot normally selects embedded Tomcat. The executable JAR contains the application and required embedded-server dependencies; running `java -jar` starts the JVM, Spring Boot and Tomcat together. `server.port` configures the listening port (default 8080 unless changed).

Know the distinction between:
- Embedded Tomcat in an executable JAR vs an externally managed Tomcat receiving a WAR.
- Tomcat/Jetty/Undertow as Servlet-container choices vs Reactor Netty as a reactive networking runtime.
- Application server/container vs reverse proxy/load balancer such as NGINX or Apache HTTP Server.

A deployment answer should state the actual packaging, runtime/server and port for the system being discussed. Do not claim a specific server was used in a previous employer's deployment unless you know it.
