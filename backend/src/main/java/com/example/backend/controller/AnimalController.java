package com.example.backend.controller;

import com.example.backend.dto.AnimalDTO;
import com.example.backend.entity.Animal;
import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
import com.example.backend.mapper.AnimalMapper;
import com.example.backend.service.AnimalService;
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
    public void postAnimal (@RequestBody Animal animal) {
        animalService.postAnimal(animal);
    }

    @GetMapping
    public List<AnimalDTO> getAllAnimals() {
        return animalService.getAllAnimals()
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @GetMapping("/animal/{id}")
    public Optional<AnimalDTO> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id)
                .map(animalMapper::mapAnimalToDto);
    }

    @GetMapping("/type/{type}")
    public List<AnimalDTO> getAllAnimalsByType(@PathVariable AnimalType type) {
        return animalService.getAllAnimalsByType(type)
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @GetMapping("/gender/{gender}")
    public List<AnimalDTO> getAllAnimalsByGender(@PathVariable Gender gender) {
        return animalService.getAllAnimalsByGender(gender)
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @GetMapping("/size/{size}")
    public List<AnimalDTO> getAllAnimalsBySize(@PathVariable AnimalSize size) {
        return animalService.getAllAnimalsBySize(size)
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @GetMapping("/age/{lower}/{upper}")
    public List<AnimalDTO> getAllAnimalsByAgeRange(@PathVariable Integer lower, @PathVariable Integer upper) {
        return animalService.getAllAnimalsByAgeRange(lower, upper)
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @GetMapping("/status/{status}")
    public List<AnimalDTO> getAllAnimalsByStatus(@PathVariable AnimalStatus status) {
        return animalService.getAllAnimalsByStatus(status)
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @PutMapping("/updateall/{id}")
    public Optional<Animal> putAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.putAnimal(id, animal);
    }

    @PatchMapping("/update/{id}")
    public Optional<Animal> patchAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.patchAnimal(id, animal);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}
