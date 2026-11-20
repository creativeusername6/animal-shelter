package com.example.backend.service;

import com.example.backend.dto.AnimalRequestDTO;
import com.example.backend.dto.AnimalResponseDTO;
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

    public AnimalResponseDTO postAnimal(AnimalRequestDTO animalRequest) {
        Animal animal = animalMapper.mapRequestToAnimal(animalRequest);
        animal.setCreatedAt(LocalDateTime.now());
        animal.setUpdatedAt(LocalDateTime.now());
        return animalMapper.mapAnimalToResponse(animalRepository.save(animal));
    }

    public Optional<AnimalResponseDTO> getAnimalById(Long id) {
        return this.animalRepository.findById(id)
                .map(animalMapper::mapAnimalToResponse);
    }

    public List<AnimalResponseDTO> getAllAnimalsByFilters(AnimalType type,
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

        return animalRepository.findAll(specification)
                .stream()
                .map(animalMapper::mapAnimalToResponse)
                .toList();
    }

    public Optional<AnimalResponseDTO> putAnimal(Long id, AnimalRequestDTO animalRequest) {
        Animal animal = animalMapper.mapRequestToAnimal(animalRequest);

        return animalRepository.findById(id)
                .map(a -> {
                    animalMapper.putAnimal(animal, a);
                    a.setUpdatedAt(LocalDateTime.now());
                    return animalRepository.save(a);
                })
                .map(animalMapper::mapAnimalToResponse);
    }

    public Optional<AnimalResponseDTO> patchAnimal(Long id, AnimalRequestDTO animalRequest) {
        Animal animal = animalMapper.mapRequestToAnimal(animalRequest);

        return animalRepository.findById(id)
                .map(a -> {
                    animalMapper.patchAnimal(animal, a);
                    a.setUpdatedAt(LocalDateTime.now());
                    return animalRepository.save(a);
                })
                .map(animalMapper::mapAnimalToResponse);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
