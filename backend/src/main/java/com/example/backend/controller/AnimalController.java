package com.example.backend.controller;

import com.example.backend.dto.AnimalPatchDTO;
import com.example.backend.dto.AnimalRequestDTO;
import com.example.backend.dto.AnimalResponseDTO;
import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
import com.example.backend.exception.AnimalNotFoundException;
import com.example.backend.service.AnimalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> postAnimal (@RequestBody @Valid AnimalRequestDTO animalRequestDTO) {
        AnimalResponseDTO animalResponseDTO = animalService.postAnimal(animalRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalResponseDTO);
    }

    @GetMapping
    public List<AnimalResponseDTO> getAllAnimalsByFilters(@RequestParam(required = false) AnimalType type,
                                                         @RequestParam(required = false) Gender gender,
                                                         @RequestParam(required = false) AnimalSize size,
                                                         @RequestParam(required = false)
                                                      @Min(value = 0, message = "Age cannot be negative")
                                                      @Max(value = 50, message = "Age is too high") Integer minAge,
                                                         @RequestParam(required = false)
                                                      @Min(value = 0, message = "Age cannot be negative")
                                                      @Max(value = 50, message = "Age is too high") Integer maxAge,
                                                         @RequestParam(required = false) AnimalStatus status) {
        return animalService.getAllAnimalsByFilters(type, gender, size, minAge, maxAge, status);
    }

    @GetMapping("/animal/{id}")
    public AnimalResponseDTO getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id)
                .orElseThrow(() -> new AnimalNotFoundException("Animal was not found: ", id));
    }

    @PutMapping("/{id}")
    public Optional<AnimalResponseDTO> putAnimal(@PathVariable Long id, @RequestBody @Valid AnimalRequestDTO animalRequest) {
        return animalService.putAnimal(id, animalRequest);
    }

    @PatchMapping("/{id}")
    public Optional<AnimalResponseDTO> patchAnimal(@PathVariable Long id, @RequestBody @Valid AnimalPatchDTO animalPatch) {
        return animalService.patchAnimal(id, animalPatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
