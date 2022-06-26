package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionRepo extends JpaRepository<Collection, Long> {

    @Query(
            nativeQuery = true,
            value = "select c.id          as id,\n" +
                    "       c.name        as name,\n" +
                    "       c.description as description,\n" +
                    "       cr.secure_url as imgUrl,\n" +
                    "       count(i.*)    as itemsCount\n" +
                    "from collections c\n" +
                    "         left outer join cloudinary_resources cr on cr.id = c.resource_id\n" +
                    "         left outer join items i on c.id = i.collection_id\n" +
                    "group by c.id, c.name, c.description, cr.secure_url\n" +
                    "order by count(i.*) desc " +
                    "limit 5"
    )
    List<CollectionProjection> getTop5BiggestCollections();

    @Query(
            nativeQuery = true,
            value = "select c.id          as id,\n" +
                    "       c.name        as name,\n" +
                    "       c.description as description,\n" +
                    "       cr.secure_url as imgUrl,\n" +
                    "       count(i.*)    as itemsCount\n" +
                    "from collections c\n" +
                    "         left outer join cloudinary_resources cr on cr.id = c.resource_id\n" +
                    "         join items i on c.id = i.collection_id\n" +
                    "where i.id = :itemId\n" +
                    "group by c.id, c.name, c.description, cr.secure_url"
    )
    CollectionProjection getCollectionByItemId(@Param("itemId") Long itemId);
}
