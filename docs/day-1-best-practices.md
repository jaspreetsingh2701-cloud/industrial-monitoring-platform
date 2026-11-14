# Java Best Practices

## Class visibility

**Problem:** A class is declared `public` even when it is only used inside its package.

**Solution:** Use package-private (no modifier) when the class does not need to be accessed from other packages.

**Best practices:**
- Keep implementation classes package-private when possible.
- Use `public` only for types that are part of the package's public API.
- Keep test classes in the same package when they need package-private access.
- Prefer named packages over Java's default package as the project grows.

## Encapsulation

**Problem:** Other classes can change an object's fields directly.

**Solution:** Make fields `private` and expose only the methods callers need.

**Best practices:**
- Keep fields private.
- Use getters for read access.
- Use methods to control state changes.
- Avoid setters when a value should not change after construction.

## Immutable fields

**Problem:** An object's identity or fixed data can accidentally be reassigned.

**Solution:** Use `final` for fields that should be assigned only once.

**Best practices:**
- Initialize required fields in the constructor.
- Remember: a `final` collection reference can still have its contents changed.
- `final` does not automatically make an object immutable.

## Optional

**Problem:** A lookup may return no result, leading to null checks scattered across the code.

**Solution:** Return `Optional<T>` when a result may be absent.

**Best practices:**
- Use `Optional.empty()` for no result.
- Use `orElseGet()` when the fallback should be evaluated only if empty.
- Avoid calling `get()` without checking presence.

## Streams and averages

**Problem:** `Stream<Double>` does not provide numeric operations such as `average()`.

**Solution:** Use `mapToDouble()` to create a `DoubleStream`.

**Best practices:**
- Use `map()` when transforming objects into objects.
- Use `mapToInt()`, `mapToLong()`, or `mapToDouble()` for primitive numeric streams.
- Decide explicitly what an empty result should mean; do not use `0.0` as a fallback unless that is correct for the business case.

## Collection checks

**Problem:** `allMatch()` returns `true` for an empty stream.

**Solution:** Check that the collection is non-empty first if the business rule requires at least one item.

**Best practices:**
- Remember that `allMatch()` and `noneMatch()` return `true` for an empty stream.
- Write tests for empty collections and boundary cases.

## Unit testing

**Problem:** Manual testing does not reliably catch regressions.

**Solution:** Write focused JUnit tests for expected behaviour.

**Best practices:**
- Keep each test focused on one behaviour.
- Use a fresh object/service in each test.
- Test normal cases, missing data, and empty collections.
- Use clear names such as `shouldReturnEmptyWhenDeviceDoesNotExist`.
- Run `mvn test` before committing.

## Code formatting

**Problem:** Different indentation and formatting styles make code harder to review.

**Solution:** Use one shared formatter configuration for the project.

**Best practices:**
- Format code before committing.
- Use the same formatter in the IDE and Maven.
- Run a formatting check in the build/CI so unformatted code is caught.
- Keep formatting separate from compilation; compilation should not unexpectedly rewrite source files.

**Commands (after the Maven formatter is configured):**
- `mvn spotless:apply` — format source files.
- `mvn spotless:check` — check formatting without changing files.
- `mvn verify` — run the configured build checks.
