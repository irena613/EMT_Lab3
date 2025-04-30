package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }


    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new));
    }

    @Override
    public Author create(String name, String surname, Long id) {
        Optional<Country> country = this.countryService.findById(id);
        Author newAuthor = new Author(name, surname, country.get());
        return this.authorRepository.save(newAuthor);
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> create(Author author) {
        Optional<Country> country = this.countryService.findById(author.getCountry().getId());
        Author newAuthor = new Author(author.getName(), author.getSurname(), country.get());

        return Optional.of(this.authorRepository.save(newAuthor));
    }

    @Override
    public Optional<Author> edit(Long id, Author author) {
        Author oldAuthor = this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
        Optional<Country> country = this.countryService.findById(author.getCountry().getId());
        if (author.getName()!=null){
            oldAuthor.setName(author.getName());
        }
        if (author.getSurname()!=null){
            oldAuthor.setSurname(author.getSurname());
        }
        if (author.getCountry()!=null){
            oldAuthor.setCountry(country.get());
        }

        return Optional.of(authorRepository.save(oldAuthor));
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }
}


