package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.payloads.request.tag.TagCreateDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.TagProjection;
import com.example.itransitioncourseproject.services.TagService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public String getTagsPageable(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                  @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size,
                                  Model model) {
        Paged<TagProjection> tags = tagService.getTagsPageable(page, size);
        model.addAttribute("tags", tags);
        return "tags";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/create")
    public String getTagCreatePage() {
        return "create-tag";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/create")
    public String createTag(@ModelAttribute TagCreateDto tagCreateDto,
                            RedirectAttributes redirectAttributes) {
        ApiResponse response = tagService.createTag(tagCreateDto);
        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:" + BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/tags";
    }
}
