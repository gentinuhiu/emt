package emt.lab.web.rest;

import emt.lab.dto.create.CreateCopyDto;
import emt.lab.dto.display.DisplayCopyDto;
import emt.lab.service.application.CopyApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copies")
@Tag(name = "Copy API", description = "Operations related to book copies")
public class CopyRestController {

    private final CopyApplicationService copyApplicationService;

    public CopyRestController(CopyApplicationService copyApplicationService) {
        this.copyApplicationService = copyApplicationService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Get all book copies",
            description = "Retrieves a list of all copies available in the library system."
    )
    public List<DisplayCopyDto> listAll(Model model) {
        return copyApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get copy by ID",
            description = "Retrieves detailed information about a specific copy using its unique ID."
    )
    public ResponseEntity<DisplayCopyDto> findById(@PathVariable Long id) {
        return copyApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/add")
    @Operation(
            summary = "Add new copy",
            description = "Creates a new copy and associates it with an existing book. Only librarians can perform this operation."
    )
    public ResponseEntity<DisplayCopyDto> add(@RequestBody CreateCopyDto createCopyDto) {
        return copyApplicationService.save(createCopyDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/edit/{id}")
    @Operation(
            summary = "Edit existing copy",
            description = "Updates the details of an existing copy by its ID. Only librarians can perform this operation."
    )
    public ResponseEntity<DisplayCopyDto> edit(@PathVariable Long id, @RequestBody CreateCopyDto createCopyDto) {
        return copyApplicationService.update(id, createCopyDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete copy",
            description = "Deletes a copy from the system by its ID. Only librarians can perform this operation."
    )
    public ResponseEntity<DisplayCopyDto> delete(@PathVariable Long id) {
        if (copyApplicationService.findById(id).isPresent()) {
            copyApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
