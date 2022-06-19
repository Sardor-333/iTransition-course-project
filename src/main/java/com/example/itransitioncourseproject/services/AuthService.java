package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.payloads.request.RegisterDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;

public interface AuthService {

    ApiResponse register(RegisterDto registerDto);
}
