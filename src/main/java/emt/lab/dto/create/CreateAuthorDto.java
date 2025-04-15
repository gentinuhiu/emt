package emt.lab.dto.create;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.Country;

public record CreateAuthorDto(
        String name,
        String surname,
        Country country
) {
    public Author toAuthor(){
        return new Author(name, surname, country);
    }
}
