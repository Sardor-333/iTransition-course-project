package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.User;

public interface LikeService {

    public void likeOrDislikeItem(Long itemId, User currentUser);
}
