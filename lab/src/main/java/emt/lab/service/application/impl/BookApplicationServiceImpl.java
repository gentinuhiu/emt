package emt.lab.service.application.impl;

import emt.lab.dto.create.CreateBookDto;
import emt.lab.dto.create.CreateCopyDto;
import emt.lab.dto.display.DisplayBookDto;
import emt.lab.dto.display.DisplayCopyDto;
import emt.lab.model.domain.Author;
import emt.lab.model.domain.Copy;
import emt.lab.service.application.AuthorApplicationService;
import emt.lab.service.application.BookApplicationService;
import emt.lab.service.domain.AuthorService;
import emt.lab.service.domain.BookService;
import emt.lab.service.domain.CopyService;
import emt.lab.service.domain.impl.CopyServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.author()).get();
        return bookService.update(id, createBookDto.toBook(author)).map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

//    @Override
//    public Optional<DisplayBookDto> rentBook(Long id) {
//        return bookService.rentBook(id).map(DisplayBookDto::from);
//    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.author()).get();
        return bookService.save(createBookDto.toBook(author)).map(DisplayBookDto::from);
    }

//    @Override
//    public Optional<DisplayBookDto> addCopy(Long id, CreateCopyDto createCopyDto) {
//        return bookService.addCopy(id, createCopyDto.toCopy()).map(DisplayBookDto::from);
//    }

//    @Override
//    public Optional<DisplayBookDto> removeCopy(Long id, Long copyId) {
//        return bookService.removeCopy(id, copyId).map(DisplayBookDto::from);
//    }
}
