package emt.lab.web.rest;

import emt.lab.dto.create.CreateBookDto;
import emt.lab.dto.create.CreateCopyDto;
import emt.lab.dto.display.DisplayBookDto;
import emt.lab.service.application.AuthorApplicationService;
import emt.lab.service.application.BookApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Operations related to books in the library")
public class BookRestController {

    private final BookApplicationService bookApplicationService;
    private final AuthorApplicationService authorApplicationService;

    public BookRestController(BookApplicationService bookApplicationService,
                              AuthorApplicationService authorApplicationService) {
        this.bookApplicationService = bookApplicationService;
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Get all books",
            description = "Retrieves a list of all books available in the library system."
    )
    public List<DisplayBookDto> listAll(Model model) {
        return bookApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get book by ID",
            description = "Retrieves detailed information about a specific book using its unique ID."
    )
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/add")
    @Operation(
            summary = "Add a new book",
            description = "Creates and adds a new book to the system. Only librarians can perform this action."
    )
    public ResponseEntity<DisplayBookDto> add(@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.save(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/edit/{id}")
    @Operation(
            summary = "Edit an existing book",
            description = "Updates an existing book's information using its ID. Only librarians can perform this action."
    )
    public ResponseEntity<DisplayBookDto> edit(@PathVariable Long id, @RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasRole('LIBRARIAN')")
//    @PutMapping("/add-copy/{id}")
//    @Operation(
//            summary = "Add a copy to a book",
//            description = "Adds a new physical copy to an existing book by book ID. Only librarians can perform this action."
//    )
//    public ResponseEntity<DisplayBookDto> addCopy(@PathVariable Long id, @RequestBody CreateCopyDto createCopyDto) {
//        return bookApplicationService.addCopy(id, createCopyDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete a book",
            description = "Deletes a book from the system using its ID. Only librarians can perform this action."
    )
    public ResponseEntity<DisplayBookDto> delete(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
//    @PreAuthorize("hasRole('LIBRARIAN')")
//    @PutMapping("/rent/{id}")
//    @Operation(
//            summary = "Rent a book",
//            description = "Marks a book as rented using its ID. Only librarians can perform this action."
//    )
//    public ResponseEntity<DisplayBookDto> rent(@PathVariable Long id) {
//        return bookApplicationService.rentBook(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
