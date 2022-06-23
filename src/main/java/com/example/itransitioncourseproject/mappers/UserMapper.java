package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.mappers.base.BaseMapper;
import com.example.itransitioncourseproject.payloads.request.ProfileSaveDto;
import com.example.itransitioncourseproject.payloads.request.RegisterDto;
import com.example.itransitioncourseproject.repositories.RoleRepo;
import com.example.itransitioncourseproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class UserMapper implements BaseMapper<User, ProfileSaveDto, ProfileSaveDto> {

    @Autowired
    protected UserRepo userRepo;

    @Autowired
    protected RoleRepo roleRepo;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public abstract User mapFromCreateDtoToEntity(ProfileSaveDto dto);

    @Override
    public abstract void mapFromUpdateDtoToEntity(ProfileSaveDto src, @MappingTarget User target);

    @Mapping(expression = "java(encodePassword(src.getPassword()))", target = "password")
    public abstract User mapFromRegisterDtoToUser(RegisterDto src);

    @Named("encodePassword")
    public String encodePassword(String password) {
        if (password == null || password.trim().equals("")) return null;
        return passwordEncoder.encode(password);
    }
}
