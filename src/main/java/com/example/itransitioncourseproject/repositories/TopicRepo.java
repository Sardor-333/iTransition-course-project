package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Topic;
import com.example.itransitioncourseproject.projections.TopicProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepo extends JpaRepository<Topic, Long> {

    @Query(
            nativeQuery = true,
            value = "select t.id                                        as id,\n" +
                    "       t.name                                      as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from topics t"
    )
    List<TopicProjection> getAllTopics();

    @Query(
            nativeQuery = true,
            value = "select t.id                                        as id,\n" +
                    "       t.name                                      as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from topics t"
    )
    Page<TopicProjection> getTopicsPageable(Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select t.id                                        as id,\n" +
                    "       t.name                                      as name,\n" +
                    "       to_char(t.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(t.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from topics t\n" +
                    "         join collections c on t.id = c.topic_id\n" +
                    "where c.id = :collectionId"
    )
    TopicProjection getTopicByCollectionId(@Param("collectionId") Long collectionId);
}
