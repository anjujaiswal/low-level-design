package day6;
//Book (isbn, title, author: all final)
public class Book {
    private final String isbn;
    private final String title;
    private final String author;

    public Book(String isbn, String title, String author) {
        if(isbn == null || title == null || author == null || isbn.isBlank() || title.isBlank() || author.isBlank()){
            throw new IllegalArgumentException("Book inputs cannot be null or blank");
        }
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
