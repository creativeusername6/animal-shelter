package com.example.backend.service;

import com.example.backend.entity.Animal;
import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
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

    public Optional<Animal> patchAnimal(Long id, Animal animal) {
        return animalRepository.findById(id)
                .map(a -> {
                    if (animal.getName() != null) a.setName(animal.getName());
                    if (animal.getType() != null) a.setType(animal.getType());
                    if (animal.getBreed() != null) a.setBreed(animal.getBreed());
                    if (animal.getAge() != null) a.setAge(animal.getAge());
                    if (animal.getGender() != null) a.setGender(animal.getGender());
                    if (animal.getSize() != null) a.setSize(animal.getSize());
                    if (animal.getDescription() != null) a.setDescription(animal.getDescription());
                    if (animal.getVaccinated() != null) a.setVaccinated(animal.getVaccinated());
                    if (animal.getNeutered() != null) a.setNeutered(animal.getNeutered());
                    if (animal.getStatus() != null) a.setStatus(animal.getStatus());
                    if (animal.getImageUrl() != null) a.setImageUrl(animal.getImageUrl());
                    if (animal.getCreatedAt() != null) a.setCreatedAt(animal.getCreatedAt());
                    if (animal.getUpdatedAt() != null) a.setUpdatedAt(animal.getUpdatedAt());

                    return animalRepository.save(a);
                });
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
