package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.projections.TagProjection;
import com.example.itransitioncourseproject.services.TagService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TagProjection>> getTags() {
        List<TagProjection> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }
}
