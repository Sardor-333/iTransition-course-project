package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.projections.RoleProjection;
import com.example.itransitioncourseproject.repositories.RoleRepo;
import com.example.itransitioncourseproject.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public Set<RoleProjection> getRoles() {
        return roleRepo.getRoles();
    }
}
