package emt.lab.dto.display;

import emt.lab.model.domain.AuthorsByCountry;
import emt.lab.model.domain.BooksByAuthor;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorsByCountryDto(
        Long countrId,
        String countryName,
        Long totalAuthors
) {
    public static DisplayAuthorsByCountryDto from(AuthorsByCountry authorsByCountry) {
        return new DisplayAuthorsByCountryDto(authorsByCountry.getCountryId(), authorsByCountry.getCountryName(), authorsByCountry.getTotalAuthors());
    }
    public static List<DisplayAuthorsByCountryDto> from(List<AuthorsByCountry> authorsByCountryList) {
        return authorsByCountryList.stream().map(DisplayAuthorsByCountryDto::from).collect(Collectors.toList());
    }
}