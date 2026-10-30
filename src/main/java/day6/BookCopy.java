package day6;
//BookCopy (copyId, Book book, CopyStatus status)

//markBorrowed(): throws if not AVAILABLE
//markReturned(): throws if not BORROWED
//
//Why: the physical item. It protects its own status, the same as Day 1’s borrow()/returnBook().
//
public class BookCopy {
    private final String copyId;
    private final Book book;
    private CopyStatus status;

    public BookCopy(String copyId, Book book) {
        if(copyId == null || copyId.isBlank() || book == null){
            throw new IllegalArgumentException("Book Copy inputs cannot be null or blank");
        }
        this.copyId = copyId;
        this.book = book;
        this.status = CopyStatus.AVAILABLE;
    }

    public String getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public boolean isAvailable(){
        return this.status == CopyStatus.AVAILABLE;
    }

    public void markBorrowed(){
        if(!isAvailable()){
            throw new IllegalStateException("Book Copy " + copyId+ " of Book " + book+ " is already booked");
        }
        this.status = CopyStatus.BORROWED;
    }

    public void markReturned(){
        if(isAvailable()){
            throw new IllegalStateException("Book Copy " + copyId+ " of Book " + book+ " is not borrowed");
        }
        this.status = CopyStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "copyId='" + copyId + '\'' +
                ", book=" + book +
                ", status=" + status +
                '}';
    }
}
