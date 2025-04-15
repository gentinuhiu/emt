package emt.lab.service.application;

import emt.lab.dto.create.CreateCountryDto;
import emt.lab.dto.display.DisplayCountryDto;
import emt.lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);
    void deleteById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
}
