# Day 3: Interfaces + Polymorphism + Dependency Injection (revision notes)

## 1. Interface vs abstract class
- **Abstract class** = what something IS ("is-a"). Can have fields, constructors, shared code. A class extends only ONE.
- **Interface** = what something CAN DO (a contract). No state. A class can implement MANY.
- **Default in LLD: start with an interface.** Use an abstract class only when implementations truly share fields/code.

```java
public interface NotificationChannel {
    void send(String message);
}

public class SmsChannel implements NotificationChannel {
    private final String phoneNumber;

    public SmsChannel(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}"))
            throw new IllegalArgumentException("Phone number must be exactly 10 digits");
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        System.out.println("SMS to " + phoneNumber + ": " + message);
    }
}
```

## 2. Program to an interface, not an implementation
`NotificationService` only knows `NotificationChannel`. Adding `PushChannel` needed **zero** changes to the service.
-> **Open/Closed Principle:** open for extension, closed for modification.

## 3. Dependency Injection
The service RECEIVES its channels through the constructor instead of creating them with `new`.

> Injecting the channels means the service depends only on the interface, not concrete classes. New channels can be added without modifying it (Open/Closed), and in tests I can inject fakes, so it's testable in isolation. This is Dependency Injection.

Answer shape for any "why" question: **what it does + the benefits + the name.**

## 4. Defensive copy
```java
private final List<NotificationChannel> channels;

public NotificationService(List<NotificationChannel> channels) {
    this.channels = new ArrayList<>(channels);   // own copy
}
```
- Storing the caller's list lets outsiders modify your object behind your back.
- `List.of(...)` is immutable -> `add` would throw `UnsupportedOperationException`.

## 5. Fail fast, reject null at the boundary
- No `null` placeholders + try/catch around valid setup. If setup fails, stop loudly.
- `addChannel(null)` must throw, or `notifyAll` crashes later somewhere confusing.

## 6. Test doubles (fakes)
```java
public class FakeChannel implements NotificationChannel {
    private final List<String> received = new ArrayList<>();
    @Override public void send(String message) { received.add(message); }
    public List<String> getReceived() { return received; }
}
```
Inject it, call `notifyAll("hi")`, check `received` is `[hi]`. No real email/SMS.

## 7. Regex escaping in Java
| You type | Regex sees | Means |
|---|---|---|
| `"\\d"` | `\d` | a digit |
| `"\\\\d"` | `\\d` | backslash then "d" (wrong) |
`matches()` checks the WHOLE string, so `^`/`$` aren't needed.

## 8. Reading a stack trace
Find the first line from YOUR package, then read upward to see what failed.

## Main structure (use every day)
1. Happy path, no try/catch
2. Failure tests, one try/catch each, specific exception
3. Unit test with a fake
