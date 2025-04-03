package emt.lab.dto.create;

import emt.lab.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry(){
        return new Country(name, continent);
    }
}
