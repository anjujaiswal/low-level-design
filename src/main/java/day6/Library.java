package day6;

import java.time.LocalDate;
import java.util.*;

//Library: the coordinator
//
//Map<String, List<BookCopy>> copiesByIsbn: borrowing starts from an ISBN, so jump straight to that book’s copies
//Map<String, Member> membersById: look up a member by ID in O(1)
//Map<String, Loan> activeLoansByCopyId: on return you have the copy in hand, so find its loan instantly
//
public class Library {
    private final Map<String, List<BookCopy>> copiesByIsbn = new HashMap<>();
    private final Map<String, Member> membersById = new HashMap<>();
    private final Map<String, Loan> activeLoansByCopyId = new HashMap<>();
    private final Set<String> copyIds = new HashSet<>();

    public void addCopy(BookCopy copy) {
        // 1. reject null
        if(copy == null) {
            throw new IllegalArgumentException("Copy cannot be null");
        }
        if (!copyIds.add(copy.getCopyId())) {
            throw new IllegalArgumentException("Copy " + copy.getCopyId() + " already exists");
        }

        // 2. find the list for copy's ISBN (create it if missing), add the copy
        //    hint: copiesByIsbn.computeIfAbsent(isbn, k -> new ArrayList<>()).add(copy);
        copiesByIsbn.computeIfAbsent(copy.getBook().getIsbn(), k-> new ArrayList<>()).add(copy);
    }

    public void addMember(Member member) {
        // reject null and duplicate IDs (same as Day 4's addOrder)
        if(member == null ){
            throw new IllegalArgumentException("Member cannot be null " );
        }
        if(membersById.containsKey(member.getMemberId())){
            throw new IllegalArgumentException("Member " + member.getMemberId() + " already exists" );
        }
        membersById.put(member.getMemberId(),member);

    }

    public Loan borrow(String memberId, String isbn, LocalDate date) {
        // 1. find the member, or throw
        // 2. count this member's active loans; at their limit? throw
        // 3. do they already hold a copy of this ISBN? throw
        // 4. find an AVAILABLE copy of this ISBN, or throw
        // 5. copy.markBorrowed()
        // 6. create the Loan, store it in activeLoansByCopyId, return it
        if(!membersById.containsKey(memberId)){
            throw new IllegalArgumentException("This member with id "+memberId+" does not exist");
        }
        Member member = membersById.get(memberId);
        int activeLoans = 0;
        for (Loan loan : activeLoansByCopyId.values()) {
            if (loan.getMember().getMemberId().equals(memberId)) {
                activeLoans++;
                if(loan.getBookCopy().getBook().getIsbn().equals(isbn)){
                    throw new IllegalStateException("Member " + memberId + " has already the copy");
                }
            }
        }
        if (activeLoans >= member.getMembershipType().getMaxBooks()) {
            throw new IllegalStateException("Member " + memberId + " has reached the borrowing limit");
        }
        for(BookCopy copy: copiesByIsbn.getOrDefault(isbn,List.of())){
            if(copy.isAvailable()){
                copy.markBorrowed();
                Loan loan = new Loan(copy,member, date);
                activeLoansByCopyId.put(copy.getCopyId(), loan);
                return loan;

            }
        }
        throw new IllegalStateException("No available copy of " + isbn);

    }

    public double returnCopy(String copyId, LocalDate date) {
        // 1. find the loan by copyId, or throw ("copy is not on loan")
        // 2. calculate the fee FIRST (loan.calculateFee(date))
        // 3. copy.markReturned(), remove the loan from the map
        // 4. return the fee
        if(!activeLoansByCopyId.containsKey(copyId)){
            throw new IllegalStateException("Copy " + copyId + " is not on loan");
        }
        Loan loan = activeLoansByCopyId.get(copyId);

        double fee = loan.calculateFee(date);     // may throw: nothing changed yet
        loan.getBookCopy().markReturned();
        activeLoansByCopyId.remove(copyId);

        return fee;
    }

    public List<Book> searchByTitle(String title) {
        /* loop all copies' books, match, no duplicates */
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Search term must not be blank");
        }
        List<Book> books = new ArrayList<>();
        for(Map.Entry<String,List<BookCopy>> bookCopy : copiesByIsbn.entrySet()){
            Book book = bookCopy.getValue().getFirst().getBook();
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                books.add(book);
            }
        }
        return books;
    }
    public List<Book> searchByAuthor(String author) { /* same */
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Search term must not be blank");
        }
        List<Book> books = new ArrayList<>();
        for(Map.Entry<String,List<BookCopy>> bookCopy : copiesByIsbn.entrySet()){
            Book book = bookCopy.getValue().getFirst().getBook();
            if(book.getAuthor().toLowerCase().contains(author.toLowerCase())){
                books.add(book);
            }
        }
        return books;
    }

    public List<BookCopy> getCopiesOf(String memberId) {
        // loop activeLoans, collect copies where loan's member has this id
        List<BookCopy> copies = new ArrayList<>();
        for(Map.Entry<String, Loan> activeLoan : activeLoansByCopyId.entrySet()){
            Loan loan = activeLoan.getValue();
            Member member = loan.getMember();
            if(member.getMemberId().equals(memberId)){
                copies.add(loan.getBookCopy());
            }
        }
        return copies;
    }

}
