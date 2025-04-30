package mk.ukim.finki.emt.lab.model.exceptions;
 
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book is not found");
    }
} 