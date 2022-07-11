package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.payloads.request.topic.TopicCreateDto;
import com.example.itransitioncourseproject.projections.TopicProjection;
import com.example.itransitioncourseproject.services.TopicService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    /**
     * PUBLIC
     */
    @GetMapping
    public ResponseEntity<List<TopicProjection>> getAllTopics() {
        List<TopicProjection> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    /**
     * PUBLIC
     */
    @GetMapping("/pageable")
    public String getTopicsPageable(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                    @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size,
                                    Model model) {
        Paged<TopicProjection> topics = topicService.getTopicsPageable(page, size);
        model.addAttribute("topics", topics);
        return "topics";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/create")
    public String getTopicCreatePage() {
        return "create-topic";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/create")
    public String createTopic(@ModelAttribute TopicCreateDto topicCreateDto) {
        return "redirect:" + BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/topics";
    }
}
