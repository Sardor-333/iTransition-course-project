package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface TopicProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    String getName();

    @Value("#{@collectionRepo.countAllByTopicId(target.id)}")
    Integer getCollectionsCount();
}
