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
