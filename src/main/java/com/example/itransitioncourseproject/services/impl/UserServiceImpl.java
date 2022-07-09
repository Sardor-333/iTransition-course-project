package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.CloudinaryResource;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.enums.UserRole;
import com.example.itransitioncourseproject.mappers.UserMapper;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.pagination.Paging;
import com.example.itransitioncourseproject.payloads.request.ProfileDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.UserProjection;
import com.example.itransitioncourseproject.repositories.CloudinaryResourceRepo;
import com.example.itransitioncourseproject.repositories.RoleRepo;
import com.example.itransitioncourseproject.repositories.UserRepo;
import com.example.itransitioncourseproject.services.MultipartService;
import com.example.itransitioncourseproject.services.UserService;
import com.example.itransitioncourseproject.utils.AuthUtils;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepo userRepo;

    private final ResourceBundleMessageSource messageSource;

    private final MultipartService multipartService;

    private final CloudinaryResourceRepo cloudinaryResourceRepo;

    private final RoleRepo roleRepo;

    private final AuthUtils authUtils;

    @Override
    public Paged<UserProjection> getUsers(Integer page, Integer size) {
        PageSizeUtils.validatePageAndSize(page, size, userRepo);
        Page<UserProjection> usersPage = userRepo.getUsers(PageRequest.of(page - 1, size, Sort.Direction.DESC, "logged_at"));
        return new Paged<>(usersPage, Paging.of(usersPage.getTotalPages(), page, size));
    }

    @Override
    public ApiResponse enableOrDisableUser(Long id, HttpServletRequest request, User currentUser) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();

            if (authUtils.hasRole(user, UserRole.ROLE_SUPER_ADMIN))
                return new ApiResponse(false, messageSource.getMessage("error.superAdminCannotBeEdited", null, Locale.getDefault()));

            enableOrDisable(user, currentUser, request);
            return new ApiResponse(true, messageSource.getMessage(
                    "ok.userEnabledOrDisabled",
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
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            if (authUtils.hasRole(user, UserRole.ROLE_SUPER_ADMIN))
                return new ApiResponse(false, messageSource.getMessage("error.superAdminCannotBeEdited", null, Locale.getDefault()));

            userRepo.delete(user);
            return new ApiResponse(true, messageSource.getMessage("ok.userDeleted", null, Locale.getDefault()));
        }
        return new ApiResponse(false, messageSource.getMessage("error.userNotFound", new Object[]{id}, Locale.getDefault()));
    }

    @Override
    public ApiResponse editProfile(ProfileDto profileDto, User currentUser) {
        if (userRepo.existsByUsernameAndIdNot(profileDto.getUsername(), currentUser.getId())) {
            return new ApiResponse(false, messageSource.getMessage("error.usernameAlreadyExists", new Object[]{profileDto.getUsername()}, Locale.getDefault()));
        }
        userMapper.mapFromProfileDtoToUser(profileDto, currentUser);

        if (multipartService.isValidMultipart(profileDto.getProfileImg())) {
            try {
                CloudinaryResource fromMultipart = multipartService.generateCloudinaryResourceFromMultipart(profileDto.getProfileImg());
                if (currentUser.getPhoto() != null)
                    cloudinaryResourceRepo.delete(currentUser.getPhoto());
                currentUser.setPhoto(fromMultipart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userRepo.save(currentUser);
        return new ApiResponse(true, messageSource.getMessage("ok.profileEdited", null, Locale.getDefault()));
    }

    @Override
    public ApiResponse changeRole(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (authUtils.hasRole(user, UserRole.ROLE_SUPER_ADMIN))
                return new ApiResponse(false, messageSource.getMessage("error.superAdminCannotBeEdited", null, Locale.getDefault()));

            if (user.getRole().getRoleName() == UserRole.ROLE_ADMIN)
                user.setRole(roleRepo.getByRoleName(UserRole.ROLE_USER));
            else user.setRole(roleRepo.getByRoleName(UserRole.ROLE_ADMIN));

            userRepo.save(user);
            return new ApiResponse(false, messageSource.getMessage("ok.userRoleChanged", new Object[]{user.getRole().getRoleName()}, Locale.getDefault()));
        }
        return new ApiResponse(false, messageSource.getMessage("error.userNotFound", new Object[]{userId}, Locale.getDefault()));
    }

    private void enableOrDisable(User user, User currentUser, HttpServletRequest request) {
        user.setEnabled(!user.isEnabled());
        userRepo.save(user);
        if (user.getId().equals(currentUser.getId())) {
            SecurityContextHolder.clearContext();
            request.getSession(false).invalidate();
        }
    }
}
