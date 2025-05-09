package mk.ukim.finki.emt.lab.service.aplication;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.dto.create.CreateBookDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayBookDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateBookDto;
import mk.ukim.finki.emt.lab.model.view.BooksInGoodConditionView;
import mk.ukim.finki.emt.lab.model.view.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;


public interface BookApplicationService {

    List<DisplayBookDto> listAll();

    Optional<DisplayBookDto> findById(Long id);

    // vo application layer treba da se pravat preku dto, specificno peku cfeateDto za create
    Optional<DisplayBookDto> create(CreateBookDto createBook);

    Optional<DisplayBookDto> edit(Long id, UpdateBookDto updateBookDto);

    Book delete(Long id);


    Optional<DisplayBookDto> isRented(Long id);

    List<BooksInGoodConditionView> listBooksInGoodCondition();
    void refreshMaterializedView();
}
