package emt.lab.service.application.impl;

import emt.lab.dto.create.CreateAuthorDto;
import emt.lab.dto.display.DisplayAuthorDto;
import emt.lab.model.domain.Author;
import emt.lab.model.projection.AuthorNameProjection;
import emt.lab.service.application.AuthorApplicationService;
import emt.lab.service.domain.AuthorService;
import emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;
    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
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
        return authorService.update(id, createAuthorDto.toAuthor(
                countryService.findById(createAuthorDto.countryId()).orElseThrow()
        )).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto) {
        return authorService.save(createAuthorDto.toAuthor(
                countryService.findById(createAuthorDto.countryId()).orElseThrow()
        )).map(DisplayAuthorDto::from);
    }

    @Override
    public List<AuthorNameProjection> findAllAuthorNames() {
        return authorService.findAllAuthorNames();
    }

    @Override
    public DisplayAuthorDto saveByParameters(String name, String surname, Long countryId) {
        return DisplayAuthorDto.from(authorService
                .save(new Author(name, surname, countryService
                        .findById(countryId)
                        .orElseThrow())).orElseThrow());
    }
}
