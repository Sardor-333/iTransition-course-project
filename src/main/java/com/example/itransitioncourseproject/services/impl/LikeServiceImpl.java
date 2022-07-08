package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.Like;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.exceptions.ObjectNotFoundException;
import com.example.itransitioncourseproject.repositories.ItemRepo;
import com.example.itransitioncourseproject.repositories.LikeRepo;
import com.example.itransitioncourseproject.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepo likeRepo;

    private final ItemRepo itemRepo;

    @Override
    public void likeOrDislikeItem(Long itemId, User currentUser) {
        Item item = itemRepo
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException("Item with id: " + itemId + " not found!"));

        Like like = likeRepo.findByLikedBy_IdAndItem_Id(currentUser.getId(), itemId).orElse(null);
        if (like == null)
            likeRepo.save(new Like(currentUser, item));
        else
            likeRepo.delete(like);
    }
}
