package com.example.backend.mapper;

import com.example.backend.dto.AnimalDTO;
import com.example.backend.entity.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal mapDtoToAnimal (AnimalDTO animalDTO);
    AnimalDTO mapAnimalToDto (Animal animal);
}
