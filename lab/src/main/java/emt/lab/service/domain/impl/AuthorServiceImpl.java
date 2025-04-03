package emt.lab.service.domain.impl;


import emt.lab.model.domain.Author;
import emt.lab.model.domain.Country;
import emt.lab.repository.AuthorRepository;
import emt.lab.repository.CountryRepository;
import emt.lab.service.domain.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        Author author = findById(id).get();
        authorRepository.delete(author);
    }

    @Override
    public Optional<Author> save(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id).map(existingAuthor -> {
            if(author.getName() != null){
                existingAuthor.setName(author.getName());
            }
            if(author.getSurname() != null){
                existingAuthor.setSurname(author.getSurname());
            }
            if(author.getCountry() != null){
                existingAuthor.setCountry(author.getCountry());
            }
            return authorRepository.save(existingAuthor);
        });
    }
}
