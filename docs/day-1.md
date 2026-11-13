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
int age = 36;
String name = "Jaspreet";
```

Primitive type names are lowercase; class/interface names conventionally use PascalCase; variables and methods use camelCase. `String` is a class, not a primitive type.

### Java setup status

**Complete:** JDK 21 and Maven are configured and verified in Windows PowerShell 5.1.

- `JAVA_HOME`: `C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.13.9-hotspot`
- JDK 21 `java.exe` and `javac.exe` resolve first on PATH.
- `java -version`: OpenJDK 21.0.13 LTS (Eclipse Temurin).
- `javac -version`: `javac 21.0.13`.
- `mvn -version`: Apache Maven 3.6.3 running on Java 21.0.13.
- Windows 11, 64-bit, PowerShell 5.1.

### First Java program

Create `src/day-1/HelloJava.java`:

```java
public class HelloJava {
    public static void main(String[] args) {
        String message = "Welcome to Java!";
        System.out.println(message);
    }
}
```

Compile and run from repository root:

```powershell
javac src\\day-1\\HelloJava.java
java -cp src\\day-1 HelloJava
```

Expected output: `Welcome to Java!`

Compilation creates `HelloJava.class` bytecode. The `java` launcher starts the class on the JVM. `-cp` sets the classpath (where Java looks for classes).

#### Understanding main

- `public`: accessible to the launcher.
- `static`: can be invoked without creating an instance.
- `void`: returns no value.
- `main`: conventional entry-point method name.
- `String[] args`: array of command-line arguments.

#### Q1. Why is int lowercase but String uppercase?

**Answer:** `int` is a primitive keyword; `String` is a standard-library class. Their capitalization follows Java naming conventions.

#### Q2. Is String primitive?

**Answer:** No. It is a reference type (class). Strings are immutable and provide methods such as `length()`, `toUpperCase()` and `contains()`.

#### Q3. Is Java case-sensitive?

**Answer:** Yes. `String` and `string` are different identifiers. Java's text class is `String`.

#### Q4. Why use types such as int and boolean?

**Answer:** Java is statically typed. A variable has a type, and the compiler checks many type errors before execution.

#### Q5. What is the purpose of main?

**Answer:** For a traditional Java application launched with the Java launcher, `main` is the entry point where execution begins.

#### Q6. Difference between javac and java?

**Answer:** `javac` compiles source into bytecode; `java` launches the compiled class on the JVM.

#### Q7. Why run class name instead of .class filename?

**Answer:** The launcher expects a class name. Use `java -cp src/day-1 HelloJava`, not `java HelloJava.class`.

### Lesson 3 — Variables and data types

A variable is a named place to store a value. Java is statically typed, so each variable has a type checked by the compiler.

JavaScript:

```javascript
let temperature = 28.5;
let deviceName = "Sensor-01";
let isActive = true;
```

Java:

```java
double temperature = 28.5;
String deviceName = "Sensor-01";
boolean isActive = true;
```

Assigning a string to a `double` is a compile-time error.

#### Eight primitive types

| Type | Example | Typical use |
|---|---|---|
| `byte` | `byte level = 100;` | Small-range integer |
| `short` | `short count = 1000;` | Short-range integer |
| `int` | `int deviceId = 101;` | Whole numbers |
| `long` | `long timestamp = 9876543210L;` | Large whole numbers |
| `float` | `float voltage = 3.3f;` | Decimal, less precision |
| `double` | `double temperature = 28.5;` | Decimal, usual default |
| `char` | `char grade = 'A';` | Single UTF-16 code unit |
| `boolean` | `boolean isActive = true;` | True/false |

`String` is a class (reference type), not a primitive.

#### Declaration, initialization and reassignment

```java
int deviceId;       // declaration
deviceId = 101;     // initialization
deviceId = 102;     // reassignment
```

#### What does var mean in Java?

Since Java 10, `var` can infer a local variable's type from its initializer:

```java
var temperature = 28.5;       // inferred double
var deviceName = "Sensor-01"; // inferred String
```

This is not dynamic typing. The inferred type remains fixed. `var` is for local variables with an initializer.

#### Practice — model a device

```java
int deviceId = 101;
String deviceName = "Boiler-Sensor-01";
double temperature = 28.5;
boolean isActive = true;

System.out.println(deviceId);
System.out.println(deviceName);
System.out.println(temperature);
System.out.println(isActive);
```

Expected output:

```text
101
Boiler-Sensor-01
28.5
true
```

#### Q8. Difference between declaration and initialization?

**Answer:** Declaration introduces a variable and its type; initialization gives it its first value. They can be combined: `int deviceId = 101;`.

#### Q9. Is Java var the same as JavaScript let?

**Answer:** No. Java `var` infers a fixed static type. JavaScript `let` permits reassignment to values of different types.

**Knowledge check — Lesson 3: 2/3.** The learner correctly identified the compile-time type error and that String is a reference type. Correction: Java `var` cannot change its inferred type from double to String.

### Lesson 4 — Operators and control flow

#### Arithmetic operators

| Operator | Meaning | Example |
|---|---|---|
| `+` | Addition | `10 + 5 = 15` |
| `-` | Subtraction | `10 - 5 = 5` |
| `*` | Multiplication | `10 * 5 = 50` |
| `/` | Division | `10 / 5 = 2` |
| `%` | Remainder | `10 % 3 = 1` |

Integer division discards the fractional part: `10 / 3` is `3`; `10.0 / 3` is approximately `3.3333`.

#### Comparison operators

`==`, `!=`, `>`, `<`, `>=`, `<=` compare values and produce a boolean.

```java
double temperature = 85.5;
System.out.println(temperature > 80); // true
System.out.println(temperature < 50); // false
```

#### Logical operators

- `&&`: AND — both conditions must be true.
- `||`: OR — at least one condition must be true.
- `!`: NOT — reverses a boolean value.

```java
boolean isActive = true;
double temperature = 85.5;
boolean needsAlert = isActive && temperature > 80;
System.out.println(needsAlert); // true
```

#### if / else

```java
double temperature = 85.5;

if (temperature > 80) {
    System.out.println("Critical temperature!");
} else if (temperature > 60) {
    System.out.println("Warning: High temperature");
} else {
    System.out.println("Temperature is normal");
}
```

Conditions are checked from top to bottom; the first matching branch executes. Syntax is very similar to JavaScript.

#### switch

Use switch to compare one value against several fixed options:

```java
String deviceStatus = "WARNING";

switch (deviceStatus) {
    case "ONLINE":
        System.out.println("Device is connected");
        break;
    case "WARNING":
        System.out.println("Device needs attention");
        break;
    case "OFFLINE":
        System.out.println("Device is disconnected");
        break;
    default:
        System.out.println("Unknown status");
}
```

In this traditional switch form, `break` prevents fall-through into the next case.

#### for loop

Use when the number of iterations is known:

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Reading " + i);
}
```

The three parts are initialization (`int i = 1`), continuation condition (`i <= 5`) and update (`i++`).

#### while loop

Use when repetition depends on a condition:

```java
int temperature = 70;
while (temperature <= 80) {
    System.out.println("Temperature: " + temperature);
    temperature += 5;
}
```

#### do-while loop

The body runs at least once because the condition is checked after the body:

```java
int attempts = 0;
do {
    System.out.println("Checking device...");
    attempts++;
} while (attempts < 3);
```

#### break and continue

- `continue` skips the current iteration.
- `break` exits the loop.

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) continue;
    if (i == 5) break;
    System.out.println(i);
}
```

Output: `1`, `2`, `4`.

### Lesson 4 — Practice questions

1. What is the result of `10 / 3` in Java, and why?
2. What is the difference between `&&` and `||`?
3. Which loop always executes at least once?
4. What is the difference between `break` and `continue`?

## Deliverable

A console application that registers devices, finds a device by ID, filters active devices, changes status and calculates a simple reading average.

## Completion checklist

- [x] JDK 21 and Maven use Java 21 in the terminal.
- [x] Java execution flow, first program, variables and data types taught.
- [x] Operators, decisions and loops taught.
- [ ] Understand class, object, interface and constructor.
- [ ] Use ArrayList, HashSet and HashMap.
- [ ] Use Stream `filter`, `map`, `collect` and `average`.
- [ ] Handle missing values with Optional.
- [ ] Run the application and explain the code without simply reading it.

## Notes / questions from the learner

Add new questions here as they arise:
- 
