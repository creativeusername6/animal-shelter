package com.example.backend.service;

import com.example.backend.entity.Animal;
import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
import com.example.backend.mapper.AnimalMapper;
import com.example.backend.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
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

    public List<Animal> getAllAnimalsByGender(Gender gender) {
        return animalRepository.findAll()
                .stream()
                .filter(a -> a.getGender() == gender)
                .toList();
    }

    public List<Animal> getAllAnimalsBySize(AnimalSize size) {
        return animalRepository.findAll()
                .stream()
                .filter(a -> a.getSize() == size)
                .toList();
    }

    public List<Animal> getAllAnimalsByAgeRange(Integer lower, Integer upper) {
        return animalRepository.findAll()
                .stream()
                .filter(a -> a.getAge() >= lower && a.getAge() <= upper)
                .toList();
    }

    public List<Animal> getAllAnimalsByStatus(AnimalStatus status) {
        return animalRepository.findAll()
                .stream()
                .filter(a -> a.getStatus() == status)
                .toList();
    }

    public Optional<Animal> putAnimal(Long id, Animal animal) {
        return animalRepository.findById(id)
                .map(a -> {
                    animalMapper.putAnimal(animal, a);
                    a.setUpdatedAt(LocalDateTime.now());
                    return animalRepository.save(a);
                });
    }

    public Optional<Animal> patchAnimal(Long id, Animal animal) {
        return animalRepository.findById(id)
                .map(a -> {
                    animalMapper.patchAnimal(animal, a);
                    a.setUpdatedAt(LocalDateTime.now());
                    return animalRepository.save(a);
                });
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
