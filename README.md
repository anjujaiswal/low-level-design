# Low-Level Design in Java

A day-by-day practice log for **low-level design (LLD)**: object-oriented foundations first, then SOLID, design patterns, and full LLD interview problems. Every day has runnable code plus a `NOTES.md` with the concepts, interview-ready answers, and the mistakes made along the way.

## Progress

| Day | Topic | What gets built | Key ideas |
|---|---|---|---|
| [Day 1](src/main/java/day1) | Classes, objects, encapsulation | `Book` with borrow/return | Private fields, `final`, behavior over setters, protecting invariants |
| [Day 2](src/main/java/day2) | Inheritance, abstract classes | Payroll: `Employee`, `FullTimeEmployee`, `Contractor` | Runtime polymorphism, Template Method, Open/Closed, access modifiers, boundary validation |
| [Day 3](src/main/java/day3) | Interfaces, polymorphism | Notification service (email, SMS, push) | Program to an interface, dependency injection, defensive copies, test doubles |
| [Day 4](src/main/java/day4) | Enums, collections | Order management with a status state machine | Enums with behavior, exhaustive `switch`, choosing `List`/`Set`/`Map`/`EnumMap` |
| [Day 5](src/main/java/day5) | Composition, `equals`/`hashCode`, custom exceptions | `Car` + `Engine`; cinema seat booking | Composition over inheritance, the hash contract, why mutable keys break `HashSet` |
| [Day 6](src/main/java/day6) | First full LLD design | Library management system | Book vs. copy modeling, loans as events, a coordinator class, injecting time for testability |

## Design approach used for every problem

1. Clarify requirements
2. Nouns become classes, verbs become methods
3. **Is a field the same for every instance?** If not, split into two classes (Book / BookCopy)
4. **What must be remembered after an action?** If several facts, the action becomes a class (Loan)
5. **Who owns the data?** The method goes there
6. **Do types differ in data or in behavior?** Data: enum with fields. Behavior: interface
7. **What is looked up, and by what?** That decides the collection
8. **How will it be tested?** Pass time and external dependencies in

## Running the code

Requires a recent JDK (the project is set to Java 24; the code also compiles on Java 21).

Open the project in IntelliJ IDEA and run any `DayN` class, or from the command line:

```bash
javac -d out $(find src -name '*.java')
java -cp out day6.Day6
```

Each `DayN.main` follows the same structure: happy path first, then failure tests (one `try/catch` per case), then queries/summary.

## Coming next

SOLID principles, creational/structural/behavioral design patterns, concurrency for LLD, and classic problems: Parking Lot, LRU Cache, Rate Limiter, Elevator, Splitwise, BookMyShow, and more.
