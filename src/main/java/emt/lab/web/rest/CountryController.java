package emt.lab.web.rest;

import emt.lab.dto.create.CreateCountryDto;
import emt.lab.dto.display.DisplayCountryDto;
import emt.lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin("http://localhost:5173")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    public List<DisplayCountryDto> getAllCountries(){
        return countryApplicationService.findAll();
    }
    @GetMapping("/{id}")
    public DisplayCountryDto getCountryById(@PathVariable Long id){
        return countryApplicationService.findById(id).orElseThrow();
    }
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> addCountry(@RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.save(createCountryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> editCountry(@PathVariable Long id, @RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.update(id, createCountryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCountryById(@PathVariable Long id){
        countryApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
