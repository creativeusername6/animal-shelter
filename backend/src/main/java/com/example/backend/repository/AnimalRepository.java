package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
