package emt.lab.service.domain;

import emt.lab.model.domain.BooksByAuthor;

import java.util.List;

public interface BooksByAuthorService {
    List<BooksByAuthor> findAll();
}
