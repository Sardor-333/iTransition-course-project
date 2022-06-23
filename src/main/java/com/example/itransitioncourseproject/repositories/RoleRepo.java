package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.Role;
import com.example.itransitioncourseproject.enums.UserRole;
import com.example.itransitioncourseproject.projections.RoleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(UserRole roleName);

    Role getByRoleName(UserRole roleName);

    @Query(
            nativeQuery = true,
            value = "select r.id                                        as id,\n" +
                    "       to_char(r.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(r.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       r.role_name                                 as name\n" +
                    "from roles r\n" +
                    "         join users u on u.role_id = r.id\n" +
                    "where u.id = :userId"
    )
    RoleProjection getRoleByUserId(@Param("userId") Long userId);

    @Query(
            nativeQuery = true,
            value = "select r.id                                        as id,\n" +
                    "       to_char(r.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(r.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       r.role_name                                 as name\n" +
                    "from roles r"
    )
    Set<RoleProjection> getRoles();
}
