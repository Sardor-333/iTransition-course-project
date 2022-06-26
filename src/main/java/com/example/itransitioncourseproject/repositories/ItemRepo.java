package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.projections.ItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {

    long countAllByCollectionId(Long collection_id);

    @Query(
            nativeQuery = true,
            value = "select i.id                                        as id,\n" +
                    "       i.name                                      as name,\n" +
                    "       to_char(i.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(i.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       count(l.*)                                  as likesCount,\n" +
                    "       count(c.*)                                  as commentsCount\n" +
                    "from items i\n" +
                    "         left join likes l on i.id = l.item_id\n" +
                    "         left join comments c on i.id = c.item_id\n" +
                    "group by i.id\n" +
                    "order by i.id desc\n" +
                    "limit 5"
    )
    List<ItemProjection> get5LatestAddedItems();
}
