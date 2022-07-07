package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface CommentProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    String getBody();

    @Value("#{@userRepo.getGetUserByCommentId(target.id)}")
    UserProjection getUser();
}
