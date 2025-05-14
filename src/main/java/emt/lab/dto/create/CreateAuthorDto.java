package emt.lab.dto.create;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.Country;

public record CreateAuthorDto(
        String name,
        String surname,
        Long countryId
) {
    public Author toAuthor(Country country){
        return new Author(name, surname, country);
    }
}
