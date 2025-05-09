package mk.ukim.finki.emt.lab.model.dto.create;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enums.Category;

public record CreateBookDto(String name, Category category, Long author, Long availableCopies, boolean goodCondition) {

    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies(),
                book.getGoodCondition()
        );
    }


    public Book toBook(Author author){
        return new Book(name, category, author, availableCopies, goodCondition);
    }
}
