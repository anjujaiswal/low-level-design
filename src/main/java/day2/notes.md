# Day 2: Inheritance + Abstract Classes + Access Modifiers (revision notes)

## 1. Inheritance = "is-a"
```java
public abstract class Employee {
    private final String id;
    private String name;

    protected Employee(String id, String name) {   // only children call it
        this.id = id;
        this.name = name;
    }

    protected abstract double calculateMonthlyPay();   // children MUST implement

    public String getPaySlip() {                       // fixed skeleton
        return "E" + id + " - " + name + ": Rs" + calculateMonthlyPay();
    }
}

public class Contractor extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public Contractor(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);               // parent constructor first
        setHourlyRate(hourlyRate);     // reuse setters -> each rule in ONE place
        setHoursWorked(hoursWorked);
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate <= 0) throw new IllegalArgumentException("Hourly rate must be positive");
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0) throw new IllegalArgumentException("Hours can't be negative");
        this.hoursWorked = hoursWorked;
    }

    @Override
    protected double calculateMonthlyPay() { return hourlyRate * hoursWorked; }
}
```
- `abstract class`: can't `new` it. `abstract` method: children must implement it.
- Always use `@Override`.
- Inheritance only for a true "is-a". `Car extends Engine` is wrong: a car HAS an engine.

## 2. Runtime polymorphism (dynamic dispatch)
Variable type is `Employee`, but Java runs the ACTUAL object's `calculateMonthlyPay()`.
Loop over `List<Employee>`: no `instanceof`, no type checks.

## 3. Template Method + Open/Closed
Parent fixes the skeleton (`getPaySlip`), children fill one step (`calculateMonthlyPay`).
Add `Intern` -> new class only. `getPaySlip` and the loop don't change.

## 4. Validation rules
- Required fields go in the constructor (no salary-less employees).
- **"Positive" -> `<= 0`. "Non-negative" -> `< 0`.** 0 hours is valid; a 0 rate is not.
- Error message must match its own rule.
- `IllegalArgumentException` = bad input. `IllegalStateException` = object's state doesn't allow it.

## 5. Testing
- One `try/catch` per failure test (the first throw skips the rest).
- Test the boundary (0), not just -2.
- **Green output != correct code.** Predict first, compare line by line.
- `getPaySlip()` returns a String; wrap it in `println` to see it.

## 6. Access modifiers
| Modifier | Who can access |
|---|---|
| `private` | same class |
| (none) package-private | same package |
| `protected` | same package + children |
| `public` | everyone |

**Start `private`, open up only with a reason.**
- Fields -> always `private`
- Helper only this class uses -> `private`
- Only children need it -> `protected` (e.g. `calculateMonthlyPay`, abstract-class constructors)
- Outside world needs it -> `public`
