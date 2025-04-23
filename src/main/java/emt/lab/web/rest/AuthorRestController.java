package emt.lab.web.rest;

import emt.lab.dto.display.DisplayAuthorsByCountryDto;
import emt.lab.service.application.AuthorsByCountryApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorsByCountryApplicationService authorsByCountryApplicationService;

    public AuthorRestController(AuthorsByCountryApplicationService authorsByCountryApplicationService) {
        this.authorsByCountryApplicationService = authorsByCountryApplicationService;
    }

    @GetMapping("/by-country")
    public List<DisplayAuthorsByCountryDto> getAuthorsByCountry() {
        return authorsByCountryApplicationService.findAll();
    }
}
