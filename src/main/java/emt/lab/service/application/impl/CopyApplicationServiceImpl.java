package emt.lab.service.application.impl;

import emt.lab.dto.create.CreateCopyDto;
import emt.lab.dto.display.DisplayCopyDto;
import emt.lab.model.domain.Book;
import emt.lab.service.application.CopyApplicationService;
import emt.lab.service.domain.BookService;
import emt.lab.service.domain.CopyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopyApplicationServiceImpl implements CopyApplicationService {
    private final CopyService copyService;
    private final BookService bookService;

    public CopyApplicationServiceImpl(CopyService copyService, BookService bookService) {
        this.copyService = copyService;
        this.bookService = bookService;
    }

    @Override
    public List<DisplayCopyDto> findAll() {
        return DisplayCopyDto.from(copyService.findAll());
    }

    @Override
    public Optional<DisplayCopyDto> findById(Long id) {
        return copyService.findById(id).map(DisplayCopyDto::from);
    }

    @Override
    public Optional<DisplayCopyDto> update(Long id, CreateCopyDto copy) {
        Book book = bookService.findById(copy.book()).get();
        return copyService.update(id, copy.toCopy(book)).map(DisplayCopyDto::from);
    }

    @Override
    public Optional<DisplayCopyDto> save(CreateCopyDto copy) {
        Book book = bookService.findById(copy.book()).get();
        return copyService.save(copy.toCopy(book)).map(DisplayCopyDto::from);
    }

    @Override
    public void deleteById(Long id) {
        copyService.deleteById(id);
    }
}
