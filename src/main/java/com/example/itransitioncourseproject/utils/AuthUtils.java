package com.example.itransitioncourseproject.utils;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.enums.UserRole;

public final class AuthUtils {

    public static boolean hasRole(User user, UserRole role) {
        return user
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role.toString()));
    }
}
