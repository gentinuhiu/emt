package emt.lab.config;

import emt.lab.model.domain.*;
import emt.lab.model.enumeration.CATEGORY;
import emt.lab.model.enumeration.QUALITY;
import emt.lab.model.enumeration.ROLE;
import emt.lab.repository.AuthorRepository;
import emt.lab.repository.BookRepository;
import emt.lab.repository.CopyRepository;
import emt.lab.repository.CountryRepository;
import emt.lab.service.domain.UserService;
import emt.lab.service.domain.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CopyRepository copyRepository;
    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(CountryRepository countryRepository, BookRepository bookRepository,
                           AuthorRepository authorRepository, CopyRepository copyRepository, UserService userService,
                           JdbcTemplate jdbcTemplate) {
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.copyRepository = copyRepository;
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        Country country1 = new Country("England", "Europe");
        Country country2 = new Country("US", "North America");
        Country country3 = new Country("China", "Asia");
        countryRepository.saveAll(List.of(country1, country2, country3));

        Author author1 = new Author("JK", "Rowlings", country1);
        Author author2 = new Author ("Elon", "Musk", country2);
        Author author3 = new Author("Chao", "Ming", country3);
        authorRepository.saveAll(List.of(author1, author2, author3));

        Book book1 = new Book("Harry Potter", CATEGORY.FANTASY, author1);
        Book book2 = new Book("Tesla Shares", CATEGORY.BIOGRAPHY, author2);
        Book book3 = new Book("Mao Zedong", CATEGORY.HISTORY, author3);
        bookRepository.saveAll(List.of(book1, book2, book3));

        Copy copy1 = new Copy(book1, QUALITY.BAD, false);
        Copy copy2 = new Copy(book1, QUALITY.NORMAL, false);
        Copy copy3 = new Copy(book1, QUALITY.GOOD, false);

        Copy copy4 = new Copy(book2, QUALITY.BAD, false);
        Copy copy5 = new Copy(book2, QUALITY.GOOD, false);

        Copy copy6 = new Copy(book3, QUALITY.NORMAL, false);
        Copy copy7 = new Copy(book3, QUALITY.GOOD, false);
        copyRepository.saveAll(List.of(copy1, copy2, copy3, copy4, copy5, copy6, copy7));

        userService.register("string", "string", "string", "string", "string", ROLE.LIBRARIAN);
        userService.register("string2", "string2", "string2", "string2", "string2", ROLE.LIBRARIAN);

        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW books_by_author");
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW authors_by_country");
    }
}