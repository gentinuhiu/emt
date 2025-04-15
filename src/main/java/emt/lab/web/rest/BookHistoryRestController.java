package emt.lab.web.rest;

import emt.lab.dto.display.DisplayBookHistoryDto;
import emt.lab.service.application.BookHistoryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
//@Tag(name = "Copy API", description = "Operations related to book copies")
public class BookHistoryRestController {

    private final BookHistoryApplicationService bookHistoryApplicationService;

    public BookHistoryRestController(BookHistoryApplicationService bookHistoryApplicationService) {
        this.bookHistoryApplicationService = bookHistoryApplicationService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get all book copies",
            description = "Retrieves a list of all copies available in the library system."
    )
    public List<DisplayBookHistoryDto> listAll(@PathVariable Long id) {
        return bookHistoryApplicationService.findAllById(id);
    }
}
