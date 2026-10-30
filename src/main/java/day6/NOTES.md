# Day 6: Library Management, first full LLD design (revision notes)

## The LLD framework (use for every problem)
1. Clarify requirements (always ask 2-3 questions, even if it seems clear)
2. Identify entities (nouns -> classes, verbs -> methods)
3. Map relationships (is-a / has-a, one-to-many)
4. Choose collections (what do I look up, by what?)
5. Define core APIs
6. Code
7. Discuss extensibility

## The 7 design questions (how the design was found)
1. **Nouns -> classes, verbs -> methods.**
2. **"Is this field the same for every instance?"** No -> split into two classes. Book (isbn, title, author) vs BookCopy (copyId, status). Same pattern: Movie/Show, Flight/Seat, Product/StockItem.
3. **"What must I remember after this action?"** Several facts -> the action becomes a class. Borrow -> Loan. (Booking, Payment, Ticket...)
4. **"Who owns the data?"** -> the method goes there. borrow() lives in Library, fee rule in MembershipType, status rule in BookCopy.
5. **"Do the types differ in data or behavior?"** Data -> enum with fields. Behavior -> interface. Shared state + behavior -> abstract class.
6. **"What do I look up, by what?"** -> the collection. Looping to find something = you need a Map.
7. **"How will I test it?"** -> pass time and external dependencies in (LocalDate as a parameter).

## Final design
- **Book**: isbn, title, author, all final, validated. Immutable catalog entry.
- **CopyStatus**: AVAILABLE, BORROWED
- **BookCopy**: copyId, Book, status. markBorrowed() / markReturned() / isAvailable(). Protects its own status.
- **MembershipType**: REGULAR(3, 14, 10), PREMIUM(5, 30, 5). calculateFee(daysLate).
- **Member**: id, name, type. Does NOT hold borrowed books.
- **Loan**: copy, member, borrowDate, dueDate (COMPUTED from type, not passed in). daysLate(), calculateFee().
- **Library** (coordinator):
  - Map<String, List<BookCopy>> copiesByIsbn
  - Map<String, Member> membersById
  - Map<String, Loan> activeLoansByCopyId
  - Set<String> copyIds (uniqueness across the whole library)

## Design principles visible in this code
| Principle | Where |
|---|---|
| Encapsulation / invariants | BookCopy status changes only via markBorrowed/markReturned |
| Single Responsibility | Book = catalog, BookCopy = physical item, Loan = borrowing event, Library = coordination |
| Information Expert ("method goes where data is") | fee in MembershipType, overdue in Loan, status in BookCopy |
| Tell, don't ask | Library calls copy.isAvailable() / loan.calculateFee(), never does the math itself |
| Open/Closed | New STUDENT membership = one enum line |
| Immutability | Book, Member, Loan all final fields |
| Single source of truth | No stored totalCopies; counts are derived |
| Make invalid states impossible | dueDate computed, not passed; status starts AVAILABLE |
| Dependency injection (of time) | LocalDate passed into borrow/returnCopy |
| Consistency | returnCopy: validate -> compute fee -> then change state |

## Bugs found this week (don't repeat)
- borrow() used LocalDate.now() instead of the date parameter -> untestable fees
- returnCopy() never removed the loan -> member stuck at limit forever
- State changed before fee computed -> half-updated system if fee throws
- copiesByIsbn.get(unknown) -> NullPointerException; use getOrDefault
- Duplicate copy IDs silently overwrote loans (Map.put replaces!) -> uniqueness Set
- `!isAfter` rejects the SAME day; use `isBefore` (date boundary bug)
- LocalDate is an object: no `<`, `-`; use isAfter/isBefore, ChronoUnit.DAYS.between
- Uniqueness scope: copy IDs unique across the LIBRARY, not per book

## Testing lessons
- Test the core business logic first (late fee!), not just validation.
- Distinct test IDs ("ISBN-1", "C1", "M1") so mixed-up parameters fail loudly.
- Label output lines so failures are obvious.
- Labels must be true: "Regular late fee" must actually test a regular, late return.
