package emt.lab.web.rest;

import emt.lab.dto.create.CreateAuthorDto;
import emt.lab.dto.display.DisplayAuthorDto;
import emt.lab.dto.display.DisplayAuthorsByCountryDto;
import emt.lab.dto.display.DisplayBookDto;
import emt.lab.model.projection.AuthorNameProjection;
import emt.lab.service.application.AuthorApplicationService;
import emt.lab.service.application.AuthorsByCountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorsByCountryApplicationService authorsByCountryApplicationService;
    private final AuthorApplicationService authorApplicationService;

    public AuthorRestController(AuthorsByCountryApplicationService authorsByCountryApplicationService, AuthorApplicationService authorApplicationService) {
        this.authorsByCountryApplicationService = authorsByCountryApplicationService;
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    public List<DisplayAuthorDto> displayAuthors() {
        return authorApplicationService.findAll();
    }
    @GetMapping("/{id}")
    public DisplayAuthorDto displayAuthor(@PathVariable long id) {
        return authorApplicationService.findById(id).orElseThrow();
    }
    @PostMapping("/add")
    public DisplayAuthorDto addAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.save(createAuthorDto).orElseThrow();
    }
    @PutMapping("/edit/{id}")
    public DisplayAuthorDto editAuthor(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto){
        return authorApplicationService.update(id, createAuthorDto)
                .orElseThrow();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayAuthorDto> deleteAuthor(@PathVariable long id) {
        authorApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
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
