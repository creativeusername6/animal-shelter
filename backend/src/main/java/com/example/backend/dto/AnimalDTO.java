package com.example.backend.dto;

import com.example.backend.enums.AnimalSize;
import com.example.backend.enums.AnimalStatus;
import com.example.backend.enums.AnimalType;
import com.example.backend.enums.Gender;

import java.time.LocalDateTime;

public record AnimalDTO (
    Long id,
    String name,
    AnimalType type,
    String breed,
    Integer age,
    Gender gender,
    AnimalSize size,
    String description,
    Boolean vaccinated,
    Boolean neutered,
    AnimalStatus status,
    String imageUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
