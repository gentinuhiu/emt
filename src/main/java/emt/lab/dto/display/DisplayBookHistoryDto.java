package emt.lab.dto.display;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.Book;
import emt.lab.model.domain.BookHistory;
import emt.lab.model.enumeration.CATEGORY;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookHistoryDto(
        Long id,
        String title,
        CATEGORY category,
        Author author,
        Long book,
        LocalDateTime stamp
) {
    public static DisplayBookHistoryDto from(BookHistory book) {
        return new DisplayBookHistoryDto(book.getId(), book.getTitle(), book.getCategory(), book.getAuthor(), book.getBook().getId(), book.getStamp());
    }
    public static List<DisplayBookHistoryDto> from(List<BookHistory> books) {
        return books.stream().map(DisplayBookHistoryDto::from).collect(Collectors.toList());
    }
}
