package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {

    Long getId();

    String getImgUrl();

    String getFirstName();

    String getLastName();

    String getUsername();

    String getLoggedAt();

    @Value("#{@roleRepo.getRoleByUserId(target.id)}")
    RoleProjection getRole();

    String getCreatedAt();

    String getUpdatedAt();

    Boolean getEnabled();
}
