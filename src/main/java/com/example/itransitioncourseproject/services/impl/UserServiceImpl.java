package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.enums.UserRole;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.pagination.Paging;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.UserProjection;
import com.example.itransitioncourseproject.repositories.UserRepo;
import com.example.itransitioncourseproject.services.UserService;
import com.example.itransitioncourseproject.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ResourceBundleMessageSource messageSource;

    @Override
    public Paged<UserProjection> getUsers(Integer page, Integer size) {
        Validator.validatePageAndSize(page, size);
        Page<UserProjection> usersPage = userRepo.getUsers(PageRequest.of(page - 1, size, Sort.Direction.DESC, "logged_at"));
        return new Paged<>(usersPage, Paging.of(usersPage.getTotalPages(), page, size));
    }

    @Override
    public ApiResponse enableOrDisableUser(Long id, HttpServletRequest request, User currentUser) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();

            if (hasRole(user, UserRole.ROLE_SUPER_ADMIN))
                return new ApiResponse(false, messageSource.getMessage("error.superAdminStatusCannotBeEdited", null, Locale.getDefault()));

            enableOrDisable(user, currentUser, request);
            return new ApiResponse(true, messageSource.getMessage(
                    "response.userEnabledOrDisabled",
                    new Object[]{user.isEnabled() ? "enabled" : "disabled"},
                    Locale.getDefault()));
        }
        return new ApiResponse(false, messageSource.getMessage("error.userNotFound", new Object[]{id}, Locale.getDefault()));
    }

    @Override
    public UserProjection getUser(User user) {
        return userRepo.getUser(user.getId());
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (hasRole(user, UserRole.ROLE_SUPER_ADMIN)) {
                return null; // todo : super admin cannot be deleted
            }
        }
        return null;
    }

    private void enableOrDisable(User user, User currentUser, HttpServletRequest request) {
        user.setEnabled(!user.isEnabled());
        userRepo.save(user);
        if (user.getId().equals(currentUser.getId())) {
            SecurityContextHolder.clearContext();
            request.getSession(false).invalidate();
        }
    }

    private boolean hasRole(User user, UserRole role) {
        return user
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role.toString()));
    }
}
