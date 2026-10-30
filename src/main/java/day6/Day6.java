package day6;

import java.time.LocalDate;
import java.util.List;

//Book (isbn, title, author: all final)
//Why: it’s the catalog entry, the same for every copy. It never changes, so it’s immutable.
//
//CopyStatus enum: AVAILABLE, BORROWED
//
//BookCopy (copyId, Book book, CopyStatus status)
//
//markBorrowed(): throws if not AVAILABLE
//markReturned(): throws if not BORROWED
//
//Why: the physical item. It protects its own status, the same as Day 1’s borrow()/returnBook().
//
//MembershipType enum: REGULAR(3, 14, 10), PREMIUM(5, 30, 5)
//
//Fields: maxBooks, loanDays, feePerDay
//double calculateFee(long daysLate): returns daysLate * feePerDay
//
//Why: the types differ only in numbers, so an enum is enough. Adding STUDENT takes one line.
//
//Member (id, name, MembershipType type)
//Why: just who they are. It does not hold the borrowed books. That’s the next class’s job.
//
//Loan (BookCopy copy, Member member, LocalDate borrowDate, LocalDate dueDate)
//
//long daysLate(LocalDate returnDate): 0 if on time
//double calculateFee(LocalDate returnDate): asks the member’s type for the fee
//
//Why: borrowing is an event with its own data. Your HashMap<Book, Date> on Member was trying to be this class. Making it explicit puts all four facts in one object.
//
//Library: the coordinator
//
//Map<String, List<BookCopy>> copiesByIsbn: borrowing starts from an ISBN, so jump straight to that book’s copies
//Map<String, Member> membersById: look up a member by ID in O(1)
//Map<String, Loan> activeLoansByCopyId: on return you have the copy in hand, so find its loan instantly
//
//borrow(memberId, isbn, date) does five checks in order:
//
//Find the member, or throw
//Are they at their limit? (Count their active loans.)
//Do they already hold a copy of this ISBN? (Your clarifying-question rule!)
//Find an AVAILABLE copy, or throw
//        copy.markBorrowed(), create the Loan with dueDate = date + loanDays, store it, return it
//
//returnCopy(copyId, date): find the loan, copy.markReturned(), remove the loan, return loan.calculateFee(date)
public class Day6 {
    public static void main(String[] args) {
        //happy path
        Library library = new Library();
        Book book1 = new Book("123","book1","anju");
        Book book2 = new Book("124","book2","isha");
        Book book3 = new Book("125","book3","avyu");
        Book book4 = new Book("126","book3","avyu");

        BookCopy bookCopy11 = new BookCopy("123",book1);
        BookCopy bookCopy12 = new BookCopy("124",book1);
        BookCopy bookCopy21 = new BookCopy("125",book2);
        BookCopy bookCopy31 = new BookCopy("126",book3);
        BookCopy bookCopy41 = new BookCopy("127",book4);
        BookCopy bookCopy51 = new BookCopy("123",book2);

        //Adding copies in library
        library.addCopy(bookCopy11);
        library.addCopy(bookCopy12);
        library.addCopy(bookCopy21);
        library.addCopy(bookCopy31);
        library.addCopy(bookCopy41);

        Member member1 = new Member("123","anju", MembershipType.PREMIUM);
        Member member2 = new Member("124","isha", MembershipType.REGULAR);

        //adding members in library
        library.addMember(member1);
        library.addMember(member2);

        //borrow a book
        library.borrow("123","123", LocalDate.parse("2026-10-30"));
        library.borrow("123","126", LocalDate.parse("2026-10-30"));
        library.borrow("124","125",LocalDate.parse("2026-10-30"));
        library.borrow("124","123",LocalDate.parse("2026-10-20"));
        library.borrow("124","124",LocalDate.parse("2026-10-20"));

        //return copy
        System.out.println("Regular late fee: " + library.returnCopy("123",LocalDate.parse("2026-10-30")));

        //search by title
        List<Book> booksBytTitle = library.searchByTitle("b");
        for(Book book : booksBytTitle){
            System.out.println(book);
        }
        //search by author
        List<Book> booksByAuthor = library.searchByAuthor("I");
        for(Book book :  booksByAuthor){
            System.out.println(book);
        }

        //get copies of member 124

        List<BookCopy> copies = library.getCopiesOf("124");
        for(BookCopy copy :  copies){
            System.out.println(copy);
        }

        //testing member

        try{
            new Member("1","a",null);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            new Member("1","",MembershipType.PREMIUM);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            new Member("","a",MembershipType.PREMIUM);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        // testing book
        try{
            new Book("1","a",null);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        //testing bookCopy
        try{
            new BookCopy("1", null);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        //test addcopy
        try{
            library.addCopy(bookCopy11);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            library.addCopy(null);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        //test addMember
        try{
            library.addMember(member1);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            library.addMember(null);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        // test borrow
        try{
            library.borrow("124","125",LocalDate.parse("2026-10-30"));
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
        try{
            library.borrow("126","125",LocalDate.parse("2026-10-30"));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            library.borrow("124","126",LocalDate.parse("2026-10-20"));
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
        try{
            library.returnCopy("124", LocalDate.parse("2026-10-20"));
            library.borrow("124","126",LocalDate.parse("2026-10-20"));
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
        //search
        try{
            List<Book> booksBytTitle1 = library.searchByTitle(null);
            for(Book book : booksBytTitle){
                System.out.println(book);
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }








    }
}
