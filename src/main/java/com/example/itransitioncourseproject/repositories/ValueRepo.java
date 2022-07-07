package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Value;
import com.example.itransitioncourseproject.projections.ValueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ValueRepo extends JpaRepository<Value, Long> {

    @Query(
            nativeQuery = true,
            value = "select v.id                                        as id,\n" +
                    "       v.target_value                              as targetValue,\n" +
                    "       to_char(v.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(v.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt\n" +
                    "from values v\n" +
                    "         join items i on v.item_id = i.id\n" +
                    "where i.id = :itemId"
    )
    List<ValueProjection> getValuesByItemId(@Param("itemId") Long itemId);
}
