package emt.lab.service.domain.impl;

import emt.lab.model.domain.AuthorsByCountry;
import emt.lab.repository.AuthorsByCountryRepository;
import emt.lab.service.domain.AuthorsByCountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsByCountryServiceImpl implements AuthorsByCountryService {
    private final AuthorsByCountryRepository authorsByCountryRepository;

    public AuthorsByCountryServiceImpl(AuthorsByCountryRepository authorsByCountryRepository) {
        this.authorsByCountryRepository = authorsByCountryRepository;
    }

    @Override
    public List<AuthorsByCountry> findAll() {
        return authorsByCountryRepository.findAll();
    }
}
