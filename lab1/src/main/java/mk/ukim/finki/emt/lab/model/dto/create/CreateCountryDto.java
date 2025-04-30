package mk.ukim.finki.emt.lab.model.dto.create;

import mk.ukim.finki.emt.lab.model.domain.Country;

public record CreateCountryDto(String name, String continent) {

    public Country toCountry(){
        return new Country(name, continent);
    }
}
