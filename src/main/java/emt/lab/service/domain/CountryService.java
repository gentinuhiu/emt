package emt.lab.service.domain;

import emt.lab.model.domain.Country;
import emt.lab.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(Country country);
    void deleteById(Long id);
    Optional<Country> update(Long id, Country country);
}