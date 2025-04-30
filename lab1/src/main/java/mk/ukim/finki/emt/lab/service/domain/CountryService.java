package mk.ukim.finki.emt.lab.service.domain;


import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();

    Optional<Country> findById(Long id);

    Country create(String name , String continent);
    //vo domain service layer treba da se pravat perku konstruktorite na modelite
    Optional<Country> create(Country country);

    Optional<Country> edit(Long id, Country country);

    void delete(Long id);
}
