package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.CloudinaryResource;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.enums.UserRole;
import com.example.itransitioncourseproject.mappers.UserMapper;
import com.example.itransitioncourseproject.payloads.request.RegisterDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.repositories.RoleRepo;
import com.example.itransitioncourseproject.repositories.UserRepo;
import com.example.itransitioncourseproject.services.AuthService;
import com.example.itransitioncourseproject.services.MultipartService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final ResourceBundleMessageSource messageSource;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final MultipartService multipartService;

    @Override
    public ApiResponse register(RegisterDto registerDto) {
        if (userRepo.existsByUsername(registerDto.getUsername())) {
            return new ApiResponse(
                    false, messageSource.getMessage("error.usernameAlreadyExists",
                    new Object[]{registerDto.getUsername()},
                    Locale.getDefault()));
        }

        User user = userMapper.mapFromRegisterDtoToUser(registerDto);
        user.setRole(roleRepo.getByRoleName(UserRole.ROLE_USER));

        try {
            CloudinaryResource fromMultipart = multipartService.generateCloudinaryResourceFromMultipart(registerDto.getProfileImg());
            user.setPhoto(fromMultipart);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userRepo.save(user);
        return new ApiResponse(true, messageSource.getMessage("ok.successfullyRegistered", null, Locale.getDefault()));
    }
}
