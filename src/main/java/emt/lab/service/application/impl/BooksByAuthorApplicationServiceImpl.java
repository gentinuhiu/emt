package emt.lab.service.application.impl;

import emt.lab.dto.display.DisplayBooksByAuthorDto;
import emt.lab.service.application.BooksByAuthorApplicationService;
import emt.lab.service.domain.BooksByAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksByAuthorApplicationServiceImpl implements BooksByAuthorApplicationService {
    private final BooksByAuthorService booksByAuthorService;
    public BooksByAuthorApplicationServiceImpl(BooksByAuthorService booksByAuthorService) {
        this.booksByAuthorService = booksByAuthorService;
    }
    @Override
    public List<DisplayBooksByAuthorDto> findAll() {
        return DisplayBooksByAuthorDto.from(booksByAuthorService.findAll());
    }
}
