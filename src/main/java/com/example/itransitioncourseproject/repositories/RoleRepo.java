package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Role;
import com.example.itransitioncourseproject.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(UserRole roleName);

    Role getByRoleName(UserRole roleName);
}
