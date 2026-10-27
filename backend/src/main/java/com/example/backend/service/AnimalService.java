package com.example.backend.service;

import com.example.backend.entity.Animal;
import com.example.backend.enums.AnimalType;
import com.example.backend.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getById(Long id) {
        return this.animalRepository.findById(id);
    }

    public List<Animal> getAllByType(AnimalType type) {
        return animalRepository.findAll()
                .stream()
                .filter(a -> a.getType() == type)
                .toList();
    }
}
