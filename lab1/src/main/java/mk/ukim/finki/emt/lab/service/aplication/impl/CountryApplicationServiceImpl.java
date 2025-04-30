package mk.ukim.finki.emt.lab.service.aplication.impl;

import mk.ukim.finki.emt.lab.model.dto.create.CreateCountryDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateCountryDto;
import mk.ukim.finki.emt.lab.model.view.AuthorsPerCountryView;
import mk.ukim.finki.emt.lab.repository.views.AuthorsPerCountryViewRepository;
import mk.ukim.finki.emt.lab.service.aplication.CountryApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository viewRepository;

    public CountryApplicationServiceImpl(CountryService countryService, AuthorsPerCountryViewRepository viewRepository) {
        this.countryService = countryService;
        this.viewRepository = viewRepository;
    }

    @Override
    public List<DisplayCountryDto> listAll() {
        return DisplayCountryDto.from(countryService.listAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> create(CreateCountryDto createCountry) {
        return countryService.create(createCountry.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> edit(Long id, UpdateCountryDto updateCountryDto) {
        return countryService.edit(id, updateCountryDto.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }

    @Override
    public List<AuthorsPerCountryView> findAllAuthorsPerCountry() {
        return viewRepository.findAll();
    }

    @Override
    public AuthorsPerCountryView findAuthorsPerCountry(Long id) {
        return viewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        viewRepository.refreshMaterializedView();
    }
}

