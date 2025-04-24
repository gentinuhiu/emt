package emt.lab.service.application;

import emt.lab.dto.create.CreateAuthorDto;
import emt.lab.dto.display.DisplayAuthorDto;
import emt.lab.model.domain.Author;
import emt.lab.model.projection.AuthorNameProjection;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();
    Optional<DisplayAuthorDto> findById(Long id);
    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto);
    void deleteById(Long id);
    Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto);
    List<AuthorNameProjection> findAllAuthorNames();
}
