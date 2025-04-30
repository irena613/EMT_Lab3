package mk.ukim.finki.emt.lab.service.aplication.impl;


import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.dto.create.CreateBookDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayBookDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateBookDto;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.service.aplication.BookApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> listAll() {
        return bookService.listAll().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        //go pravam map in this way za da ne moram tuka direktno da go mapiram so site
        // detali tuku samoda povikam funkcija koja e predefinirana i da se mapira
        // tamu a mene da mi go vrati vekje mapiraniot dto objekt?? vajda
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> create(CreateBookDto createBook) {
        Optional<Author> author = authorService.findById(createBook.author());
        if (author.isPresent()) {
            return bookService.create(createBook.toBook(author.orElse(null))).map(DisplayBookDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDto> edit(Long id, UpdateBookDto updateBookDto) {
        Optional<Author> author = authorService.findById(updateBookDto.author());
        return bookService.edit(id,
                        updateBookDto.toBook(author.orElse(null)))
                        .map(DisplayBookDto::from);

    }

    @Override
    public Book delete(Long id) {
        return bookService.delete(id);
    }

    @Override
    public Optional<DisplayBookDto> isRented(Long id) {
        //idk how but it works
        Book book = bookService.findById(id).orElseThrow(BookNotFoundException::new);
        return bookService.isRented(book)
                .map(DisplayBookDto::from);
//        return Optional.empty();
    }
}
