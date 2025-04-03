package emt.lab.dto.display;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.Book;
import emt.lab.model.domain.Copy;
import emt.lab.model.enumeration.CATEGORY;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        Long id,
        String title,
        CATEGORY category,
        Author author
) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(book.getId(), book.getTitle(), book.getCategory(), book.getAuthor());
    }
    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }
}
