package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.model.dto.create.CreateAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateAuthorDto;
import mk.ukim.finki.emt.lab.service.aplication.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.aplication.CountryApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author Controller", description = "Operations related to authors management")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;
    private final CountryApplicationService countryApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService, CountryApplicationService countryApplicationService) {
        this.authorApplicationService = authorApplicationService;
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping()
    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors.")
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID", description = "Retrieves an author by their unique identifier.")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new author", description = "Creates a new author and returns the created entity.")
    public ResponseEntity<DisplayAuthorDto> create(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.create(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing author", description = "Updates an existing author's information.")
    public ResponseEntity<DisplayAuthorDto> edit(@PathVariable Long id, @RequestBody UpdateAuthorDto updateAuthorDto) {
        return authorApplicationService.edit(id, updateAuthorDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author by their unique identifier.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/per-country")
    @Operation(summary = "List number of books per author for every author")
    public ResponseEntity<?> findAllNumberOfAuthorsPerCountry() {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAllAuthorsPerCountry());
    }

    @GetMapping("/per-country/{id}")
    @Operation(summary = "List number of books per author for a given author")
    public ResponseEntity<?> findNumberOfAuthorsPerCountry(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAuthorsPerCountry(id));
    }
}
