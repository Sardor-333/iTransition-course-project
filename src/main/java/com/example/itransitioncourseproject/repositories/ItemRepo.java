package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {

    long countAllByCollectionId(Long collection_id);
}
