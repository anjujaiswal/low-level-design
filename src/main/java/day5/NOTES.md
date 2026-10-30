# Day 5: Composition + equals/hashCode + Custom Exceptions (revision notes)

## 1. Composition over inheritance
- **Inheritance = "is-a". Composition = "has-a".** In LLD, composition is the default.
- **The inheritance trap: class explosion.** Petrol/Electric x with/without GPS -> PetrolCar, PetrolCarWithGps, ElectricCar, ElectricCarWithGps... every new option doubles the classes. And a class can only `extend` ONE parent.

```java
public interface Engine { void start(); }

public class Car {
    private final Engine engine;          // has-a

    public Car(Engine engine) {           // injected (Day 3)
        if (engine == null) throw new IllegalArgumentException("Engine is required");
        this.engine = engine;
    }

    public void start() { engine.start(); }   // delegation
}
```
Add `HybridEngine` -> one new class, `Car` unchanged. Behavior is **plugged in, not inherited.**

**When is inheritance right?** Only a true "is-a" forever AND children share real state (like `Employee`). Unsure -> compose.

## 2. equals and hashCode
**Default Java compares objects by memory address.** Two `new Seat("A", 1)` are "different" -> double booking.

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;                              // same object: shortcut
    if (o == null || getClass() != o.getClass()) return false; // null / other type: no crash
    Seat other = (Seat) o;                                   // safe cast now
    return number == other.number && row.equals(other.row);
}

@Override
public int hashCode() {
    return Objects.hash(row, number);                        // SAME fields as equals
}
```

### How a HashSet / HashMap finds an object
1. `hashCode()` picks the **bucket**
2. `equals()` checks objects **inside that bucket**

### The contract (interview favorite)
- `a.equals(b)` => `a.hashCode() == b.hashCode()`. **Always override BOTH.**
- Same fields in both.
- Fields used must **never change** -> make them `final`.

### What you proved with the experiment
`hashCode()` commented out, `equals()` kept:
- `List.contains` still worked (List uses only `equals`).
- `HashSet.contains` FAILED: default hashCode is address-based, two `new Seat("A",1)` land in different buckets, `equals` is never even called.
- Result: **A1 booked twice, no error**, and `availableCount()` wrong.
> equals without hashCode doesn't just fail, it creates a bug that LOOKS correct.

### Mutable key bug (think question)
> A HashSet stores each object in a bucket based on its hash at insertion time. If a field used in hashCode changes afterward, the hash changes, so lookups search the wrong bucket. contains and remove fail even for the same object, the entry is stuck in the set, and the seat can be double booked. That's why those fields must be final (immutable).

### getClass() vs instanceof
- `getClass() != o.getClass()`: only the exact same class can be equal. Keeps equals **symmetric**. Safer default.
- `instanceof`: subclasses can be equal too; can break symmetry if a subclass overrides equals.

### Shortcut: records
`public record Seat(String row, int number) {}` generates equals, hashCode, toString, getters. Fields are final automatically.

## 3. Custom exceptions
```java
public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException(String message) { super(message); }
}
```
- Extend `RuntimeException` (unchecked).
- Create one only for **domain failures callers want to handle specifically** (e.g. show "pick another seat"). Not for every error.

## 4. Screen design choices
```java
private final Set<Seat> seats;                       // valid seats: Set, O(1) contains, no duplicates
private final Set<Seat> booked = new HashSet<>();    // booked: Set

public Screen(List<Seat> seats) {
    if (seats == null || seats.isEmpty())
        throw new IllegalArgumentException("Screen must have at least one seat");
    this.seats = new HashSet<>(seats);               // defensive copy
}
```

## 5. Testing lessons from today
- **A test must target the real scenario.** Booking the SAME object twice passes even without equals/hashCode, so it proves nothing. Use two separate `new Seat("A", 1)` objects.
- **Test different cases, not the same case twice:** `""` (empty) vs `"   "` (blank). `isBlank()` catches both; `isEmpty()` only the first.
- **Error messages state the rule + the value:** `"Seat " + seat + " is already booked"`, `"Row must not be blank"`.

## 6. Answer shape for "what goes wrong" questions
**What happens step by step -> the consequence -> the rule -> the fix.**
A HashSet stores each object in a bucket based on its hash code at insertion time. 
If a field used in hashCode changes afterward, the hash changes, so lookups search the wrong bucket. 
contains and remove fail even for the same object, the entry is stuck in the set, and the seat can be 
double booked. That’s why fields used in equals/hashCode should be final, making the object immutable.