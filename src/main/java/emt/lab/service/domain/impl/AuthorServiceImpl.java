package emt.lab.service.domain.impl;

import emt.lab.model.domain.Author;
import emt.lab.model.projection.AuthorNameProjection;
import emt.lab.repository.AuthorRepository;
import emt.lab.service.domain.AuthorService;
import emt.lab.event.AuthorChangedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher publisher;


    public AuthorServiceImpl(AuthorRepository authorRepository, ApplicationEventPublisher publisher) {
        this.authorRepository = authorRepository;
        this.publisher = publisher;
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
        publisher.publishEvent(new AuthorChangedEvent(author));
    }

    @Override
    public Optional<Author> save(Author author) {
        authorRepository.save(author);
        publisher.publishEvent(new AuthorChangedEvent(author));
        return Optional.of(author);
    }

    @Override
    public List<AuthorNameProjection> findAllAuthorNames() {
        return authorRepository.findAllAuthorNames();
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
            authorRepository.save(existingAuthor);
            publisher.publishEvent(new AuthorChangedEvent(existingAuthor));
            return existingAuthor;
        });
    }
}
