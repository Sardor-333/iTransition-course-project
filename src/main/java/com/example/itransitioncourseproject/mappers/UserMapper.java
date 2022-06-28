package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.ProfileDto;
import com.example.itransitioncourseproject.payloads.request.RegisterDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public abstract void mapFromProfileDtoToUser(ProfileDto src, @MappingTarget User target);

    @Mapping(expression = "java(encodePassword(src.getPassword()))", target = "password")
    public abstract User mapFromRegisterDtoToUser(RegisterDto src);

    @Named("encodePassword")
    public String encodePassword(String password) {
        if (password == null || password.trim().equals("")) return null;
        return passwordEncoder.encode(password);
    }
}
