package mk.ukim.finki.emt.lab.model.dto.update;

import mk.ukim.finki.emt.lab.model.domain.Country;

public record UpdateCountryDto(String name, String continent) {

    public Country toCountry(){
        return new Country(name, continent);
    }
}
