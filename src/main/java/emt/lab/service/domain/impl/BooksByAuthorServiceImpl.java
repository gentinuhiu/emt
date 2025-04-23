package emt.lab.service.domain.impl;

import emt.lab.model.domain.BooksByAuthor;
import emt.lab.repository.BooksByAuthorRepository;
import emt.lab.service.domain.BooksByAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksByAuthorServiceImpl implements BooksByAuthorService {
    private BooksByAuthorRepository booksByAuthorRepository;
    public BooksByAuthorServiceImpl(BooksByAuthorRepository booksByAuthorRepository) {
        this.booksByAuthorRepository = booksByAuthorRepository;
    }
    @Override
    public List<BooksByAuthor> findAll() {
        return booksByAuthorRepository.findAll();
    }
}
