package mk.ukim.finki.emt.lab.service.domain;


import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enums.Category;
import mk.ukim.finki.emt.lab.model.dto.previousDTOs.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> findById(Long id);

    //vo domain service layer treba da se pravat perku konstruktorite na modelite
    Optional<Book> create(Book book);
    Optional<Book> edit(Long id, Book book);

    Book delete(Long id);

//    Optional<Book> isRented(Long id);
      Optional<Book> isRented(Book book);

    Book create(String name, Category category, Long id, Long availableCopies);
    Optional<Book> create(BookDto book);
//    Book edit(Long id, BookDto book);
    Optional<Book> edit(Long id, BookDto book);

    Book edit(Long id, String name, Category category, Long authorId, Long availableCopies);




}
