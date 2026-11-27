package com.example.backend.mapper;

import com.example.backend.dto.AnimalPatchDTO;
import com.example.backend.dto.AnimalRequestDTO;
import com.example.backend.dto.AnimalResponseDTO;
import com.example.backend.entity.Animal;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void putAnimal(AnimalRequestDTO source, @MappingTarget Animal target);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchAnimal(AnimalPatchDTO source, @MappingTarget Animal target);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Animal mapRequestToAnimal(AnimalRequestDTO animalRequest);

    AnimalResponseDTO mapAnimalToResponse(Animal animal);
}
