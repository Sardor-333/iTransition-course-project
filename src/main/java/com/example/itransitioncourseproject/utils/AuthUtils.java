package com.example.itransitioncourseproject.utils;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AuthUtils {

    private final ResourceBundleMessageSource messageSource;

    public boolean hasRole(User user, UserRole role) {
        return user
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role.toString()));
    }

    public boolean userHasAccessToCollection(Collection collection, User currentUser) {
        if (collection == null)
            return false;

        // IF SIMPLE USER AND IS NOT THE OWNER OF THE COLLECTION THEN DOES NOT HAVE ACCESS
        return !(this.hasRole(currentUser, UserRole.ROLE_USER) && !collection.getUser().getId().equals(currentUser.getId()));
    }
}
