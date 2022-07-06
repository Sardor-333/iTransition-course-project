package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepo extends JpaRepository<Value, Long> {
}
