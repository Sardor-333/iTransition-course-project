package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.projections.TopicProjection;
import com.example.itransitioncourseproject.services.TopicService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<List<TopicProjection>> getAllTopicsPageable(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                                                      @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size) {
        List<TopicProjection> topics = topicService.getTopics();
        return ResponseEntity.ok(topics);
    }
}
