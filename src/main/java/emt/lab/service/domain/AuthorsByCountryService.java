package emt.lab.service.domain;

import emt.lab.model.domain.AuthorsByCountry;

import java.util.List;

public interface AuthorsByCountryService {
    List<AuthorsByCountry> findAll();
}
