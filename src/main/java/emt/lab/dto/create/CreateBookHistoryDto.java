package emt.lab.dto.create;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.Book;
import emt.lab.model.domain.BookHistory;
import emt.lab.model.enumeration.CATEGORY;

import java.time.LocalDateTime;

public record CreateBookHistoryDto(
        String title,
        CATEGORY category,
        Long author,
        Long book,
        LocalDateTime stamp
) {

    public BookHistory toBookHistory(Author author, Book book){
        return new BookHistory(title, category, author, book, stamp);
    }
}
