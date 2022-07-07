package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface LikeProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    @Value("#{@userRepo.getUserByLikeId(target.id)}")
    UserProjection getLikedBy();
}
