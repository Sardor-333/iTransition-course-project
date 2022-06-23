package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.projections.RoleProjection;

import java.util.Set;

public interface RoleService {

    Set<RoleProjection> getRoles();
}
