# Day 1 — Java Fundamentals

**Outcome:** Build a small Java console application that manages industrial devices.

| Block | Topics — one-line guide |
|---|---|
| Hour 1 | Java platform, JDK/JRE/JVM, syntax, variables, primitive/reference types, operators and control flow. |
| Hour 2 | OOP: classes, objects, constructors, encapsulation, inheritance, polymorphism, abstraction and interfaces. |
| Hour 3 | Collections: List, Set, Map, Queue, generics and JavaScript equivalents. |
| Hour 4 | Lambdas, functional interfaces, Streams, Optional and method references. |
| Hour 5 | Build Device, SensorReading, repository and service classes; practise filtering and aggregation. |
| Hour 6 | Exercises, exception handling, basic JUnit tests, debugging and interview revision. |

## Hour 1 — Java foundations

### Why is `int` lowercase but `String` uppercase?

Java is case-sensitive. `int` is a **primitive type** built into the language, whereas `String` is a **class** (a reference type) provided by the Java standard library.

```java
int age = 36;                 // primitive value
String name = "Jaspreet";    // object/reference type
```

Java convention:
- Primitive type names are lowercase: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`.
- Class and interface names normally use PascalCase: `String`, `Integer`, `Device`, `ArrayList`.
- Variable and method names normally use camelCase: `deviceName`, `getDevice()`.

The capitalization is not what makes a type primitive or a class; it is a naming convention that reflects Java's language design. `String` is a class, not a primitive type.

### Java setup status

Environment output supplied during Day 1:
- `java -version`: Java 8.0.511 runtime.
- `javac -version`: Java compiler 8.0.341.
- `mvn -version`: Maven 3.6.3, running on Java 8.0.341.
- `git --version`: Git 2.37.1.
- `code --version`: VS Code 1.146.0.

**Status:** Git and VS Code commands are available. Java and Maven are installed, but the active Java toolchain is Java 8—not the planned JDK 21. Install JDK 21 and configure `JAVA_HOME` and `Path` before proceeding with the project code. Check that `java`, `javac` and `mvn` all resolve to JDK 21.

## Deliverable

A console application that registers devices, finds a device by ID, filters active devices, changes status and calculates a simple reading average.

## Completion checklist
- [ ] JDK 21 and Maven use Java 21 in the terminal.
- [ ] Understand class, object, interface and constructor.
- [ ] Use ArrayList, HashSet and HashMap.
- [ ] Use Stream `filter`, `map`, `collect` and `average`.
- [ ] Handle missing values with Optional.
- [ ] Run the application and explain the code without simply reading it.

## Questions & Answers

Keep questions that come up during the course here with a clear answer and a small example. We will add to this section each day.

### Q1. Why is `int` lowercase but `String` uppercase?

**Answer:** `int` is one of Java's eight primitive types and is a reserved language keyword, written lowercase. `String` is a class supplied by Java's standard library, and class names conventionally start with an uppercase letter.

```java
int count = 5;
String deviceName = "Sensor A";
```

### Q2. Is `String` a primitive type?

**Answer:** No. `String` is a reference type (a class). It represents text and has useful methods such as `length()`, `toUpperCase()` and `contains()`. Java strings are immutable: operations that appear to change a string return a new string.

### Q3. Is Java case-sensitive?

**Answer:** Yes. `String` and `string` are different identifiers. The class is `String`; there is no built-in Java type named `string`.

### Q4. Why does Java use types such as `int` and `boolean`?

**Answer:** Java is statically typed. A variable has a declared type, and the compiler checks many type errors before the program runs. This helps make contracts and data handling explicit.

## Notes / questions from the learner

Add new questions here as they arise:
- 
