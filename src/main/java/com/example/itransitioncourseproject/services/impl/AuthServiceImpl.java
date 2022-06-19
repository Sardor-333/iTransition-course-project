package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.enums.UserRole;
import com.example.itransitioncourseproject.payloads.request.RegisterDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.repositories.RoleRepo;
import com.example.itransitioncourseproject.repositories.UserRepo;
import com.example.itransitioncourseproject.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final ResourceBundleMessageSource messageSource;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse register(RegisterDto registerDto) {
        if (userRepo.existsByUsername(registerDto.getUsername())) {
            return new ApiResponse(
                    false, messageSource.getMessage("usernameAlreadyExists",
                    new Object[]{registerDto.getUsername()},
                    Locale.getDefault()));
        }

        userRepo.save(new User(
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                Arrays.asList(roleRepo.getByRoleName(UserRole.ROLE_USER))
        ));
        return new ApiResponse(true, messageSource.getMessage("successfullyRegistered", null, Locale.getDefault()));
    }
}
