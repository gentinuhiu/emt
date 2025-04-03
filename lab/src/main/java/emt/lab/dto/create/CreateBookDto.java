package emt.lab.dto.create;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.Book;
import emt.lab.model.domain.Copy;
import emt.lab.model.enumeration.CATEGORY;

import java.util.List;

public record CreateBookDto(
        String title,
        CATEGORY category,
        Long author
) {

    public Book toBook(Author author){
        return new Book(title, category, author);
    }
}
