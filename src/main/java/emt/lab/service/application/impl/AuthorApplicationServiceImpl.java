package emt.lab.service.application.impl;

import emt.lab.dto.create.CreateAuthorDto;
import emt.lab.dto.display.DisplayAuthorDto;
import emt.lab.model.projection.AuthorNameProjection;
import emt.lab.service.application.AuthorApplicationService;
import emt.lab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    public AuthorApplicationServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto) {
        return authorService.update(id, createAuthorDto.toAuthor()).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto) {
        return authorService.save(createAuthorDto.toAuthor()).map(DisplayAuthorDto::from);
    }

    @Override
    public List<AuthorNameProjection> findAllAuthorNames() {
        return authorService.findAllAuthorNames();
    }
}
