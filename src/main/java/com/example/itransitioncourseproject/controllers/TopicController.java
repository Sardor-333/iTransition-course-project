package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.projections.TopicProjection;
import com.example.itransitioncourseproject.services.TopicService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(BaseUrl.API_PREFIX+BaseUrl.API_VERSION + "/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    /**
     * PUBLIC
     */
    @GetMapping
    public ResponseEntity<List<TopicProjection>> getTopics() {
        List<TopicProjection> topics = topicService.getTopics();
        return ResponseEntity.ok(topics);
    }
}
