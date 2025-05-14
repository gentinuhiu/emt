package emt.lab.web.rest;

import emt.lab.dto.display.DisplayCountryDto;
import emt.lab.service.application.CountryApplicationService;
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
}
