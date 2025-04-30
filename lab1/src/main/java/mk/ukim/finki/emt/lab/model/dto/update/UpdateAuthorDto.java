package mk.ukim.finki.emt.lab.model.dto.update;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateAuthorDto(String name, String surname, Long country) {
    public static UpdateAuthorDto from(Author author){
        return new UpdateAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<UpdateAuthorDto> from(List<Author> authors){
        return authors.stream().map(UpdateAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country){
        return new Author(name, surname, country);
    }
}
