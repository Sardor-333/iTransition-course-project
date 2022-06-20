package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface CollectionProjection {

    Long getId();

    String getName();

    String getDescription();

    String getImgUrl();

    @Value("#{@itemRepo.countAllByCollectionId(target.id)}")
    Long getItemsCount();
}
