package com.example.backend.dto;

import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record AnimalPatchDTO(
        @Pattern(regexp = ".*\\S.*", message = "Name cannot be blank")
        String name,
        AnimalType type,
        @Size(max = 100, message = "Breed cannot be longer than 100 characters")
        String breed,
        @Min(value = 0, message = "Age cannot be negative")
        @Max(value = 50, message = "Age is too high")
        Integer age,
        Gender gender,
        AnimalSize size,
        @Size(max = 2000, message = "Description cannot be longer than 2000 characters")
        String description,
        Boolean vaccinated,
        Boolean neutered,
        AnimalStatus status,
        @URL(message = "URL must be valid")
        String imageUrl) {}
