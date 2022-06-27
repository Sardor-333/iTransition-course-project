package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import com.example.itransitioncourseproject.services.CollectionService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public ModelAndView getCollections(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                       @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size,
                                       ModelMap model) {
        model.addAttribute("collections", collectionService.getCollections(page, size));
        return new ModelAndView("collections", model);
    }

    @GetMapping("/my")
    public ModelAndView getMyCollections(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                         @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size,
                                         ModelMap model,
                                         @AuthenticationPrincipal User user) {
        Paged<CollectionProjection> myCollections = collectionService.getMyCollections(page, size, user);
        model.addAttribute("collections", myCollections);
        return new ModelAndView("my-collections", model);
    }
}
