package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.CommentCreateDto;
import com.example.itransitioncourseproject.projections.CommentProjection;
import com.example.itransitioncourseproject.services.CommentService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/comments")
@RequiredArgsConstructor
public class CommentController {

    private final SimpMessagingTemplate messagingTemplate;

    private final CommentService commentService;

    @MessageMapping("/items/{itemId}/comments")
    public void leaveComment(@DestinationVariable(value = "itemId") Long itemId,
                             @Payload CommentCreateDto commentCreateDto,
                             Principal principal) {
        Authentication authentication = (Authentication) principal;
        User currentUser = (User) authentication.getPrincipal();

        CommentProjection newComment = commentService.leaveComment(itemId, commentCreateDto, currentUser);
        messagingTemplate.convertAndSend("/topic/items/" + itemId + "/comments", newComment);
    }
}
