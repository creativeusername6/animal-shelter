package com.example.backend.mapper;

import com.example.backend.dto.AnimalDTO;
import com.example.backend.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    Animal mapDtoToAnimal (AnimalDTO animalDTO);
    AnimalDTO mapAnimalToDto (Animal animal);
}
