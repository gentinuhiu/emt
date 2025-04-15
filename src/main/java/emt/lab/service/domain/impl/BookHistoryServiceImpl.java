package emt.lab.service.domain.impl;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.BookHistory;
import emt.lab.repository.BookHistoryRepository;
import emt.lab.service.domain.BookHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookHistoryServiceImpl implements BookHistoryService {
    private final BookHistoryRepository bookHistoryRepository;

    public BookHistoryServiceImpl(BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }

    @Override
    public List<BookHistory> findAllById(Long id) {
        return bookHistoryRepository.findAllByBookId(id);
    }
}
