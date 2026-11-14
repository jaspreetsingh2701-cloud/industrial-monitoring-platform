# Setup Guide — Java Development in VS Code (Windows)

This guide assumes you are new to Java and have not configured a Java development environment before. Follow the steps in order. We will use **JDK 21**, **PowerShell**, **VS Code**, and Maven.

> Important for this computer: the earlier terminal output showed Java 8 first in the command search order. In particular, Windows was finding the Oracle Java 8 launcher at `C:\Program Files (x86)\Common Files\Oracle\Java\java8path\java.exe`. We need Java 21 to appear before that old entry. Do not uninstall Java 8; just configure Java 21 as the default.

## 1. Install JDK 21

1. Open the official Eclipse Temurin download page: https://adoptium.net/temurin/releases/?version=21
2. Select **Version 21 (LTS)**, **Windows**, **x64**, **JDK** (not JRE), and the **.msi** installer.
3. Download and run the installer.
4. Follow the wizard and accept the licence.
5. If installer options are shown, enable **Set JAVA_HOME** and **Add to PATH**. Wording may vary.
6. Complete the installation.

A JDK (Java Development Kit) contains the Java runtime plus developer tools such as `javac`, the Java compiler. A JRE alone is not sufficient for our development work.

## 2. Find the exact JDK installation folder

1. Press `Win + E` to open File Explorer.
2. Open **This PC → Local Disk (C:) → Program Files → Eclipse Adoptium**.
3. Look for a folder beginning with `jdk-21` or `jdk-21.0...`.
4. Open it. You should see folders such as `bin`, `conf`, `include`, `jmods`, and `lib`.
5. Copy the full folder path from File Explorer's address bar.

Example only — your actual version suffix may differ:

```text
C:\Program Files\Eclipse Adoptium\jdk-21.0.x.x-hotspot
```

The folder that directly contains `bin` is the JDK home folder. This is the value for `JAVA_HOME`. Do **not** add `\bin` to the JAVA_HOME value.

If the folder is not in that location, search Windows for `jdk-21` or check the installation destination shown by the installer. Confirm the selected folder contains both `bin\java.exe` and `bin\javac.exe`.

## 3. Understand JAVA_HOME and PATH

- `JAVA_HOME` is a variable that stores the location of the JDK installation folder, for example `C:\Program Files\Eclipse Adoptium\jdk-21...`.
- `Path` is a list of folders Windows searches when you type a command such as `java` or `javac`.
- Adding `%JAVA_HOME%\bin` to Path tells Windows to look inside the JDK's `bin` folder for Java commands.

In simple terms: JAVA_HOME points to the JDK root folder; Path points to its `bin` folder.

## 4. Set JAVA_HOME using Windows settings

1. Press the **Windows key** and type **environment variables**.
2. Select **Edit the system environment variables**.
3. In System Properties, open the **Advanced** tab.
4. Click **Environment Variables...**.
5. Under **User variables for your account**, click **New...**.
6. Enter:
   - Variable name: `JAVA_HOME`
   - Variable value: paste the exact JDK 21 folder path copied in Step 2. Example: `C:\Program Files\Eclipse Adoptium\jdk-21.0.x.x-hotspot`
7. Click **OK**.

If `JAVA_HOME` already exists under User variables or System variables, select it and click **Edit...** instead of creating a duplicate. Set it to the JDK 21 root folder.

A User variable is sufficient for your Windows account; you do not need to change settings for every account.

## 5. Add Java 21 to PATH and put it first

1. In the **Environment Variables** window, under **User variables for your account**, select **Path** and click **Edit...**.
2. Click **New** and enter:

```text
%JAVA_HOME%\bin
```

3. Select the new entry and use **Move Up** until it is near the top.
4. Look for older Java entries, especially:

```text
C:\Program Files (x86)\Common Files\Oracle\Java\java8path
C:\Program Files\Java\jdk1.8.0_341\bin
```

These paths were present in your earlier terminal output. Move the Java 21 entry above them. If the Oracle `java8path` entry is in **System variables → Path**, check that list too and move Java 21 above the old Java entry there. Do not delete unrelated Path entries.

5. Click **OK** on the Path editor, **OK** on Environment Variables, and **OK** on System Properties.

Windows searches Path entries in order. An older launcher appearing first can cause `java` to run Java 8 even after JDK 21 is installed. That is why order matters. You do not need to uninstall Java 8.

## 6. Close and reopen PowerShell, then verify

Environment variable changes do not update terminals that were already open.

1. Close all open PowerShell windows and VS Code terminals.
2. Open a **new** PowerShell window.
3. Run these commands one at a time:

```powershell
$env:JAVA_HOME
where.exe java
where.exe javac
java -version
javac -version
```

Expected results:

- `$env:JAVA_HOME` prints the JDK 21 root folder, without `\bin`.
- The first result from `where.exe java` is the JDK 21 `bin\java.exe`.
- The first result from `where.exe javac` is the JDK 21 `bin\javac.exe`.
- Both version commands report **21**.

It is okay if `where.exe` lists Java 8 after the Java 21 path. The first match is normally the command Windows runs.

### If Java 8 still appears first

1. Run `where.exe java` and note the first path.
2. Reopen **Edit the system environment variables → Environment Variables**.
3. Check both **User variables → Path** and **System variables → Path**.
4. Move `%JAVA_HOME%\bin` above all old Java entries, especially Oracle's `java8path`.
5. Close all terminals, open a new PowerShell window, and repeat the verification commands.

### If JAVA_HOME prints nothing

Go back to Step 4. Confirm the variable name is exactly `JAVA_HOME`, that it was saved, and that you opened a new PowerShell window.

### If javac is not recognised

You may have installed a JRE instead of a JDK, or the JDK `bin` folder is missing from Path. Confirm that the JDK folder contains both `bin\java.exe` and `bin\javac.exe`, then re-check Step 5.

## 7. Install Visual Studio Code and Java extensions

1. Download VS Code: https://code.visualstudio.com/
2. Install and open it.
3. Press `Ctrl + Shift + X` to open Extensions.
4. Install:
   - **Extension Pack for Java** (Microsoft) — language support, debugging, testing, and project management.
   - **Maven for Java** (Microsoft) — Maven project support.
   - **Spring Boot Extension Pack** (VMware) — Spring Boot tools.
   - **Extension Pack for YAML** (Red Hat) — YAML editing support.
   - **Docker** (Microsoft) — useful when we reach Docker.

Restart VS Code if it asks you to.

## 8. Install and verify Git

1. Download Git for Windows: https://git-scm.com/download/win
2. Run the installer using the default options.
3. Open a new PowerShell window and run:

```powershell
git --version
```

Configure your identity if you have not already (replace these examples with your own details):

```powershell
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

Use the email associated with your Git hosting account if you want commits attributed to it.

## 9. Verify Maven

Maven builds Java code, runs tests, and manages dependencies. Your earlier terminal output showed Maven 3.6.3 is already installed, so do not reinstall it just for this step.

After Java 21 is active, run:

```powershell
mvn -version
```

The output must show **Java version: 21**. Maven commonly uses `JAVA_HOME` to select the Java runtime. If Maven still reports Java 8, re-check Steps 4–6.

When a generated project includes a Maven Wrapper, prefer it because it uses the Maven version configured by the project. On Windows, run these commands from the project folder:

```powershell
.\mvnw.cmd -version
.\mvnw.cmd test
```

Maven download page, if we need a global installation later: https://maven.apache.org/download.cgi

## 10. Open the existing repository in VS Code

Open PowerShell and run:

```powershell
cd C:\third\industrial-monitoring-platform
code .
```

If `code` is not recognised, open VS Code manually and choose **File → Open Folder...**, then select `C:\third\industrial-monitoring-platform`.

In VS Code, open **Terminal → New Terminal** and run:

```powershell
java -version
javac -version
mvn -version
git --version
```

The integrated terminal should also use Java 21. If it still shows Java 8, fully close VS Code and reopen it after changing environment variables.

## 11. Create and run a small Java program

This is a simple check before we create Spring Boot services.

In PowerShell:

```powershell
cd C:\third\industrial-monitoring-platform
New-Item -ItemType Directory -Force src\day-1
notepad src\day-1\HelloJava.java
```

Paste this into Notepad and save:

```java
public class HelloJava {
    public static void main(String[] args) {
        String message = "Java 21 is ready!";
        System.out.println(message);
    }
}
```

Back in PowerShell, compile and run:

```powershell
javac src\day-1\HelloJava.java
java -cp src\day-1 HelloJava
```

Expected output:

```text
Java 21 is ready!
```

This confirms that both the JDK compiler (`javac`) and Java runtime (`java`) work.

## 12. Spring Boot project setup (when we reach the backend step)

Use Spring Initializr: https://start.spring.io/

Initial choices:

- Project: Maven
- Language: Java
- Spring Boot: a stable release offered by Initializr that supports Java 21
- Group: `com.monitoring`
- Artifact: `device-service`
- Packaging: Jar
- Java: 21

Add dependencies when needed: Spring Web, Spring Data JPA, Validation, PostgreSQL Driver, and Spring Boot Actuator. We will do this together when the course reaches the backend project.

## 13. Optional tools — install only when needed

- **Docker Desktop:** install when we reach Docker. It may require WSL 2 on Windows.
- **PostgreSQL:** we can run it through Docker later or install it locally.
- **API client:** use `curl`, the VS Code REST Client extension, or Postman.
- **Node.js LTS:** needed for the React dashboard, not for Java itself. Install from https://nodejs.org/.

Do not install every optional tool before Java 21 is working.

## 14. Final verification checklist

Run these commands in a new PowerShell window and keep the complete output:

```powershell
$env:JAVA_HOME
where.exe java
where.exe javac
java -version
javac -version
mvn -version
git --version
```

Checklist:

- [ ] JDK 21 (not just JRE) installed.
- [ ] Located the JDK folder that contains `bin`.
- [ ] `JAVA_HOME` points to the JDK root folder, not the `bin` folder.
- [ ] `%JAVA_HOME%\bin` is near the top of Path.
- [ ] Java 21 is the first Java executable found by `where.exe java` and `where.exe javac`.
- [ ] `java -version` reports version 21.
- [ ] `javac -version` reports version 21.
- [ ] `mvn -version` reports Java version 21.
- [ ] VS Code and Extension Pack for Java are installed.
- [ ] Git is installed.
- [ ] HelloJava compiles and runs successfully.

**Next step:** Do only Steps 1–6 first. Share the output of `$env:JAVA_HOME`, `where.exe java`, `java -version`, `javac -version`, and `mvn -version`. We will confirm Java 21 is correctly selected before moving on to coding.
