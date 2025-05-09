package mk.ukim.finki.emt.lab.model.dto.display;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enums.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(Long id,
                             String name,
                             Category category,
                             Long author,
                             Long availableCopies,
                             boolean goodCondition) {
//TODO figure out user thingie
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies(), book.getGoodCondition());
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies, goodCondition);
    }
}
