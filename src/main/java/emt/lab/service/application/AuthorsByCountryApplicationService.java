package emt.lab.service.application;

import emt.lab.dto.display.DisplayAuthorsByCountryDto;

import java.util.List;

public interface AuthorsByCountryApplicationService {
    List<DisplayAuthorsByCountryDto> findAll();
}
