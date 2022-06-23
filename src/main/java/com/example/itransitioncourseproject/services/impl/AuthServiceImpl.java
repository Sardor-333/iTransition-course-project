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
import com.example.itransitioncourseproject.services.CloudinaryService;
import com.example.itransitioncourseproject.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String DIRECTORY_UPLOAD = "src/main/resources/uploads/";

    private final UserRepo userRepo;
    private final ResourceBundleMessageSource messageSource;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final FileService fileService;
    private final CloudinaryService cloudinaryService;

    @Override
    public ApiResponse register(RegisterDto registerDto) {
        if (userRepo.existsByUsername(registerDto.getUsername())) {
            return new ApiResponse(
                    false, messageSource.getMessage("usernameAlreadyExists",
                    new Object[]{registerDto.getUsername()},
                    Locale.getDefault()));
        }

        User user = userMapper.mapFromRegisterDtoToUser(registerDto);
        user.setRole(roleRepo.getByRoleName(UserRole.ROLE_USER));

        if (fileService.validateMultipart(registerDto.getProfileImg())) {
            try {
                File saved = fileService.save(registerDto.getProfileImg(), DIRECTORY_UPLOAD);
                CloudinaryResource resource = cloudinaryService.uploadFile(saved);
                saved.delete();
                user.setPhoto(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userRepo.save(user);

        return new ApiResponse(true, messageSource.getMessage("successfullyRegistered", null, Locale.getDefault()));
    }
}
