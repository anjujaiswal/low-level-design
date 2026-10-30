# Day 1: Classes, Objects, Encapsulation (revision notes)

## 1. Class vs object
- **Class** = blueprint. **Object** = a real thing built from it (`new Book(...)`).

## 2. Encapsulation: expose behavior, not data
```java
public class Book {
    private final String isbn;     // never changes -> final
    private final String title;
    private final String author;
    private boolean isAvailable;   // changes -> not final

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;   // valid initial state
    }

    public void borrow() {
        if (!isAvailable) throw new IllegalStateException("Book is already borrowed");
        isAvailable = false;
    }

    public void returnBook() {
        if (isAvailable) throw new IllegalStateException("Book was not borrowed");
        isAvailable = true;
    }

    public boolean isAvailable() { return isAvailable; }
    public String getTitle()     { return title; }
}
```

## 3. Rules
- Fields are always `private`. Use `final` for anything that never changes.
- **Not doing math on it? It's a `String`** (ISBN, phone, pincode, IDs). `long` drops leading zeros and can't hold `X` or `-`.
- Throw **specific** exceptions, never `new Exception(...)`.
- Validate state in **both** directions (`borrow` AND `returnBook`).
- Domain classes don't `System.out.println`. The caller prints.
- Getters are fine. Pointless setters are not.

## 4. Why `borrow()` beats `setIsAvailable(boolean)`
> A setter lets anyone change state without rules, so two people can borrow the same book. `borrow()` keeps the rule inside `Book`, so the object can never be in an invalid state, and future changes like due dates happen in one method instead of every caller.

**Setters expose data; methods express behavior and protect rules (invariants).**

## 5. Habits
- **Run before you send.** Paste the output with the code.
- Package names lowercase (`day1`), class names PascalCase (`Day1`), one class per file.
