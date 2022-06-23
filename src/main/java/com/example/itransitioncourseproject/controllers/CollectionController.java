package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.utils.BaseUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/collections")
public class CollectionController {

    @GetMapping
    public String getCollections() {
        return null;
    }
}