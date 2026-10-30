package day1;

public class Day1 {
    public static void main(String[] args)  {

//        Create 2 books, borrow one, and try borrowing it again.
//                Catch the exception and print a message.

        Book book1 = new Book("123","book1","aB");
        Book book2 = new Book("124","book2","ac");
//        try {
//            book1.borrow();
//            book1.borrow();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        book1.borrow();
        System.out.println("Borrowed: " + book1.getTitle());

        try {
            book1.borrow();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            book2.returnBook();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }


//       Why is setIsAvailable(boolean) worse than borrow() / returnBook()? Answer
//       it in your own words. I won't let that one slide,
//       because it's the core idea of today's lesson.

        //because we this function name is hard to understand this name does not sound meaningfull on reading
    }
}
