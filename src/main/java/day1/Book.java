package day1;
class Book {
    //isbn, title, author, isAvailable
    private final String  isbn; //will not change //if not doing maths it will always be string
    private final String title;//will not change
    private final String author;//will not change
    private boolean isAvailable;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void borrow()  {
        if(!this.isAvailable){
            throw new IllegalStateException("Book is already borrowed");
        }
        this.isAvailable = false;

    }

    public void returnBook(){
        if(isAvailable) throw new IllegalStateException("Book was never borrowed");
        this.isAvailable = true;

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

    public boolean isAvailable() {
        return isAvailable;
    }
}



//my code before lesson
//class Book{
//    //isbn, title, author, isAvailable
//    private final long  isbn; //will not change
//    private final String title;//will not change
//    private final String author;//will not change
//    private boolean isAvailable;
//
//    public Book(long isbn, String title, String author) {
//        this.isbn = isbn;
//        this.title = title;
//        this.author = author;
//        this.isAvailable = true;
//    }
//
//    public void borrow() throws Exception {
//        if(!this.isAvailable){
//            throw  new Exception("Book is already borrowed");
//        }
//        this.isAvailable = false;
//        System.out.println("Book is borrowed succefully");
//    }
//
//    public void returnBook(){
//        this.isAvailable = true;
//        System.out.println("Book is returned");
//
//    }
//
//}

