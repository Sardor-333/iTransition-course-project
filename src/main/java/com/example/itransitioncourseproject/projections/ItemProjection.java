package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ItemProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    String getName();

    @Value("#{@collectionRepo.getCollectionByItemId(target.id)}")
    CollectionProjection getCollection();

    Integer getLikesCount();

    Integer getCommentsCount();
}
