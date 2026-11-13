# Setup Guide — Java Development in VS Code (Windows)

We will use VS Code as the editor and PowerShell as the terminal.

## 1. Install the JDK

Install **JDK 21 (LTS)** from a trusted distribution such as:
- Eclipse Temurin: https://adoptium.net/
- Oracle JDK: https://www.oracle.com/java/technologies/downloads/

Choose the Windows x64 installer that matches your machine. During installation, enable options to set `JAVA_HOME` and update `PATH` if offered.

Open a new PowerShell window and verify:

```powershell
java -version
javac -version
```

Both commands should report version 21.

If PowerShell says the command is not recognised:
1. Confirm the JDK installation directory.
2. Set `JAVA_HOME` to the JDK directory, for example `C:\Program Files\Eclipse Adoptium\jdk-21...`.
3. Add `%JAVA_HOME%\bin` to the system/user `Path`.
4. Open a new terminal and retry.

Do not set `JAVA_HOME` to the `bin` directory.

## 2. Install VS Code

Download and install VS Code:
https://code.visualstudio.com/

Open VS Code and install the following extensions:

- **Extension Pack for Java** (Microsoft) — Java language support, debugging, testing and project management.
- **Maven for Java** (Microsoft) — Maven project support.
- **Spring Boot Extension Pack** (VMware) — Spring Boot tools and dashboard.
- **Extension Pack for YAML** (Red Hat) — YAML editing support, useful for configuration and Docker Compose.
- **Docker** (Microsoft) — container workflow when we reach Docker.

Install extensions from the Extensions view (`Ctrl+Shift+X`).

## 3. Install Maven

Spring Initializr projects include a Maven Wrapper (`mvnw` / `mvnw.cmd`). Prefer the wrapper for project commands because it uses the Maven version configured by the project.

For a global Maven installation, download from:
https://maven.apache.org/download.cgi

If installing globally, add Maven's `bin` directory to `Path`, then verify:

```powershell
mvn -version
```

The Maven output should show Maven and the Java 21 runtime.

## 4. Install Git

Install Git for Windows:
https://git-scm.com/download/win

Verify:

```powershell
git --version
```

Configure your identity if you have not already:

```powershell
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

Use the email associated with your Git hosting account if you want commits attributed to it.

## 5. Optional but useful tools

- **PostgreSQL:** We can run it through Docker later, or install locally.
- **API client:** Use curl, the VS Code REST Client extension, or Postman.
- **Docker Desktop:** Install when we reach Day 5; it may require WSL 2 on Windows.
- **Node.js LTS:** Needed for the React dashboard, not for Java itself. Install from https://nodejs.org/.

Do not install every optional tool before verifying the Java toolchain.

## 6. Create and open the workspace

Create a project folder:

```powershell
mkdir industrial-monitoring-platform
cd industrial-monitoring-platform
code .
```

If `code` is not recognised, open VS Code manually and use **File → Open Folder**.

## 7. Create a Java project using Spring Initializr

For Day 1, we can start with a simple Maven Java project. For Day 2, generate the Spring Boot service using:
https://start.spring.io/

Initial choices:
- Project: Maven
- Language: Java
- Spring Boot: current stable release compatible with Java 21
- Group: `com.monitoring`
- Artifact: `device-service`
- Packaging: Jar
- Java: 21

Add dependencies when needed:
- Spring Web
- Spring Data JPA
- Validation
- PostgreSQL Driver
- Spring Boot Actuator (when we cover operational visibility)

Download the ZIP, extract it into `services/device-service`, and open the repository root in VS Code.

Use the generated Maven Wrapper on Windows:

```powershell
.\mvnw.cmd -version
.\mvnw.cmd test
```

## 8. Verify the environment

Run these commands in PowerShell and keep the output:

```powershell
java -version
javac -version
mvn -version
git --version
```

If Maven Wrapper is available inside a generated project:

```powershell
.\mvnw.cmd -version
```

## 9. VS Code workflow

- Open the project root folder, not just an individual `.java` file.
- Use the integrated terminal: **Terminal → New Terminal**.
- Use the Java Projects view to inspect Maven projects.
- Use Run/Debug above the `main` method or the Run and Debug panel.
- Use the Testing view to run discovered tests.
- Format code and resolve imports with the Java extension.

## 10. Common setup issues

| Symptom | Likely cause / check |
|---|---|
| `java` not recognised | JDK `bin` missing from `Path`; reopen terminal |
| `javac` not recognised | JRE-only install or JDK `bin` missing |
| VS Code cannot find Java | Check Extension Pack for Java and configured JDK |
| Maven uses wrong Java | Check `mvn -version` and `JAVA_HOME` |
| `mvn` not recognised | Use project Maven Wrapper or install Maven and update `Path` |
| Project imports stay unresolved | Open repository root; allow Maven import to finish |
| Port already in use | Stop the other process or configure a different port |

## Setup completion checklist

- [ ] JDK 21 installed.
- [ ] `java -version` reports Java 21.
- [ ] `javac -version` reports Java 21.
- [ ] VS Code installed.
- [ ] Extension Pack for Java installed.
- [ ] Maven available globally or Maven Wrapper ready.
- [ ] Git installed.
- [ ] Project root opens in VS Code.
- [ ] A small Java program can run successfully.

**Next step:** Share the command output and we will verify the environment together before starting Day 1 coding.
