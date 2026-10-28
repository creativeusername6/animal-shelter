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

    public void postAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(Long id) {
        return this.animalRepository.findById(id);
    }

    public List<Animal> getAllAnimalsByType(AnimalType type) {
        return animalRepository.findAll()
                .stream()
                .filter(a -> a.getType() == type)
                .toList();
    }

    public Optional<Animal> putAnimal(Long id, Animal animal) {
        return animalRepository.findById(id)
                .map(a -> {
                    a.setName(animal.getName());
                    a.setType(animal.getType());
                    a.setBreed(animal.getBreed());
                    a.setAge(animal.getAge());
                    a.setGender(animal.getGender());
                    a.setSize(animal.getSize());
                    a.setDescription(animal.getDescription());
                    a.setVaccinated(animal.getVaccinated());
                    a.setNeutered(animal.getNeutered());
                    a.setStatus(animal.getStatus());
                    a.setImageUrl(animal.getImageUrl());
                    a.setCreatedAt(animal.getCreatedAt());
                    a.setUpdatedAt(animal.getUpdatedAt());
                    return animalRepository.save(a);
                });
    }
}
