package emt.lab.service.domain.impl;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.BookHistory;
import emt.lab.repository.BookHistoryRepository;
import emt.lab.repository.BookRepository;
import emt.lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookHistoryRepository bookHistoryRepository;

    public BookServiceImpl(BookRepository bookRepository, BookHistoryRepository bookHistoryRepository) {
        this.bookRepository = bookRepository;
        this.bookHistoryRepository = bookHistoryRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    BookHistory bookHistory = new BookHistory(existingBook.getTitle(), existingBook.getCategory(),
                            existingBook.getAuthor(), existingBook, LocalDateTime.now());

                    if (book.getTitle() != null) {
                        existingBook.setTitle(book.getTitle());
                    }
                    if (book.getCategory() != null) {
                        existingBook.setCategory(book.getCategory());
                    }
                    if (book.getAuthor() != null) {
                        existingBook.setAuthor(book.getAuthor());
                    }
                    bookHistoryRepository.save(bookHistory);
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Optional<Book> save(Book book) {
        if (book.getAuthor() != null) {
            return Optional.of(bookRepository.save((new Book(book.getTitle(), book.getCategory(), book.getAuthor()))));
        }
        return Optional.empty();
    }
}