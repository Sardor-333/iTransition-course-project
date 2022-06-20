package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.services.CollectionService;
import com.example.itransitioncourseproject.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final CollectionService collectionService;

    @GetMapping(path = {"/", "home"})
    public String home(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE) Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_SIZE) Integer size,
                       Model model) {
        model.addAttribute("collections", collectionService.getCollections(page, size));
        return "home";
    }
}
