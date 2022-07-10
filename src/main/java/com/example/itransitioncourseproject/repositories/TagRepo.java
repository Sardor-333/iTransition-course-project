package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Tag;
import com.example.itransitioncourseproject.projections.TagProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepo extends JpaRepository<Tag, Long> {

    boolean existsByName(String name);

    @Query(
            nativeQuery = true,
            value = "select t.id                                        as id,\n" +
                    "       t.name                                      as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from tags t " +
                    "order by t.created_at desc"
    )
    List<TagProjection> getAllTags();

    @Query(
            nativeQuery = true,
            value = "select t.id as id,\n" +
                    "       t.name as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from tags t\n" +
                    "join items_tags it on t.id = it.tag_id\n" +
                    "join items i on it.item_id = i.id\n" +
                    "where i.id = :itemId"
    )
    List<TagProjection> getGetTagsByItemId(@Param("itemId") Long itemId);

    @Query(
            nativeQuery = true,
            value = "select t.id                                        as id,\n" +
                    "       t.name                                      as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from tags t " +
                    "order by t.created_at desc"
    )
    Page<TagProjection> getAllTagsPageable(Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select t.id                                        as id,\n" +
                    "       t.name                                      as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from tags t " +
                    "where t.id = :tagId"
    )
    Optional<TagProjection> getTagById(@Param("tagId") Long tagId);
}
