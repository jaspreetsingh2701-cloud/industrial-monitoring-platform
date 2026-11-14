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
| `docs/06-java-spring-glossary.md` | One-line definitions of Java, Maven, Spring Boot, REST and backend terms; updated throughout the sprint |
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

## Before Maven: how Java developers managed libraries and builds

**BASIC | INTERVIEW | REAL USE CASE**

Maven did not invent Java libraries or JAR files. Java developers used third-party libraries long before Maven existed. The difference was that developers often had to manage those libraries and the build process themselves.

### 1. The manual approach: download JARs and configure the classpath

A JAR (Java Archive) is a packaged file containing compiled Java classes and resources. Think of it as a library package that your application can use.

Before automated dependency management became common, a developer might:

1. Find a library online and download its `.jar` file.
2. Copy it into the project's `lib/` folder.
3. Add that JAR to the IDE's build path or Java classpath.
4. Repeat the process for every library the application needs.
5. Share the JAR files or setup instructions with teammates.
6. Repeat the process when a library is upgraded.

A project might look like this:

```text
my-java-app/
├── src/
│   └── Main.java
├── lib/
│   ├── gson-2.x.jar
│   └── logging-library.jar
└── README.md
```

You could compile and run code with a manually configured classpath. For example, from the project root on Windows PowerShell:

```powershell
javac -cp "lib/*" -d out src/Main.java
java -cp "out;lib/*" Main
```

The `-cp` option tells Java where to find classes. The wildcard `lib/*` includes JAR files in that folder. This is still possible today; it is simply more work to maintain as a project grows.

### 2. What made the manual approach difficult?

Imagine your application uses Library A, and Library A itself needs Library B and Library C.

You may have to find and download all three JARs, check compatible versions, add each one to the classpath, and ensure your teammates do exactly the same thing.

Common problems included:

- **Dependency discovery:** finding every library your chosen library also needs.
- **Version conflicts:** two libraries may require different versions of another library.
- **Team consistency:** one developer may have a different JAR version from another.
- **Build repetition:** every developer or CI machine needs the right classpath and build steps.
- **Upgrades and cleanup:** replacing old JARs and identifying unused ones takes manual effort.

This is often described informally as JAR hell: dependency versions and combinations become difficult to manage. [Apache Maven describes how dependency relationships can lead to version conflicts.](https://maven.apache.org/pom.html)

### 3. Then came build tools such as Apache Ant

Apache Ant helped automate build tasks. Developers wrote a `build.xml` file describing tasks such as:

- Compile Java source files.
- Copy resource files.
- Run tests.
- Package compiled classes into a JAR or WAR.

However, **Ant by itself did not provide Maven-style dependency resolution**. Projects commonly kept JARs in a `lib/` directory and configured them in the build script or classpath. Tools such as Apache Ivy could be added to Ant projects for dependency management.

### 4. What Maven improved

Maven brought dependency management and a standardised build model together:

| Earlier manual / Ant-based workflow | Maven workflow |
|---|---|
| Download each JAR yourself | Declare dependencies in `pom.xml` |
| Track required supporting JARs yourself | Resolve transitive dependencies automatically |
| Maintain a project-specific classpath | Maven builds the appropriate classpath |
| Copy JARs into a shared project folder | Download and cache dependencies in the local Maven repository |
| Write many build steps and their order | Use Maven's standard build lifecycle and plugins |
| Developers configure builds differently | Share the POM so the project build is repeatable |

For example, when you declare a dependency in `pom.xml`, Maven can download it from a configured remote repository (commonly Maven Central), store it in your local repository (usually `~/.m2/repository`), and resolve the libraries it depends on. You normally do not need to download each supporting JAR yourself. [Maven's dependency guide explains dependency resolution and transitive dependencies.](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)

### 5. The evolution in one picture

```text
Manual JAR management
Download JARs -> put them in lib/ -> configure classpath
                 |
                 v
Ant build automation
Write build.xml -> automate compile, test and package
                 |
                 +--> Often still manage JAR dependencies manually
                 +--> Ivy could be added for dependency management
                 |
                 v
Maven
Declare dependencies in pom.xml
                 |
                 v
Maven resolves and downloads required JARs
                 |
                 v
Maven builds, tests and packages the application
```

**Important:** This is a simplified history, not a claim that every Java team followed the exact same sequence. Manual JAR management, IDE-managed libraries, Ant, Ivy and Maven have all been used in different projects and periods.

**One-line takeaway:** Before Maven, developers could still use libraries, but they often had to find, download, version, share and connect the JARs themselves. Maven automated much of that work and standardised how Java projects are built.

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

## Current implementation snapshot

**Day 2 — Device Service:** The Spring Boot service currently exposes device CRUD endpoints, validates request DTOs, centralises API error responses and has separate service/controller tests. The latest verified test run reported **10 passing tests** (6 service tests and 4 MockMvc controller tests).

- Implementation guide and verified endpoint/test details: [Day 2 guide](docs/day-2.md)
- Current learning status and next steps: [Progress tracker](PROGRESS.md)
- Short definitions for Spring, REST and testing terms: [Java & Spring glossary](docs/06-java-spring-glossary.md)

**Current limitation:** Device records are stored in memory. PostgreSQL and Spring Data JPA persistence, environment profiles and production logging are still pending; do not treat the service as database-persistent yet.

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


## Device Service shortcuts — PowerShell

**BASIC | REAL USE CASE**

Long Maven commands are useful to understand what is happening, but typing them repeatedly is inconvenient. We use a small PowerShell script as a shortcut, similar in purpose to an npm script such as `npm run dev`.

The script is stored at:

```text
scripts/
└── device.ps1
```

Run these commands from the repository root in Windows PowerShell:

| Shortcut | What it does |
|---|---|
| `.\scripts\device.ps1 compile` | Runs Maven `clean compile` for the Device Service |
| `.\scripts\device.ps1 run` | Starts the Device Service using the Spring Boot Maven Plugin |
| `.\scripts\device.ps1 test` | Runs Maven `clean test` for the Device Service |
| `.\scripts\device.ps1 package` | Runs Maven `clean package` for the Device Service |
| `.\scripts\device.ps1 clean` | Removes the Device Service build output |
| `.\scripts\device.ps1` | Starts the Device Service; `run` is the default task |

The script selects the shared services POM (`services/pom.xml`) and the `device-service` module, so you do not need to repeat those Maven arguments each time. It also resolves the repository root from the script's own location, then restores the terminal's original directory after the command finishes.

### What the script does under the hood

- `param(...)` accepts the requested task and restricts it to the supported task names.
- `$PSScriptRoot` identifies the folder containing the script; this lets the script locate the repository even if PowerShell was opened in another directory.
- `$MavenArgs` stores the common Maven project-selection arguments.
- `switch` chooses the Maven goal(s) for the requested task.
- `Push-Location` and `Pop-Location` temporarily change and then restore the working directory.
- `$LASTEXITCODE` checks whether Maven completed successfully; a non-zero exit code is reported as a failure.

If PowerShell blocks script execution, you can allow scripts for the current PowerShell process only (this does not change the machine-wide policy):

```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
```

**Important:** The PowerShell file is only a convenient wrapper. Maven still performs the compilation, tests and packaging, and the Spring Boot Maven Plugin still starts the Java application. We can add similar scripts for the Telemetry Service and frontend as those parts of the project are implemented.
