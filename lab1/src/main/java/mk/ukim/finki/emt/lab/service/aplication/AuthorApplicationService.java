package mk.ukim.finki.emt.lab.service.aplication;

import mk.ukim.finki.emt.lab.model.dto.create.CreateAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateAuthorDto;
import mk.ukim.finki.emt.lab.model.view.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> listAll();

    Optional<DisplayAuthorDto> findById(Long id);
// vo application layer treba da se pravat preku dto, specificno peku cfeateDto za create
    Optional<DisplayAuthorDto> create(CreateAuthorDto createAuthorDto);

    Optional<DisplayAuthorDto> edit(Long id, UpdateAuthorDto updateAuthorDto);

    void delete(Long id);

    List<BooksPerAuthorView> findAllBooksPerAuthor();
    BooksPerAuthorView findBooksPerAuthor(Long id);
    void refreshMaterializedView();
}
