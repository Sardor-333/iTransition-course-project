package com.example.itransitioncourseproject.common;

import com.example.itransitioncourseproject.entities.Role;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.enums.UserRole;
import com.example.itransitioncourseproject.repositories.RoleRepo;
import com.example.itransitioncourseproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String sqlInitMode;

    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        if (!Objects.isNull(sqlInitMode) && sqlInitMode.equals("always")) {

            // ROLE USER
            Role roleUser = roleRepo.findByRoleName(UserRole.ROLE_USER).orElse(null);
            if (roleUser == null) {
                roleUser = new Role(UserRole.ROLE_USER);
                roleRepo.save(roleUser);
            }

            // ROLE ADMIN
            Role roleAdmin = roleRepo.findByRoleName(UserRole.ROLE_ADMIN).orElse(null);
            if (roleAdmin == null) {
                roleAdmin = new Role(UserRole.ROLE_ADMIN);
                roleRepo.save(roleAdmin);
            }

            Role roleSuperAdmin = roleRepo.findByRoleName(UserRole.ROLE_SUPER_ADMIN).orElse(null);
            if (roleSuperAdmin == null) {
                roleSuperAdmin = new Role(UserRole.ROLE_SUPER_ADMIN);
                roleRepo.save(roleSuperAdmin);
            }

            // ADMIN
            User admin = userRepo.findByUsername("sardor").orElse(null);
            if (admin == null) {
                admin = new User(
                        "Sardor",
                        "Shermatov",
                        "sardor",
                        passwordEncoder.encode("sardor"),
                        Collections.asSet(roleSuperAdmin)
                );
                userRepo.save(admin);
            }
        }
    }
}
