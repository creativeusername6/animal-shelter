package com.example.backend.service;

import com.example.backend.entity.Animal;
import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
import com.example.backend.mapper.AnimalMapper;
import com.example.backend.repository.AnimalRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Optional<Animal> getAnimalById(Long id) {
        return this.animalRepository.findById(id);
    }

    public List<Animal> getAllAnimalsByFilters(AnimalType type,
                                               Gender gender,
                                               AnimalSize size,
                                               Integer minAge,
                                               Integer maxAge,
                                               AnimalStatus status) {
        Specification<Animal> specification = (root, _, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (type != null) predicates.add(builder.equal(root.get("type"), type));
            if (gender != null) predicates.add(builder.equal(root.get("gender"), gender));
            if (size != null) predicates.add(builder.equal(root.get("size"), size));
            if (minAge != null) predicates.add(builder.greaterThanOrEqualTo(root.get("age"), minAge));
            if (maxAge != null) predicates.add(builder.lessThanOrEqualTo(root.get("age"), maxAge));
            if (status != null) predicates.add(builder.equal(root.get("status"), status));

            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return animalRepository.findAll(specification);
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
