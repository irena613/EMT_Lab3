package mk.ukim.finki.emt.lab.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.model.dto.create.CreateCountryDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.dto.update.UpdateCountryDto;
import mk.ukim.finki.emt.lab.service.aplication.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country Controller", description = "Operations related to country management")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping()
    @Operation(summary = "Get all countries", description = "Retrieves a list of all countries.")
    public List<DisplayCountryDto> findAll() {
        return countryApplicationService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get country by ID", description = "Retrieves a country by its unique identifier.")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new country", description = "Creates a new country and returns the created entity.")
    public ResponseEntity<DisplayCountryDto> create(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.create(createCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing country", description = "Updates an existing country's information.")
    public ResponseEntity<DisplayCountryDto> edit(@PathVariable Long id, @RequestBody UpdateCountryDto updateCountryDto) {
        return countryApplicationService.edit(id, updateCountryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a country", description = "Deletes a country by its unique identifier.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
