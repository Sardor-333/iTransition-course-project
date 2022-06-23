package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    String getLoggedAt();

    String getFirstName();

    String getLastName();

    String getUsername();

    Boolean getEnabled();

    String getImgUrl();

    @Value("#{@roleRepo.getRoleByUserId(target.id)}")
    RoleProjection getRole();
}
