package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.payloads.request.ProfileDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.UserProjection;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    Paged<UserProjection> getUsers(Integer page, Integer size);

    ApiResponse enableOrDisableUser(Long id, HttpServletRequest request, User currentUser);

    UserProjection getUser(User user);

    ApiResponse deleteUser(Long id);

    ApiResponse editProfile(ProfileDto profileDto, User currentUser);

    ApiResponse changeRole(Long userId);
}
