package com.example.backend.dto;

import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public record AnimalDTO (
    Long id,
    @NotBlank(message = "Name cannot be blank")
    String name,
    @NotNull(message = "Type is required")
    AnimalType type,
    @Size(max = 100, message = "Breed cannot be longer than 100 characters")
    String breed,
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 50, message = "Age is too high")
    Integer age,
    @NotNull(message = "Gender is required")
    Gender gender,
    @NotNull(message = "Size is required")
    AnimalSize size,
    @Size(max = 2000, message = "Description cannot be longer than 2000 characters")
    String description,
    @NotNull(message = "Vaccinated must be specified")
    Boolean vaccinated,
    @NotNull(message = "Neutered must be specified")
    Boolean neutered,
    @NotNull(message = "Status is required")
    AnimalStatus status,
    @URL(message = "URL must be valid")
    String imageUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
