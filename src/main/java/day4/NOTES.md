# Day 4: Enums + Collections (revision notes)

## 1. Enums: a fixed set of values

**Why an enum, not a String?** Name the mechanism:
- **Type safety:** `"PIAD"` compiles and fails silently at runtime. `OrderStatus.PIAD` is a compile error. Invalid values cannot exist.
- **Rules live in one place:** transition logic (`canMoveTo`) sits inside the enum, not scattered as string comparisons across the codebase.
- **Exhaustive switch:** add a new value (e.g. `RETURNED`) and the switch expression won't compile until you handle it.

Compare enums with `==` (each value exists exactly once).

```java
public enum OrderStatus {
    CREATED, PAID, SHIPPED, DELIVERED, CANCELLED;

    public boolean canMoveTo(OrderStatus next) {
        return switch (this) {
            case CREATED -> next == PAID || next == CANCELLED;
            case PAID    -> next == SHIPPED || next == CANCELLED;
            case SHIPPED -> next == DELIVERED;
            case DELIVERED, CANCELLED -> false;   // final states
        };
    }
}
```

### Enums with fields + behavior (senior move)
```java
public enum VehicleType {
    BIKE(1, 10.0), CAR(2, 20.0), TRUCK(4, 50.0);

    private final int spotsNeeded;
    private final double hourlyRate;

    VehicleType(int spotsNeeded, double hourlyRate) {   // always private
        this.spotsNeeded = spotsNeeded;
        this.hourlyRate = hourlyRate;
    }
    public double calculateFee(int hours) { return hours * hourlyRate; }
}
```
No `if (type == CAR) ... else if` chains. Data lives with the value it belongs to.
Built-ins: `values()`, `valueOf("CAR")`, `name()`.

## 2. Collections: choose by what you need to DO

| Need | Use | LLD example |
|---|---|---|
| Ordered, duplicates OK | `List` -> `ArrayList` | moves in a game, cart items |
| No duplicates, fast `contains` | `Set` -> `HashSet` | booked seats, unique customers |
| Look up by key | `Map` -> `HashMap` | `orderId -> Order` |
| Enum keys | `EnumMap` | `OrderStatus -> count` |

- **Lookup by ID -> always a Map.** `map.get(id)` is O(1); looping a List is O(n).
- **`HashSet` / `HashMap` have NO guaranteed order.** Insertion order -> `LinkedHashSet`/`LinkedHashMap`. Sorted -> `TreeSet`/`TreeMap`.
- **Declare the interface type:** `Map<String, Order> m = new HashMap<>();`

### Counting pattern
```java
Map<OrderStatus, Integer> count = new EnumMap<>(OrderStatus.class);
for (Order o : orders.values()) {
    count.put(o.getStatus(), count.getOrDefault(o.getStatus(), 0) + 1);
}
```

### Duplicate check BEFORE put
`HashMap.put` with an existing key **silently overwrites**. Always check `containsKey` first and throw.

### Don't return null for "not found"
Throw with the ID in the message: `"Order not found: " + id`.

## 3. Design idea: fixed initial state
A new `Order` always starts as `CREATED` (not a constructor argument).
> If status were a constructor argument, anyone could create an order directly in DELIVERED, bypassing the transition rules. Fixing the initial state forces every change through `moveTo()`, so the state machine can't be skipped.

**Thread from Days 1-4:** an object must make invalid states impossible.

## 4. Pre-submission checklist (use every day)
- [ ] Re-read requirements one by one
- [ ] "Positive" -> `<= 0`; "non-negative" -> `< 0`
- [ ] Validate ALL inputs first (null/blank), then assign
- [ ] Catch the specific exception, never `Exception`
- [ ] Fields `private`, `final` where possible
- [ ] Error messages state the rule and include the value/ID
- [ ] Predict output, run, compare line by line
