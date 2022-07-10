package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.projections.ItemDetailProjection;
import com.example.itransitioncourseproject.projections.ItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {

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
                    "group by i.id, i.created_at\n" +
                    "order by i.created_at desc\n" +
                    "limit 5"
    )
    List<ItemProjection> get5LatestAddedItems();

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
                    "group by i.id, i.created_at\n" +
                    "order by i.created_at desc"
    )
    List<ItemProjection> getAllItems();

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
                    "         left join comments c on i.id = c.item_id\n " +
                    "join collections col on col.id = i.collection_id " +
                    "where col.id = :collectionId " +
                    "group by i.id, i.created_at\n" +
                    "order by i.created_at desc"
    )
    List<ItemProjection> getItemsByCollectionId(@Param("collectionId") Long collectionId);

    @Query(
            nativeQuery = true,
            value = "select i.id                                        as id,\n" +
                    "       i.name                                      as name,\n" +
                    "       to_char(i.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(i.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       count(l.*)                                  as likesCount,\n" +
                    "       count(c.*)                                  as commentsCount\n" +
                    "from items i\n" +
                    "         join items_tags it on i.id = it.item_id\n" +
                    "         join tags t on t.id = it.tag_id\n" +
                    "         left join comments c on i.id = c.item_id\n" +
                    "         left outer join likes l on i.id = l.item_id\n" +
                    "where t.id = :tagId\n" +
                    "group by i.id, i.created_at\n" +
                    "order by i.created_at desc"
    )
    List<ItemProjection> getItemsByTagId(@Param("tagId") Long tagId);

    @Query(
            nativeQuery = true,
            value = "select i.id                                        as id,\n" +
                    "       to_char(i.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(i.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       i.name                                      as name,\n" +
                    "       case\n" +
                    "           when :userId is null then false\n" +
                    "           when :userId in (\n" +
                    "               select l.liked_by\n" +
                    "               from items i2\n" +
                    "                        join likes l on l.item_id = i2.id\n" +
                    "               where i2.id = :itemId\n" +
                    "           ) then true\n" +
                    "           else false end                          as likedByMe\n" +
                    "from items i\n" +
                    "where i.id = :itemId"
    )
    ItemDetailProjection getItemDetailById(@Param("itemId") Long itemId, @Param("userId") Long userId);

    long countAllByTagsId(Long tags_id);
}
