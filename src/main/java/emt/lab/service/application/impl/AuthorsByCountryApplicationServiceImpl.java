package emt.lab.service.application.impl;

import emt.lab.dto.display.DisplayAuthorsByCountryDto;
import emt.lab.service.application.AuthorApplicationService;
import emt.lab.service.application.AuthorsByCountryApplicationService;
import emt.lab.service.domain.AuthorsByCountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsByCountryApplicationServiceImpl implements AuthorsByCountryApplicationService {
    private final AuthorsByCountryService authorsByCountryService;

    public AuthorsByCountryApplicationServiceImpl(AuthorsByCountryService authorsByCountryService) {
        this.authorsByCountryService = authorsByCountryService;
    }

    @Override
    public List<DisplayAuthorsByCountryDto> findAll() {
        return DisplayAuthorsByCountryDto.from(authorsByCountryService.findAll());
    }
}
