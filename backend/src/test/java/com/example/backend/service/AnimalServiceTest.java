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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {
    @Mock
    AnimalRepository animalRepository;
    @InjectMocks
    AnimalService animalService;
    @Mock
    AnimalMapper animalMapper;

    @Test
    void postAnimalShouldCreateAnimal() {
        LocalDateTime now = LocalDateTime.now();
        Long id = 1L;

        AnimalRequestDTO animalRequest = new AnimalRequestDTO("Name",
                AnimalType.DOG,
                "Breed",
                3,
                Gender.MALE,
                AnimalSize.LARGE,
                "Description",
                true,
                true,
                AnimalStatus.AVAILABLE,
                "dog.png");

        Animal animal = new Animal();
        Animal savedAnimal = new Animal();

        AnimalResponseDTO expectedResponse = new AnimalResponseDTO(id,
                "Name",
                AnimalType.DOG,
                "Breed",
                3,
                Gender.MALE,
                AnimalSize.LARGE,
                "Description",
                true,
                true,
                AnimalStatus.AVAILABLE,
                "dog.png",
                now,
                now);

        when(animalMapper.mapRequestToAnimal(animalRequest)).thenReturn(animal);
        when(animalRepository.save(any(Animal.class))).thenReturn(savedAnimal);
        when(animalMapper.mapAnimalToResponse(savedAnimal)).thenReturn(expectedResponse);

        AnimalResponseDTO animalResponse = animalService.postAnimal(animalRequest);

        assertNotNull(animalResponse);
        assertEquals(expectedResponse, animalResponse);
        assertNotNull(animal.getCreatedAt());
        assertNotNull(animal.getUpdatedAt());

        verify(animalMapper).mapRequestToAnimal(animalRequest);
        verify(animalRepository).save(animal);
        verify(animalMapper).mapAnimalToResponse(savedAnimal);
    }
}
