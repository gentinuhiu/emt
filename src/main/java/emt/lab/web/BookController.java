package emt.lab.web;

import emt.lab.model.domain.Book;
import emt.lab.service.domain.AuthorService;
import emt.lab.service.domain.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String listAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "home";
    }

}
