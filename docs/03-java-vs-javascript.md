# Java Concepts Compared with JavaScript

This guide maps common Java concepts to familiar JavaScript concepts. The mapping is conceptual; the languages do not behave identically.

## 1. Collections

| Java collection | Purpose | JavaScript equivalent |
|---|---|---|
| `ArrayList` | Dynamic ordered list; allows duplicates | `Array` |
| `LinkedList` | List/deque implementation with efficient operations at ends | No direct built-in equivalent; array or custom linked list |
| `HashSet` | Unique elements; no guaranteed iteration order | `Set` |
| `LinkedHashSet` | Unique elements while preserving insertion order | `Set` |
| `TreeSet` | Unique elements kept in sorted order | No direct built-in equivalent; sorted array or library |
| `HashMap` | Key-value pairs; no guaranteed iteration order | `Map` or plain object |
| `LinkedHashMap` | Key-value pairs preserving insertion order | `Map` |
| `TreeMap` | Key-value pairs sorted by key | No direct built-in equivalent; `Map` plus sorting |
| `Queue` | First-in-first-out processing abstraction | Array used with `push` and `shift` |
| `Deque` | Add/remove at both ends | Array, with operation/performance differences |

## 2. Core language concepts

| Java | Purpose | JavaScript equivalent |
|---|---|---|
| Class | Blueprint for objects | `class` |
| Object | Instance of a class | Object / class instance |
| Interface | Contract a class agrees to implement | No native interface; TypeScript `interface` is closest |
| Abstract class | Base class that can define shared behaviour and abstract methods | Abstract class in TypeScript; JavaScript has no `abstract` keyword |
| Constructor | Initialises a new object | `constructor()` |
| `extends` | Inheritance | `extends` |
| `implements` | Declares interface implementation | TypeScript `implements` |
| `private` | Restricts member access | `#private` fields / TypeScript `private` |
| `static` | Member belongs to class, not instance | `static` |
| `enum` | Named set of constants | TypeScript `enum` or frozen object |
| `record` | Concise immutable data carrier | TypeScript type/interface or object convention |

## 3. Functional programming

| Java | Purpose | JavaScript equivalent |
|---|---|---|
| Lambda | Inline function expression | Arrow function |
| Functional interface | Interface with one abstract method | Function type / callback contract |
| Stream `map()` | Transform each element | Array `.map()` |
| Stream `filter()` | Keep matching elements | Array `.filter()` |
| Stream `forEach()` | Perform an action for each element | Array `.forEach()` |
| Stream `reduce()` | Combine elements into one result | Array `.reduce()` |
| Stream `sorted()` | Sort stream elements | Array `.sort()` |
| Stream `collect()` | Gather stream results into a collection | Often `.toArray()` or direct array/object construction |
| `Optional<T>` | Explicitly model a value that may be absent | `T \| null \| undefined` plus safe handling |
| Method reference `Type::method` | Concise reference to an existing method | Passing a function reference |

## 4. Error handling and asynchronous work

| Java | Purpose | JavaScript equivalent |
|---|---|---|
| `try/catch/finally` | Handle exceptions and cleanup | `try/catch/finally` |
| Checked exception | Exception the compiler requires code to handle or declare | No direct equivalent |
| Unchecked exception | Runtime exception not required to be declared | Runtime `Error` |
| `CompletableFuture` | Compose asynchronous computations | `Promise` |
| `ExecutorService` | Manage a pool of worker threads | No direct browser equivalent; Node worker threads/thread pools are conceptually related |
| `synchronized` | Coordinate access between threads | No direct language equivalent; locks/coordination primitives |
| `volatile` | Visibility of updates between threads | No direct JavaScript equivalent |

## 5. Important differences to remember

- Java is statically typed and checks types at compile time; JavaScript is dynamically typed at runtime. TypeScript adds a compile-time type system to JavaScript.
- Java source is compiled to bytecode and runs on the JVM; JavaScript runs in a JavaScript engine such as V8.
- Java has built-in thread-based concurrency; JavaScript commonly uses an event loop and asynchronous APIs, with workers for parallel execution.
- Java collections are explicit types with different performance and ordering characteristics. Choose a collection based on the operation you need.
- Java Streams are lazy for intermediate operations and execute when a terminal operation is invoked.

## 6. Small examples

### JavaScript
```javascript
const activeDevices = devices.filter(device => device.active);
const names = activeDevices.map(device => device.name);
```

### Java
```java
List<String> names = devices.stream()
    .filter(Device::isActive)
    .map(Device::getName)
    .toList();
```

The intent is similar: filter the collection, transform each matching item, and produce a result.
