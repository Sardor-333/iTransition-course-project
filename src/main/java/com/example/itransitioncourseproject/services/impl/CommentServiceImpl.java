package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.Comment;
import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.exceptions.ObjectNotFoundException;
import com.example.itransitioncourseproject.payloads.request.CommentCreateDto;
import com.example.itransitioncourseproject.projections.CommentProjection;
import com.example.itransitioncourseproject.repositories.CommentRepo;
import com.example.itransitioncourseproject.repositories.ItemRepo;
import com.example.itransitioncourseproject.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ItemRepo itemRepo;
    private final CommentRepo commentRepo;

    @Override
    public CommentProjection leaveComment(Long itemId, CommentCreateDto commentCreateDto, User currentUser) {
        Item item = itemRepo
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException("Item with id: " + itemId + " not found!"));

        Comment saved = commentRepo.save(new Comment(currentUser, item, commentCreateDto.getBody()));
        return commentRepo.getCommentById(saved.getId());
    }
}
