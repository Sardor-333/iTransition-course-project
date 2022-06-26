package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface CollectionProjection {

    Long getId();

    String getName();

    String getDescription();

    String getImgUrl();

    Integer getItemsCount();

    @Value("#{@userRepo.getUserByCollectionId(target.id)}")
    UserProjection getAuthor();
}
