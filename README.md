# Industrial Monitoring Platform — Learning Sprint

This repository is both a hands-on full-stack project and a structured five-day learning sprint.

## Goal
Build a working industrial monitoring prototype while learning Java, Spring Boot, microservices, service communication, real-time data delivery, and frontend integration.

**Time budget:** 5 days × 6 hours/day = 30 focused hours.

**Important:** The five-day target is a working prototype and interview-ready fundamentals. It is not intended to represent production-grade mastery of Java or distributed systems.

## Documentation map

| File | Purpose |
|---|---|
| `docs/00-five-day-plan.md` | Day-by-day schedule with six-hour time blocks |
| `docs/01-project-overview.md` | What we are building, features, scope and outcomes |
| `docs/02-architecture.md` | Services, responsibilities, communication and data flow |
| `docs/day-1.md` … `docs/day-5.md` | Daily topics, learning labels and deliverables |
| `docs/day-1-best-practices.md` | Short problem/solution/best-practice notes from Day 1 |
| `docs/03-java-vs-javascript.md` | Java concepts mapped to JavaScript equivalents |
| `docs/04-vscode-java-setup.md` | Windows + VS Code + Java setup |
| `docs/05-extended-backend-roadmap.md` | Days 6–18: expanded Java, Spring, microservices and interview curriculum |
| `PROGRESS.md` | Checklist and learning log |

## Java compilation: JDK vs Maven

**Can Java compile with only the JDK? Yes.** The JDK includes `javac`, the Java compiler, and the `java` launcher used to run compiled classes. For a small program, you can compile and run Java directly without Maven.

**So why does this project have a `pom.xml`?** Maven is a build and dependency management tool. The `pom.xml` (Project Object Model) tells Maven how to build the project: project identity, Java version, dependencies such as JUnit, plugins such as the compiler and test runner, source folders, and build tasks. Maven uses the JDK underneath; it does not replace the JDK or the Java compiler.

| Tool | Responsibility |
|---|---|
| JDK | Provides Java development tools, including `javac` and `java` |
| `javac` | Compiles `.java` source files into `.class` bytecode |
| `java` | Runs compiled Java classes on the JVM |
| Maven | Manages dependencies and runs repeatable build, test and packaging steps using `pom.xml` |
| `pom.xml` | Project/build configuration read by Maven |

**When to use which:** direct `javac` commands are useful for learning and tiny standalone examples. Maven is more practical as the project grows, especially when it has multiple source files, external libraries, automated tests, plugins, and repeatable builds across developer machines and CI.

## Maven and npm: the familiar comparison

If you come from a React/Node.js background, think of Maven and npm as tools that solve similar project-management problems.

**The quick mental model:** `pom.xml` is broadly comparable to `package.json`, and Maven is broadly comparable to npm. Both ecosystems use a project file to describe dependencies and support repeatable project workflows. They are not exact one-to-one equivalents.

| Java + Maven | JavaScript + Node.js | What it means |
|---|---|---|
| `pom.xml` | `package.json` | Project metadata, dependencies and build configuration |
| Maven | npm | Dependency and project-build tooling |
| Maven dependencies | `dependencies` in `package.json` | Libraries the application needs |
| Maven plugins | Build/test tools configured through Maven | Extend Maven's build lifecycle with compilation, testing, formatting, packaging, etc. |
| `mvn test` | `npm test` (if a test script is defined) | Run project tests |
| `mvn package` | A build script such as `npm run build` | Produce a distributable build artifact |
| `~/.m2/repository` | npm's cache; project packages are commonly in `node_modules` | Where downloaded dependencies are stored |

### A dependency example

In a React project, you might add React to `package.json`:

```json
{
  "dependencies": {
    "react": "^19.0.0"
  }
}
```

In a Java project, you declare a library in `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

Then `npm install` downloads Node.js packages, while Maven resolves and downloads Java dependencies when you run a Maven build (for example, `mvn compile`).

### What if the project file is deleted?

- Java still works: the JDK can compile and run Java code directly.
- Existing JAR files do not become unusable. You can still reference them manually with the Java classpath.
- But Maven loses the project's configuration: its declared dependencies, plugins, source/build settings and lifecycle configuration are no longer available from that POM.
- Similarly, deleting `package.json` does not break JavaScript itself, but it removes the project's npm manifest, scripts and declared dependency information.

**Remember:** Java is the language/runtime ecosystem; Maven is a build and dependency tool. Just as React does not require npm to exist as a language feature, Java does not require Maven to compile and run a small program. For a real multi-library application, a build tool makes dependency and build management much easier.

## Java commands — Windows PowerShell

Run these from the repository root unless a command says otherwise.

### 1. Check the Java and Maven installations

```powershell
java -version
javac -version
mvn -version
```

### 2. Compile and run the introductory Java program directly

```powershell
javac src/day-1/HelloJava.java
java -cp src/day-1 HelloJava
```

This creates `HelloJava.class` beside the source file. The classpath (`-cp`) tells the Java launcher where to find the compiled class.

### 3. Compile and run the Day 1 domain model directly

```powershell
javac -d out src/day-1/domain-model/Device.java src/day-1/domain-model/DeviceService.java src/day-1/domain-model/Main.java src/day-1/domain-model/SensorReading.java
java -cp out Main
```

Here, `-d out` places compiled `.class` files in the `out` directory instead of beside the source files. The four source files are compiled together because they reference one another.

### 4. Use Maven for the project build

```powershell
mvn compile
mvn test
mvn clean test
mvn package
```

- `mvn compile`: compiles the configured main source files.
- `mvn test`: compiles and runs the tests.
- `mvn clean test`: removes the previous build output, then compiles and runs tests from a clean state.
- `mvn package`: runs the build lifecycle through packaging and creates the configured artifact under `target/`.

### 5. Format and verify Java code

This project uses Spotless with Google Java Format for Java source files.

```powershell
mvn spotless:apply
mvn spotless:check
```

- `mvn spotless:apply`: formats Java files.
- `mvn spotless:check`: checks formatting without modifying files.

These commands format Java source, not `pom.xml`. Format XML separately using the VS Code XML formatter (for example, Shift + Alt + F with the Red Hat XML extension configured).

### Recommended pre-commit verification

```powershell
mvn spotless:apply
mvn spotless:check
mvn clean test
```

Commit only after formatting and tests succeed.

## Suggested repository structure

```text
industrial-monitoring-platform/
├── apps/
│   └── dashboard/                 # React + TypeScript + Vite
├── services/
│   ├── device-service/            # Java + Spring Boot
│   └── telemetry-service/         # Java + Spring Boot
├── contracts/
│   └── telemetry.proto            # gRPC contract
├── infrastructure/
│   └── docker-compose.yml
├── docs/
│   └── ...                        # These learning/project documents
├── PROGRESS.md
└── README.md
```

## Working agreement

- Follow one day at a time; do not jump ahead just to create more code.
- At the start of each day, review its guide and prerequisites.
- Implement a small working increment, run it, and understand it before moving on.
- Record commands, decisions, errors and fixes in the relevant docs or `PROGRESS.md`.
- Keep secrets, local environment files and build outputs out of Git.

## From Java code to a running backend — the simple picture

Imagine you want to build a house. You need tools, a plan, materials and people who know how to put everything together. A Java backend works in a similar way.

### The pieces, in plain English

1. **JDK — Your toolbox.** It gives you the Java compiler (`javac`) and the Java launcher (`java`). You can write, compile and run a small Java program with just the JDK.
2. **Maven — Your project organiser.** As the project grows, you need libraries, tests and repeatable build steps. Maven downloads the libraries and runs those steps for you.
3. **pom.xml — The instruction sheet for Maven.** It tells Maven which Java version, libraries and build plugins this project needs. For example, we will list Spring Boot and testing libraries here.
4. **Spring Boot — The backend-building toolkit.** It gives us ready-made building blocks for web APIs, dependency injection and database access, so we don't have to build all that plumbing ourselves.
5. **Tomcat — The front door for web requests.** In our Spring MVC application, embedded Tomcat listens for HTTP requests and passes them into Spring. We don't need to install Tomcat separately for the usual executable-JAR setup.
6. **Our application — The business logic.** This is the code we write: register a device, find a device, store sensor readings and return API responses.

### How they work together

```text
We write Java code
       |
       v
JDK provides javac and java
       |
       v
Maven reads pom.xml
       |
       +--> downloads required libraries
       +--> compiles our Java code using the JDK
       +--> runs tests and packages the application
       |
       v
Spring Boot application starts on the JVM
       |
       v
Embedded Tomcat receives HTTP requests
       |
       v
Spring routes requests to our controller
       |
       v
Our service logic does the actual work
```

### So, why can't we just use the JDK for everything?

We can use the JDK to compile and run Java code. But the JDK alone does not manage all the extra libraries, tests, plugins and repeatable project build steps that a real application needs. Maven handles that project work.

And why Spring Boot? Maven can build a project, but it doesn't provide the backend features we want. Spring Boot gives us the framework to create those features.

**One-line memory trick:**

- **JDK:** gives us the tools to work with Java.
- **Maven:** manages how the project is built.
- **pom.xml:** tells Maven what this project needs.
- **Spring Boot:** helps us build the backend.
- **Tomcat:** receives web requests.
- **Our code:** solves the actual business problem.

For our Industrial Monitoring Platform, we'll use Maven to bring in Spring Boot, Spring Boot to build the Device REST API, embedded Tomcat to receive HTTP requests, and our Java code to manage devices and sensor data.
