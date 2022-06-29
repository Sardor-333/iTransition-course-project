package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.services.ItemService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * PUBLIC
     */
    @GetMapping
    public ModelAndView getItems(ModelMap model) {
        model.addAttribute("items", itemService.getItems());
        return new ModelAndView("items", model);
    }

    /**
     * PUBLIC
     */
    @GetMapping("/collection/{collectionId}")
    public ModelAndView getItemsByCollection(@PathVariable Long collectionId, ModelMap model) {
        model.addAttribute("items", itemService.getItemsByCollectionId(collectionId));
        return new ModelAndView("items", model);
    }

    /**
     * PUBLIC
     */
    @GetMapping("/tag/{tagId}")
    public ModelAndView getItemsByTag(@PathVariable Long tagId, ModelMap model) {
        model.addAttribute("items", itemService.getItemsByTagId(tagId));
        return new ModelAndView("items", model);
    }
}
