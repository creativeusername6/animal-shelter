package com.example.backend.controller;

import com.example.backend.dto.AnimalDTO;
import com.example.backend.enums.AnimalType;
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

    @GetMapping
    public List<AnimalDTO> getAll() {
        return animalService.getAll()
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }

    @GetMapping("/animal/{id}")
    public Optional<AnimalDTO> getById(@PathVariable Long id) {
        return animalService.getById(id)
                .map(animalMapper::mapAnimalToDto);
    }

    @GetMapping("/type/{type}")
    public List<AnimalDTO> getAllByType(@PathVariable AnimalType type) {
        return animalService.getAllByType(type)
                .stream()
                .map(animalMapper::mapAnimalToDto)
                .toList();
    }
}
