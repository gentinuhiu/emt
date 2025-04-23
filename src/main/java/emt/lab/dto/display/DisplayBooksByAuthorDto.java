package emt.lab.dto.display;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.BooksByAuthor;
import emt.lab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBooksByAuthorDto(
        Long author,
        Long totalBooks
) {
    public static DisplayBooksByAuthorDto from(BooksByAuthor booksByAuthor) {
        return new DisplayBooksByAuthorDto(booksByAuthor.getAuthor(), booksByAuthor.getTotalBooks());
    }
    public static List<DisplayBooksByAuthorDto> from(List<BooksByAuthor> booksByAuthorList) {
        return booksByAuthorList.stream().map(DisplayBooksByAuthorDto::from).collect(Collectors.toList());
    }
}