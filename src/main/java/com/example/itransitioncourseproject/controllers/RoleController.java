package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.projections.RoleProjection;
import com.example.itransitioncourseproject.services.RoleService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    ResponseEntity<Set<RoleProjection>> getRoles() {
        Set<RoleProjection> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }
}
