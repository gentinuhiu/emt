package emt.lab.service.domain;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.BookHistory;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
//    List<BookHistory> findById(Long id);
    Optional<Book> findById(long id);
    Optional<Book> update(Long id, Book book);
//    Optional<Book> rentBook(Long id);
    void deleteById(Long id);
    Optional<Book> save(Book book);
//    Optional<Book> addCopy(Long id, Copy copy);
//    Optional<Book> removeCopy(Long id, Long copyId);
}