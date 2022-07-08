package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Comment;
import com.example.itransitioncourseproject.projections.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query(
            nativeQuery = true,
            value = "select c.id                                        as id,\n" +
                    "       c.body                                      as body,\n" +
                    "       to_char(c.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(c.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from comments c\n" +
                    "         join items i on c.item_id = i.id\n" +
                    "where i.id = :itemId " +
                    "order by c.created_at desc"
    )
    List<CommentProjection> getCommentsByItemId(@Param("itemId") Long itemId);

    @Query(
            nativeQuery = true,
            value = "select c.id                                        as id,\n" +
                    "       c.body                                      as body,\n" +
                    "       to_char(c.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(c.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from comments c\n" +
                    "where c.id = :commentId"
    )
    CommentProjection getCommentById(@Param("commentId") Long commentId);
}
