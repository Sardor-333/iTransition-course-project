package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Like;
import com.example.itransitioncourseproject.projections.LikeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like, Long> {

    @Query(
            nativeQuery = true,
            value = "select l.id                                        as id,\n" +
                    "       to_char(l.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(l.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from likes l\n" +
                    "         join items i on l.item_id = i.id\n" +
                    "where i.id = :itemId"
    )
    List<LikeProjection> getLikesByItemId(@Param("itemId") Long itemId);

    Optional<Like> findByLikedBy_IdAndItem_Id(Long likedBy_id, Long item_id);
}
