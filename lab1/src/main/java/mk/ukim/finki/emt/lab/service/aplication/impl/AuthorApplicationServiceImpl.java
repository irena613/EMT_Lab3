package mk.ukim.finki.emt.lab.service.aplication.impl;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.dto.create.CreateAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateAuthorDto;
import mk.ukim.finki.emt.lab.model.events.AuthorDeletedEvent;
import mk.ukim.finki.emt.lab.model.events.AuthorCreatedEvent;
import mk.ukim.finki.emt.lab.model.events.AuthorChangedEvent;
import mk.ukim.finki.emt.lab.model.view.BooksPerAuthorView;
import mk.ukim.finki.emt.lab.repository.views.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt.lab.service.aplication.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService, BooksPerAuthorViewRepository viewRepository, BooksPerAuthorViewRepository booksPerAuthorViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<DisplayAuthorDto> listAll() {
        return DisplayAuthorDto.from(authorService.listAll());
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {

        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> create(CreateAuthorDto createAuthorDto) {

        Optional<Country> country = countryService.findById(createAuthorDto.country());
        Author author1 = createAuthorDto.toAuthor(country.orElse(null));

        this.applicationEventPublisher.publishEvent(new AuthorCreatedEvent(author1));

        return authorService.create(author1).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> edit(Long id, UpdateAuthorDto updateAuthorDto) {
        Country country = countryService.findById(updateAuthorDto.country()).orElseThrow();
        Author author1 = updateAuthorDto.toAuthor(country);
        this.applicationEventPublisher.publishEvent(new AuthorChangedEvent(author1));

        return authorService.edit(id, author1).map(DisplayAuthorDto::from);

    }


    @Override
    public void delete(Long id) {
        Author author =authorService.findById(id).orElseThrow();
        authorService.delete(id);

        this.applicationEventPublisher.publishEvent(new AuthorDeletedEvent(author));
    }


    @Override
    public List<BooksPerAuthorView> findAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }

    @Override
    public BooksPerAuthorView findBooksPerAuthor(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }
}
