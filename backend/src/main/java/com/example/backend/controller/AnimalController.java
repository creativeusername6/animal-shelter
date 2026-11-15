package com.example.backend.controller;

import com.example.backend.dto.AnimalDTO;
import com.example.backend.entity.Animal;
import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
import com.example.backend.mapper.AnimalMapper;
import com.example.backend.service.AnimalService;
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
    private final AnimalMapper animalMapper;

    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
    }

    @PostMapping
    public ResponseEntity<Animal> postAnimal (@RequestBody Animal animal) {
        animalService.postAnimal(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(animal);
    }

    @GetMapping
    public List<AnimalDTO> getAllAnimalsByFilters(@RequestParam(required = false) AnimalType type,
                                                  @RequestParam(required = false) Gender gender,
                                                  @RequestParam(required = false) AnimalSize size,
                                                  @RequestParam(required = false)
                                                      @Min(value = 0, message = "Age cannot be negative")
                                                      @Max(value = 50, message = "Age is too high") Integer minAge,
                                                  @RequestParam(required = false)
                                                      @Min(value = 0, message = "Age cannot be negative")
                                                      @Max(value = 50, message = "Age is too high") Integer maxAge,
                                                  @RequestParam(required = false) AnimalStatus status) {
        return animalService.getAllAnimalsByFilters(type, gender, size, minAge, maxAge, status)
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @GetMapping("/animal/{id}")
    public Optional<AnimalDTO> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id)
                .map(animalMapper::mapAnimalToDto);
    }

    @PutMapping("/{id}")
    public Optional<Animal> putAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.putAnimal(id, animal);
    }

    @PatchMapping("/{id}")
    public Optional<Animal> patchAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.patchAnimal(id, animal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
