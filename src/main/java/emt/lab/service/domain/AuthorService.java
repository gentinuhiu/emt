package emt.lab.service.domain;


import emt.lab.model.domain.Country;
import emt.lab.repository.AuthorRepository;
import emt.lab.repository.CountryRepository;
import org.springframework.stereotype.Service;
import emt.lab.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> update(Long id, Author author);
    void deleteById(Long id);
    Optional<Author> save(Author author);
}