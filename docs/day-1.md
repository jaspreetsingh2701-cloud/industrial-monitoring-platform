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

**Status: JDK 21 and Maven are successfully configured.** Verified in a fresh Windows PowerShell 5.1 session.

- `JAVA_HOME`: `C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.13.9-hotspot`
- `where.exe java`: JDK 21 `bin\\java.exe` is the first match. The Oracle Java 8 launcher appears second and does not take priority.
- `where.exe javac`: JDK 21 `bin\\javac.exe`.
- `java -version`: OpenJDK 21.0.13 LTS (Eclipse Temurin).
- `javac -version`: `javac 21.0.13`.
- `mvn -version`: Apache Maven 3.6.3, running on Java 21.0.13.
- Environment: Windows 11, 64-bit, PowerShell 5.1.

**Conclusion:** The Java runtime, compiler, and Maven all use Java 21. Java 8 can remain installed because Java 21 resolves first on PATH.

### Writing and running your first Java program

Create `src/day-1/HelloJava.java`:

```java
public class HelloJava {
    public static void main(String[] args) {
        String message = "Welcome to Java!";
        System.out.println(message);
    }
}
```

Compile and run from the repository root in PowerShell:

```powershell
javac src\\day-1\\HelloJava.java
java -cp src\\day-1 HelloJava
```

Expected output:

```text
Welcome to Java!
```

Compilation creates `HelloJava.class` (Java bytecode). The Java launcher starts the class through the JVM. The `-cp` option sets the classpath, telling Java where to look for the compiled class.

#### Understanding the main method

- `public`: the launcher can access the method.
- `static`: the method belongs to the class and can be invoked without creating an instance.
- `void`: the method does not return a value.
- `main`: the conventional entry-point method name.
- `String[] args`: an array of strings containing any command-line arguments passed to the program.

#### Q5. What is the purpose of the main method?

**Answer:** For a traditional Java application launched with the Java launcher, `main` is the entry point where execution begins. The standard form is `public static void main(String[] args)`.

#### Q6. What is the difference between javac and java?

**Answer:** `javac` is the Java compiler. It compiles source files (for example, `.java`) into bytecode files (`.class`). The `java` launcher starts the compiled class on the JVM.

#### Q7. Why do we run the class name instead of the .class filename?

**Answer:** The launcher expects the class name, not a filename. For example, use `java -cp src/day-1 HelloJava`, not `java HelloJava.class`. The classpath (`-cp`) tells Java where the class file is located.

### Lesson 3 — Variables and data types

A variable is a named place to store a value. Java is statically typed, so each variable has a type that is checked by the compiler.

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

The declared type stays fixed. For example, assigning `"Hot"` to a `double` variable is a compile-time error.

#### Java's eight primitive types

| Type | Example | Typical use |
|---|---|---|
| `byte` | `byte level = 100;` | Small-range integer |
| `short` | `short count = 1000;` | Short-range integer |
| `int` | `int deviceId = 101;` | Whole numbers |
| `long` | `long timestamp = 9876543210L;` | Large whole numbers |
| `float` | `float voltage = 3.3f;` | Decimal with less precision |
| `double` | `double temperature = 28.5;` | Decimal (usual default) |
| `char` | `char grade = 'A';` | Single UTF-16 code unit |
| `boolean` | `boolean isActive = true;` | True/false |

`String` is not primitive; it is a class (reference type) used for text.

#### Declaration, initialization and reassignment

```java
int deviceId;       // declaration
deviceId = 101;     // initialization
deviceId = 102;     // reassignment
```

You can also declare and initialize in one statement: `int deviceId = 101;`.

#### What does `var` mean in Java?

Since Java 10, `var` can be used for local variable type inference:

```java
var temperature = 28.5;  // inferred as double
var deviceName = "Sensor-01"; // inferred as String
```

This is not dynamic typing. The compiler infers the type once, and the variable remains that type. `var` is limited to local variables with an initializer; it is not a replacement for declaring types everywhere.

#### Practice — model a device

Create a small program or add these variables to `HelloJava`, then print each value:

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

**Expected output:**

```text
101
Boiler-Sensor-01
28.5
true
```

#### Q8. What is the difference between declaration and initialization?

**Answer:** Declaration introduces a variable and its type (for example, `int deviceId;`). Initialization gives it its first value (for example, `deviceId = 101;`). They can happen in one statement.

#### Q9. Is Java's `var` the same as JavaScript's `let`?

**Answer:** No. Java `var` asks the compiler to infer a local variable's static type from its initializer. The type is still fixed. JavaScript `let` allows reassignment and the variable can hold values of different types.

## Deliverable

A console application that registers devices, finds a device by ID, filters active devices, changes status and calculates a simple reading average.

## Completion checklist
- [x] JDK 21 and Maven use Java 21 in the terminal.
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

### Q5. What is the purpose of the main method?

**Answer:** For a traditional Java application launched with the Java launcher, `main` is the entry point where execution begins. The standard form is `public static void main(String[] args)`.

### Q6. What is the difference between javac and java?

**Answer:** `javac` compiles Java source into bytecode; `java` launches the compiled class on the JVM.

### Q7. Why do we run the class name instead of the .class filename?

**Answer:** The launcher expects the class name. Use `java -cp src/day-1 HelloJava`, not `java HelloJava.class`.

### Q8. What is the difference between declaration and initialization?

**Answer:** Declaration introduces a variable and its type. Initialization assigns its first value. They can be combined: `int deviceId = 101;`.

### Q9. Is Java's `var` the same as JavaScript's `let`?

**Answer:** No. Java `var` infers a fixed static type from the initializer. JavaScript `let` allows reassignment, including values of different types.

## Notes / questions from the learner

Add new questions here as they arise:
- 


### Knowledge check — Lesson 3

**Score: 2/3**

1. Assigning a string such as `"Offline"` to an `int` variable causes a compile-time type error.
2. `String` is a reference type, not a primitive.
3. Java `var` does not allow a variable to change its inferred type. For example, after `var temperature = 28.5;`, assigning `"Hot"` is a compile-time error. This differs from JavaScript `let`, which permits values of different types at runtime.
