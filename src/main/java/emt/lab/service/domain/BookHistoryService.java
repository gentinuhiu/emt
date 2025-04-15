package emt.lab.service.domain;

import emt.lab.dto.display.DisplayBookHistoryDto;
import emt.lab.model.domain.Book;
import emt.lab.model.domain.BookHistory;

import java.util.List;
import java.util.Optional;

public interface BookHistoryService {
    List<BookHistory> findAllById(Long id);
//    Optional<Book> findById(Long id);
}
