package mk.ukim.finki.emt.lab.model.dto.update;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enums.Category;

public record UpdateBookDto(String name, Category category, Long author, Long availableCopies) {
    public static UpdateBookDto from(Book book) {
        return new UpdateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }


    public Book toBook(Author author){
        return new Book(name, category, author, availableCopies);
    }
}
