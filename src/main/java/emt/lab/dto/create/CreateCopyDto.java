package emt.lab.dto.create;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.Copy;
import emt.lab.model.enumeration.QUALITY;

public record CreateCopyDto(Long book, QUALITY quality, boolean rented) {
    public Copy toCopy(Book book){
        return new Copy(book, quality, rented);
    }
}
