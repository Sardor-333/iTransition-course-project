package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Tag;
import com.example.itransitioncourseproject.projections.TagProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Long> {

    @Query(
            nativeQuery = true,
            value = "select t.id                                        as id,\n" +
                    "       t.name                                      as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from tags t"
    )
    List<TagProjection> getAllTags();
}
