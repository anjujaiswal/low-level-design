package day6;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Loan (BookCopy copy, Member member, LocalDate borrowDate, LocalDate dueDate)
//
//long daysLate(LocalDate returnDate): 0 if on time
//double calculateFee(LocalDate returnDate): asks the member’s type for the fee
//
public class Loan {
    private final BookCopy bookCopy;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    public Loan(BookCopy bookCopy, Member member, LocalDate borrowDate) {
        if (bookCopy == null || member == null || borrowDate == null) {
            throw new IllegalArgumentException("Copy, member and borrow date are required");
        }
        this.bookCopy = bookCopy;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(member.getMembershipType().getLoanDays());
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public long  daysLate(LocalDate returnDate){
        if (returnDate == null || returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Return date cannot be before the borrowDate");                              // on time, or early
        }
        if (!returnDate.isAfter(dueDate)) {
            return 0;                                          // on time, or early
        }
        return ChronoUnit.DAYS.between(dueDate, returnDate);
    }

    public double calculateFee(LocalDate returnDate){
        long  daysLate = daysLate(returnDate);
        return this.member.getMembershipType().calculateFee(daysLate);
    }

}
