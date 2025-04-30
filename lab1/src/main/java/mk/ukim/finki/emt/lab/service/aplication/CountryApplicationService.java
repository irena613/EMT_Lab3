package mk.ukim.finki.emt.lab.service.aplication;

import mk.ukim.finki.emt.lab.model.dto.create.CreateCountryDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateCountryDto;
import mk.ukim.finki.emt.lab.model.view.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> listAll();

    Optional<DisplayCountryDto> findById(Long id);

    // vo application layer treba da se pravat preku dto, specificno peku cfeateDto za create
    Optional<DisplayCountryDto> create(CreateCountryDto createCountry);

    Optional<DisplayCountryDto> edit(Long id, UpdateCountryDto updateCountryDto);

    void delete(Long id);

    List<AuthorsPerCountryView> findAllAuthorsPerCountry();
    AuthorsPerCountryView findAuthorsPerCountry(Long id);
    void refreshMaterializedView();

}
