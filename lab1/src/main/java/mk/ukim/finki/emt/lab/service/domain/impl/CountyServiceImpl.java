package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountyServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountyServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.ofNullable(this.countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new));
    }

    @Override
    public Optional<Country> create(Country country) {
        return Optional.of(countryRepository.save(new Country(country.getName(), country.getContinent())));
    }

    @Override
    public Optional<Country> edit(Long id, Country country) {
        return countryRepository.findById(id)
                .map(existingCountry ->{
                    if (country.getName() != null){
                        existingCountry.setName(country.getName());
                    }
                    if (country.getContinent() != null){
                        existingCountry.setContinent(country.getContinent());
                    }
                    return countryRepository.save(existingCountry);
                });
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name, continent);
        return this.countryRepository.save(country);
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }


//    @Override
//    public Optional<Country> create(CountryDto country) {
//        if (country.getName()!=null && country.getContinent()!=null) {
//            Country newCountry = new Country(country.getName(), country.getContinent());
//            return Optional.of(this.countryRepository.save(newCountry));
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Country> edit(Long id, CountryDto country) {
//
//        Country oldCountry = this.countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
//        if (country.getName()!=null){
//            oldCountry.setName(country.getName());
//        }
//        if (country.getContinent()!=null){
//            oldCountry.setContinent(country.getContinent());
//        }
//
//        return Optional.of(countryRepository.save(oldCountry));
//    }

    @Override
    public void delete(Long id) {
        this.countryRepository.deleteById(id);
    }
}
