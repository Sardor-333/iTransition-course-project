package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.CommentCreateDto;
import com.example.itransitioncourseproject.projections.CommentProjection;

public interface CommentService {

    CommentProjection leaveComment(Long itemId, CommentCreateDto commentCreateDto, User currentUser);
}
