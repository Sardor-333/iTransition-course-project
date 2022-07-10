package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface TagProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    String getName();

    @Value("#{@itemRepo.countAllByTagsId(target.id)}")
    Integer getItemsCount();
}
