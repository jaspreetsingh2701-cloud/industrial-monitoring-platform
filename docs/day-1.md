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


### Deeper explanation — Encapsulation and Abstraction

#### Encapsulation: compare without and with it

Without encapsulation, a public field can be changed by any caller, including to invalid values:

```java
public class Device {
    public double temperature;
}

Device device = new Device();
device.temperature = 25.5; // valid
device.temperature = -500; // also allowed — no validation
```

With encapsulation, make the field private and expose a controlled operation:

```java
public class Device {
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if (temperature < -50 || temperature > 200) {
            System.out.println("Invalid temperature!");
            return;
        }
        this.temperature = temperature;
    }
}

Device device = new Device();
device.setTemperature(25.5); // accepted
device.setTemperature(-500); // rejected
System.out.println(device.getTemperature()); // 25.5
```

| Without encapsulation | With encapsulation |
|---|---|
| Public fields | Private fields |
| Direct modification | Controlled methods |
| No central validation | Validation can be enforced |
| Invalid states are easier to create | Object can protect its invariants |

Encapsulation is not simply adding getters and setters. It is controlling access to internal state and enforcing the object's rules. Prefer meaningful operations such as `deactivate()` or `updateTemperature()` when they express the domain better than a generic setter.

#### Abstraction: expose what, hide how

Suppose the dashboard needs readings from temperature, pressure and humidity sensors. The dashboard should ask for a reading without knowing the sensor's internal implementation.

Without a shared contract, the dashboard may need sensor-type checks and separate logic for every sensor. With an interface, the dashboard depends on one capability:

```java
public interface Monitorable {
    double readValue();
}

public class TemperatureSensor implements Monitorable {
    @Override
    public double readValue() {
        return 28.5;
    }
}

public class PressureSensor implements Monitorable {
    @Override
    public double readValue() {
        return 101.2;
    }
}

public class Dashboard {
    public void displayReading(Monitorable sensor) {
        System.out.println(sensor.readValue());
    }
}
```

Use it:

```java
Dashboard dashboard = new Dashboard();
dashboard.displayReading(new TemperatureSensor());
dashboard.displayReading(new PressureSensor());
```

The dashboard calls the same method for both sensor types. It does not need to know how each sensor obtains its value. Adding another class that implements `Monitorable` does not require changing the dashboard method.

**Core idea:** abstraction exposes a capability (what an object can do) while hiding implementation details (how it does it).

#### Interface: a capability or contract

An interface defines methods a class agrees to provide. It is useful when different or unrelated classes share a capability.

```java
public interface Monitorable {
    double readValue();
}

public class TemperatureSensor implements Monitorable {
    @Override
    public double readValue() {
        return 28.5;
    }
}
```

A class can implement multiple interfaces. Interfaces cannot be instantiated and have no constructors. Interface fields are implicitly `public static final`; interfaces can also contain `default`, `static` and `private` methods.

#### Abstract class: a shared foundation

Use an abstract class when related classes need common state, a constructor and shared implementation, but some behaviour must be supplied by each child.

```java
public abstract class Sensor {
    protected int deviceId;
    protected String deviceName;

    public Sensor(int deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public void connect() {
        System.out.println(deviceName + " connected");
    }

    public abstract double readValue();
}

public class TemperatureSensor extends Sensor {
    public TemperatureSensor(int id, String name) {
        super(id, name);
    }

    @Override
    public double readValue() {
        return 28.5;
    }
}
```

Here, `connect()` is shared implementation. `readValue()` is abstract, so each concrete child must implement it. You cannot instantiate `Sensor` directly. `super(...)` calls the parent constructor.

#### Interface vs abstract class — side by side

| Feature | Interface | Abstract class |
|---|---|---|
| Main purpose | Define a capability/contract | Provide a shared base |
| Used by a class with | `implements` | `extends` |
| Constructor | No | Yes |
| Instance fields | No | Yes |
| Implemented methods | Can have default, static and private methods | Can have concrete methods |
| Abstract methods | Yes | Yes |
| Multiple | A class can implement many interfaces | A class can extend only one class |

A class can extend one abstract/base class and implement multiple interfaces, for example: `class TemperatureSensor extends Sensor implements Monitorable, Controllable`.

#### When to choose which in our platform

- Use `Monitorable` when a type must provide `readValue()`, regardless of its class hierarchy.
- Use an abstract `Sensor` when sensor classes share ID, name, connection state and common connection behaviour.
- Use separate interfaces such as `Monitorable` and `Controllable` for independent capabilities.

#### TypeScript comparison

TypeScript interfaces are similar to Java interfaces as contracts:

```typescript
interface Monitorable {
    readValue(): number;
}

class TemperatureSensor implements Monitorable {
    readValue(): number {
        return 28.5;
    }
}
```

TypeScript also supports `abstract class`. Java's `interface` and TypeScript's `interface` are similar in their contract role, though their language rules and runtime behaviour are not identical.

#### Four OOP pillars — quick distinction

| Pillar | Meaning | Monitoring example |
|---|---|---|
| Encapsulation | Protect internal data and control changes | Private temperature field with validation |
| Abstraction | Expose capabilities, hide implementation details | Dashboard calls `Monitorable.readValue()` |
| Inheritance | Reuse parent behaviour | TemperatureSensor extends Sensor |
| Polymorphism | Same call, different implementation | Temperature and pressure sensors implement `readValue()` differently |

## Deliverable

A console application that registers devices, finds a device by ID, filters active devices, changes status and calculates a simple reading average.

## Completion checklist

- [x] JDK 21 and Maven use Java 21 in the terminal.
- [x] Java execution flow, first program, variables and data types taught.
- [x] Operators, decisions and loops taught.
- [x] Understand class, object, interface and constructor.
- [ ] Use ArrayList, HashSet and HashMap.
- [ ] Use Stream `filter`, `map`, `collect` and `average`.
- [ ] Handle missing values with Optional.
- [ ] Run the application and explain the code without simply reading it.


### Lesson 5 — Object-Oriented Programming (OOP)

OOP models software as objects that combine state (data) and behaviour (methods). Our monitoring platform will model devices and sensors as Java objects.

#### Class and object

A class is a blueprint; an object is an instance created from it.

```java
public class Device {
    int deviceId;
    String deviceName;
    boolean isActive;
}
```

Create and use an object:

```java
Device device1 = new Device();
device1.deviceId = 101;
device1.deviceName = "Boiler Sensor";
device1.isActive = true;
System.out.println(device1.deviceName);
```

A class can create many independent objects, each with its own instance field values.

#### Constructor

A constructor initializes an object when it is created. It has the same name as the class and no return type (not even `void`).

```java
public class Device {
    int deviceId;
    String deviceName;
    boolean isActive;

    public Device(int deviceId, String deviceName, boolean isActive) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.isActive = isActive;
    }
}
```

Create it with `Device device = new Device(101, "Boiler Sensor", true);`.

If no constructor is declared, Java provides a default no-argument constructor. Once you declare a constructor yourself, Java does not automatically provide that default constructor.

#### The this keyword

`this` refers to the current object. In `this.deviceId = deviceId;`, the left side is the object's field and the right side is the constructor parameter.

#### Encapsulation

Encapsulation protects an object's internal state by making fields private and exposing controlled methods.

```java
public class Device {
    private int deviceId;
    private String deviceName;
    private boolean active;

    public Device(int deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.active = true;
    }

    public int getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public boolean isActive() { return active; }

    public void deactivate() {
        this.active = false;
    }
}
```

External code can call `device.deactivate()`, but cannot directly access `device.active`. This lets the class control and validate state changes.

#### Access modifiers

| Modifier | Accessibility |
|---|---|
| `public` | Accessible from anywhere |
| `protected` | Same package and subclasses (with Java's access rules) |
| No modifier (package-private) | Same package |
| `private` | Only within the declaring class |

Common practice: keep fields private, expose intended public methods, and keep implementation helpers private.

#### Inheritance

Inheritance lets a child class extend a parent class and reuse its accessible behaviour.

```java
public class Device {
    protected int deviceId;
    protected String deviceName;

    public Device(int deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public void connect() {
        System.out.println(deviceName + " connected");
    }
}

public class TemperatureSensor extends Device {
    public TemperatureSensor(int deviceId, String deviceName) {
        super(deviceId, deviceName);
    }

    public void readTemperature() {
        System.out.println("Reading temperature...");
    }
}
```

`extends` declares inheritance; `super(...)` invokes the parent constructor. Java classes have single class inheritance: a class can extend only one class.

#### Polymorphism

Polymorphism allows the same method call to behave differently depending on the object's runtime type.

```java
class Device {
    public void read() {
        System.out.println("Reading generic device");
    }
}

class TemperatureSensor extends Device {
    @Override
    public void read() {
        System.out.println("Reading temperature");
    }
}

class PressureSensor extends Device {
    @Override
    public void read() {
        System.out.println("Reading pressure");
    }
}

Device first = new TemperatureSensor();
Device second = new PressureSensor();
first.read();
second.read();
```

Output is `Reading temperature` followed by `Reading pressure`. The reference type is `Device`, but Java dispatches the overridden method based on the actual object at runtime. `@Override` asks the compiler to verify that the method really overrides a parent method.

#### Abstraction: abstract class

An abstract class can provide shared implementation while requiring subclasses to implement selected methods.

```java
abstract class Device {
    public abstract void read();

    public void connect() {
        System.out.println("Device connected");
    }
}

class TemperatureSensor extends Device {
    @Override
    public void read() {
        System.out.println("Reading temperature");
    }
}
```

You cannot instantiate an abstract class directly.

#### Abstraction: interface

An interface defines a contract. A class uses `implements` to promise that it provides the required behaviour.

```java
public interface Monitorable {
    void read();
}

public class TemperatureSensor implements Monitorable {
    @Override
    public void read() {
        System.out.println("Reading temperature");
    }
}

Monitorable sensor = new TemperatureSensor();
sensor.read();
```

A Java class can implement multiple interfaces, but can extend only one class. Interfaces can also contain default, static and private methods; their fields are implicitly `public static final`. Interfaces have no constructors.

#### Abstract class vs interface

| Abstract class | Interface |
|---|---|
| A class extends it | A class implements it |
| Can have instance fields and constructors | No instance constructors; fields are constants |
| Can contain abstract and concrete methods | Can declare abstract, default, static and private methods |
| A class can extend only one class | A class can implement multiple interfaces |

For our monitoring platform, `Monitorable` can be a contract implemented by different sensor types.

#### Four pillars of OOP

| Pillar | Meaning | Monitoring example |
|---|---|---|
| Encapsulation | Protect internal data | Private device fields |
| Inheritance | Reuse parent behaviour | TemperatureSensor extends Device |
| Polymorphism | Same call, different implementation | Sensor-specific `read()` |
| Abstraction | Expose a contract, hide details | Monitorable interface |

#### Lesson 5 practice

1. What is the difference between a class and an object?
2. Why does a constructor have no return type?
3. Why do we make fields private?
4. What does `super()` do?
5. What is the difference between `extends` and `implements`?
6. What is runtime polymorphism?



## Lessons 6–9 — Collections, lambdas, Streams and Optional

### Collections: Array, ArrayList, HashSet and HashMap

An **array** is a fixed-size language feature; it is not part of the Collections Framework. **ArrayList**, **HashSet** and **HashMap** are collection types.

| Structure | Stores | Add / update | Access |
|---|---|---|---|
| Array | Fixed-size indexed values | Assign by index | array[0] |
| ArrayList<E> | Ordered elements; duplicates allowed | add(value) | get(index) |
| HashSet<E> | Unique elements; no guaranteed order | add(value) | contains(value) |
| HashMap<K,V> | Unique keys mapped to values | put(key, value) | get(key) |

Java collections store objects, not primitive values. Use wrapper classes such as Integer for int and Double for double. Java automatically converts between primitive values and wrappers in many assignments and collection operations; this is called **autoboxing** and **unboxing**.

~~~java
int count = 10;
Integer boxedCount = count; // autoboxing: int -> Integer
int unboxedCount = boxedCount; // unboxing: Integer -> int

List<Integer> ids = new ArrayList<>();
ids.add(101); // int is automatically boxed to Integer
~~~

### Iterating through collections

Enhanced for loop works for lists and sets:

~~~java
for (String device : devices) {
    System.out.println(device);
}
~~~

For a map, iterate through its entries:

~~~java
for (Map.Entry<Integer, String> entry : deviceNames.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
~~~

entrySet() provides key-value entries; getKey() returns the key and getValue() returns the value. A HashMap does not guarantee iteration order.

### Lambda expressions and functional interfaces

A lambda is a concise implementation of a functional interface (an interface with one abstract method).

~~~java
List<Integer> numbers = List.of(10, 20, 30);
numbers.forEach(number -> System.out.println(number));
~~~

Java lambda syntax uses ->; JavaScript arrow functions use =>. Common built-in functional interfaces include Predicate<T> (tests a condition), Function<T,R> (converts a value), Consumer<T> (accepts a value and returns nothing) and Supplier<T> (supplies a value).

### Streams: filter, map and reduce

A stream processes data through a pipeline. It does not itself store data and normally does not modify the source collection.

~~~java
List<Integer> temperatures = List.of(20, 25, 30, 35, 40);

List<Integer> hotReadings = temperatures.stream()
    .filter(temp -> temp > 30)
    .toList(); // [35, 40]

List<Integer> doubled = temperatures.stream()
    .map(temp -> temp * 2)
    .toList(); // [40, 50, 60, 70, 80]

int total = temperatures.stream()
    .reduce(0, (sum, temp) -> sum + temp); // 150
~~~

- filter() keeps elements matching a condition.
- map() transforms each element.
- reduce() combines elements into one result.
- Intermediate operations such as filter() and map() build the pipeline; terminal operations such as toList(), forEach() and reduce() trigger processing.
- toList() returns an unmodifiable list in modern Java (including Java 21).

### Optional: represent a value that may be absent

Optional<T> makes possible absence explicit in a method's return type. It is useful for results such as a device lookup that may not find a match.

~~~java
Optional<String> found = Optional.of("Temperature Sensor");
Optional<String> missing = Optional.empty();

String name = missing.orElse("Unknown Device");
~~~

Common methods: of(value) (non-null value), ofNullable(value) (nullable input), empty(), isPresent(), ifPresent(...), map(...), orElse(...) and orElseThrow(). Optional.of(null) throws NullPointerException; use ofNullable() when the input may be null. Prefer orElseGet() when computing the fallback should happen only if needed.

---

## Lesson 10 — Build the first Device domain model

**Goal:** use classes, encapsulation, constructors, ArrayList, Streams and Optional together. Create these files under src/day-1/domain-model/. For this first exercise, keep them in the default package (do not add package declarations).

### 1. Device.java

~~~java
public class Device {
    private final int id;
    private final String name;
    private boolean active;

    public Device(int id, String name) {
        this.id = id;
        this.name = name;
        this.active = true;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return active; }

    public void deactivate() {
        this.active = false;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (active: " + active + ")";
    }
}
~~~

The ID and name are final because they should not change after construction. The active state can change through the deactivate() method.

### 2. DeviceService.java

~~~java
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceService {
    private final List<Device> devices = new ArrayList<>();

    public void register(Device device) {
        devices.add(device);
    }

    public Optional<Device> findById(int id) {
        return devices.stream()
            .filter(device -> device.getId() == id)
            .findFirst();
    }

    public List<Device> findActiveDevices() {
        return devices.stream()
            .filter(Device::isActive)
            .toList();
    }
}
~~~

findFirst() returns an Optional<Device> because there may be no matching device. Device::isActive is a method reference, equivalent here to device -> device.isActive().

### 3. Main.java

~~~java
public class Main {
    public static void main(String[] args) {
        DeviceService service = new DeviceService();

        service.register(new Device(101, "Boiler Temperature"));
        service.register(new Device(102, "Pipeline Pressure"));
        service.register(new Device(103, "Tank Humidity"));

        service.findById(102).ifPresent(System.out::println);

        service.findById(103).ifPresent(Device::deactivate);

        System.out.println("Active devices:");
        service.findActiveDevices().forEach(System.out::println);

        System.out.println("Missing device:");
        System.out.println(
            service.findById(999).map(Device::getName).orElse("Not found")
        );
    }
}
~~~

Compile and run from the repository root:

~~~powershell
javac -d out src/day-1/domain-model/Device.java src/day-1/domain-model/DeviceService.java src/day-1/domain-model/Main.java
java -cp out Main
~~~

Expected behaviour: device 102 is printed; device 103 is deactivated; the active list contains devices 101 and 102; lookup of 999 prints **Not found**.

### Practice tasks

1. Register a fourth device with ID 104.
2. Deactivate device 101.
3. Find device 101 and print its name using map() and orElse().
4. Add a method to DeviceService that returns the total number of registered devices.
5. Explain why findById() returns Optional<Device> instead of returning null.

### Progress checklist

- [x] Array vs ArrayList; ArrayList, HashSet and HashMap basics.
- [x] Wrapper types, autoboxing and unboxing.
- [x] Enhanced for loops and map entry iteration.
- [x] Lambda expressions and functional interfaces.
- [x] Stream filter, map, reduce and terminal operations.
- [x] Optional basics and safe lookup patterns.
- [ ] Complete and run the Device domain model exercise.
- [ ] Add SensorReading and calculate an average reading.
- [ ] Add basic JUnit tests and review Day 1 interview questions.

## Notes / questions from the learner

Add new questions here as they arise:
- 
