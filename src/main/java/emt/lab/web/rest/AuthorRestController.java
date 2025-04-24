package emt.lab.web.rest;

import emt.lab.dto.display.DisplayAuthorsByCountryDto;
import emt.lab.model.projection.AuthorNameProjection;
import emt.lab.service.application.AuthorApplicationService;
import emt.lab.service.application.AuthorsByCountryApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorsByCountryApplicationService authorsByCountryApplicationService;
    private final AuthorApplicationService authorApplicationService;

    public AuthorRestController(AuthorsByCountryApplicationService authorsByCountryApplicationService, AuthorApplicationService authorApplicationService) {
        this.authorsByCountryApplicationService = authorsByCountryApplicationService;
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping("/by-country")
    public List<DisplayAuthorsByCountryDto> getAuthorsByCountry() {
        return authorsByCountryApplicationService.findAll();
    }

    @GetMapping("/names")
    public List<AuthorNameProjection> getAuthorNames() {
        return authorApplicationService.findAllAuthorNames();
    }
}
