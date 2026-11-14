# Java & Spring Boot — Quick Reference Glossary

A growing, beginner-friendly dictionary of the technical terms used in the Industrial Monitoring Platform learning sprint. Each definition is intentionally short; follow the linked daily guides for deeper explanations and examples.

## Java foundations

| Term | One-line meaning |
|---|---|
| JDK (Java Development Kit) | The toolkit for developing Java applications; includes the compiler and Java runtime tools. |
| JVM (Java Virtual Machine) | The runtime engine that executes Java bytecode. |
| JRE (Java Runtime Environment) | The components needed to run Java applications; modern JDK distributions provide the runtime. |
| `javac` | The Java compiler that converts `.java` source files into bytecode (`.class` files). |
| Bytecode | Platform-independent instructions executed by the JVM. |
| Class | A blueprint that defines the data and behaviour of objects. |
| Object | An instance of a class. |
| Constructor | A special class member used to initialise a new object. |
| Interface | A contract that specifies methods implementing classes must provide. |
| Dependency | Another class or library that a piece of code needs to do its work. |
| Immutable object | An object whose state cannot change after it is created. |
| Collection | An object used to store and work with groups of values. |
| Stream API | A Java API for processing data from collections through operations such as filter, map and reduce. |
| Optional | A container that may hold a value or be empty, helping represent an absent result. |

## Build tools and dependencies

| Term | One-line meaning |
|---|---|
| Maven | A Java build and dependency management tool. |
| `pom.xml` | Maven's project configuration file, declaring dependencies, plugins and build settings. |
| Dependency | A library or module that an application needs. |
| Transitive dependency | A library required by another library that your project uses. |
| Maven repository | A location where Maven retrieves and stores build artifacts such as JAR files. |
| Local Maven repository | The local cache of downloaded Maven artifacts, usually `~/.m2/repository`. |
| JAR (Java Archive) | A packaged file containing compiled Java classes and resources. |
| Maven Central | A widely used public repository for Java and JVM libraries. |
| Maven plugin | An extension that adds tasks to Maven's build lifecycle, such as compilation, testing or packaging. |
| Build lifecycle | Maven's standard sequence of phases for validating, compiling, testing and packaging a project. |
| Apache Ant | A Java build automation tool configured with an XML `build.xml` file. |
| Apache Ivy | A dependency management tool often used alongside Ant. |
| Classpath | The locations Java uses to find compiled classes and JAR dependencies. |

## Spring and Spring Boot

| Term | One-line meaning |
|---|---|
| Spring Framework | A Java framework that provides application infrastructure, including dependency injection and web capabilities. |
| Spring Boot | A layer on top of Spring that simplifies application setup through auto-configuration, starter dependencies and convenient defaults. |
| Spring Container | The Spring runtime that creates, configures and manages application Beans. |
| IoC (Inversion of Control) | A design principle where the framework manages object creation and wiring instead of application code controlling everything itself. |
| DI (Dependency Injection) | Supplying an object with the dependencies it needs rather than making it create them itself. |
| Bean | A Java object whose creation and lifecycle are managed by the Spring container. |
| ApplicationContext | Spring's container interface that holds Beans and provides application-level services. |
| Bean scope | A rule defining how many instances Spring creates and how long they live. |
| Singleton scope | Spring's default scope: one Bean instance per Bean definition per ApplicationContext. |
| Constructor injection | Providing a class's required dependencies through its constructor. |
| Component scanning | Spring's process of discovering eligible annotated classes in configured packages. |
| Auto-configuration | Spring Boot's conditional setup of application infrastructure based on dependencies and configuration. |
| Configuration | Settings that control how an application and its components behave, such as server port, database connection and logging level. |
| `application.properties` | A Spring Boot configuration file commonly used to define application settings as key-value pairs. |
| `application.yml` | A YAML-format alternative for defining Spring Boot application settings in a structured way. |
| Starter dependency | A convenient dependency bundle that brings together commonly needed libraries for a capability. |
| `@SpringBootApplication` | The annotation combining Spring Boot configuration, auto-configuration and component scanning. |
| `@SpringBootConfiguration` | Marks a class as a source of Spring Boot configuration. |
| `@EnableAutoConfiguration` | Enables Spring Boot's conditional auto-configuration mechanism. |
| `@ComponentScan` | Tells Spring where to scan for components and Beans. |
| `@Component` | Marks a class as a Spring-managed component candidate. |
| `@Service` | Marks a class as a service-layer component. |
| `@Repository` | Marks a persistence-layer component and enables persistence exception translation. |
| `@Controller` | Marks a Spring MVC controller. |
| `@RestController` | Marks a controller whose handler methods write response bodies, commonly JSON. |
| `@Bean` | Marks a method whose returned object should be registered as a Spring Bean. |
| Embedded Tomcat | A web server that runs inside the Spring Boot application process. |
| Servlet | A Java component that handles HTTP requests and produces HTTP responses. |
| Servlet container | A runtime such as Tomcat that manages Servlet lifecycle and routes HTTP requests to Servlets. |
| WAR (Web Application Archive) | A packaged Java web application that can be deployed to a compatible external Servlet container. |
| `web.xml` | The traditional Java web application deployment descriptor used to configure Servlet mappings and other web settings. |
| `@WebServlet` | A Servlet annotation that can declare a Servlet and its URL mapping without a `web.xml` entry. |
| Jetty | An alternative Java web server and Servlet container that can host Java web applications. |
| Undertow | An alternative lightweight Java web server that can be used with Spring Boot. |
| Java `HttpServer` | A basic HTTP server API in the JDK for lightweight HTTP handling without a full Servlet container. |
| DispatcherServlet | Spring MVC's front controller that dispatches incoming HTTP requests to handler methods. |

## Web and REST APIs

| Term | One-line meaning |
|---|---|
| HTTP | The protocol clients and servers use to exchange web requests and responses. |
| REST | An architectural style for designing APIs around resources and standard HTTP semantics. |
| Endpoint | A URL and HTTP method combination through which an API operation is accessed. |
| Controller | The application layer that receives HTTP requests and returns HTTP responses. |
| Service layer | The layer that coordinates application operations and business rules. |
| Repository | The abstraction responsible for accessing and persisting application data. |
| DTO (Data Transfer Object) | An object designed to carry data across an application or API boundary. |
| Entity | An object mapped to a database table when using an ORM such as JPA. |
| JSON | A text-based format commonly used to exchange structured data between APIs and clients. |
| HTTP status code | A numeric response code indicating the outcome of an HTTP request. |
| Validation | Checking incoming data against rules before processing it. |
| Exception handling | Handling errors so an application can respond predictably. |

## Concurrency and safe application design

| Term | One-line meaning |
|---|---|
| Thread | A path of execution within a running process. |
| Concurrency | Multiple tasks making progress during overlapping periods. |
| Thread safety | The property of code behaving correctly when accessed by multiple threads concurrently. |
| Stateless Bean | A Bean that does not keep request-specific mutable state in shared instance fields. |
| Instance variable | A field belonging to an object instance and shared by threads that access that same object. |
| Local variable | A variable declared inside a method invocation, separate for each invocation. |
| Race condition | A bug where the result depends on the timing or interleaving of concurrent operations. |

## Add to this glossary

When a new term appears in a lesson, add a concise definition here and link to a deeper explanation when appropriate. Keep definitions short; use the daily guides for full examples, implementation steps and interview notes.

## Related learning documents

- [Five-day learning plan](00-five-day-plan.md)
- [Day 1 — Java fundamentals](day-1.md)
- [Day 2 — Spring Boot and REST APIs](day-2.md)
- [Java vs JavaScript comparison](03-java-vs-javascript.md)
- [Extended backend roadmap](05-extended-backend-roadmap.md)
