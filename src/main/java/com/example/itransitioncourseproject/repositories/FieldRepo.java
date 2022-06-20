package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Field;
import com.example.itransitioncourseproject.enums.FieldType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FieldRepo extends JpaRepository<Field, Long> {

    Optional<Field> findByFieldType(FieldType fieldType);
}
