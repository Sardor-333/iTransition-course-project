package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.services.LikeService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/item/{itemId}")
    public String likeOrDislikeItem(@PathVariable Long itemId, @AuthenticationPrincipal User currentUser) {
        likeService.likeOrDislikeItem(itemId, currentUser);
        return "redirect:/api/v1/items/" + itemId;
    }
}
