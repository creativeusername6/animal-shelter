package com.example.backend.mapper;

import com.example.backend.dto.AnimalDTO;
import com.example.backend.entity.Animal;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void putAnimal(Animal source, @MappingTarget Animal target);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchAnimal(Animal source, @MappingTarget Animal target);

    Animal mapDtoToAnimal (AnimalDTO animalDTO);
    AnimalDTO mapAnimalToDto (Animal animal);
}
