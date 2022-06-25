package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.CloudinaryResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudinaryResourceRepo extends JpaRepository<CloudinaryResource, Long> {
}
