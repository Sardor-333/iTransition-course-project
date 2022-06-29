package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.services.CollectionService;
import com.example.itransitioncourseproject.services.ItemService;
import com.example.itransitioncourseproject.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final CollectionService collectionService;
    private final ItemService itemService;
    private final TagService tagService;

    /**
     * PUBLIC
     */
    @GetMapping(path = {"/", "home"})
    public String home(Model model) {
        model.addAttribute("collections", collectionService.getTop5LargestCollections());
        model.addAttribute("items", itemService.get5LatestAddedItems());
        model.addAttribute("tags", tagService.getAllTags());
        return "home";
    }
}
