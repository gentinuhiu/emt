package emt.lab.service.domain.impl;

import emt.lab.dto.display.DisplayBookDto;
import emt.lab.model.domain.Book;
import emt.lab.repository.AuthorRepository;
import emt.lab.repository.BookRepository;
import emt.lab.repository.CopyRepository;
import emt.lab.service.domain.BookService;
import emt.lab.service.domain.CopyService;
import org.springframework.stereotype.Service;
import emt.lab.model.domain.Copy;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
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
                    if (book.getTitle() != null) {
                        existingBook.setTitle(book.getTitle());
                    }
                    if (book.getCategory() != null) {
                        existingBook.setCategory(book.getCategory());
                    }
                    if (book.getAuthor() != null) {
                        existingBook.setAuthor(book.getAuthor());
                    }
//                    existingBook.setRented(bookDto.isRented());
                    return bookRepository.save(existingBook);
                });
    }

//    @Override
//    public Optional<Book> rentBook(Long id) {
//        return bookRepository.findById(id)
//                .map(existingBook -> {
////                    existingBook.setRented(bookDto.isRented());
//                    if(existingBook.getCopies() != null && !existingBook.getCopies().isEmpty()){
//                        if(existingBook.getAvailableCopies() > 0){
//                            Copy copy = existingBook.getNotRented();
//                            copy.setRented(true);
//                            copyService.save(copy);
//                        }
//                    }
//                    return bookRepository.save(existingBook);
//                });
//    }

    @Override
    public Optional<Book> save(Book book) {
        if (book.getAuthor() != null) {
            return Optional.of(bookRepository.save((new Book(book.getTitle(), book.getCategory(), book.getAuthor()))));
        }
        return Optional.empty();
    }

//    @Override
//    public Optional<Book> addCopy(Long id, Copy copy) {
//        return bookRepository.findById(id).map(book -> {
//            copyService.save(copy);
//            book.getCopies().add(copy);
//            return bookRepository.save(book);
//        });
//    }

//    @Override
//    public Optional<Book> removeCopy(Long id, Long copyId) {
//        Book book = bookRepository.findById(id).get();
//        book.getCopies().removeIf(c -> c.getId().equals(copyId));

//        copyService.deleteById(id);
//        return Optional.of(bookRepository.save(book));
//    }


//    public List<Book> findByAuthorAndBookName(String authorName, String bookName) {
//        return bookRepository.findByBookNameAndAuthorName(bookName, authorName);
//    }
}