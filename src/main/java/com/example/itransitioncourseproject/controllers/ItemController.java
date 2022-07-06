package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.item.ItemCreateDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import com.example.itransitioncourseproject.projections.FieldProjection;
import com.example.itransitioncourseproject.projections.TagProjection;
import com.example.itransitioncourseproject.services.CollectionService;
import com.example.itransitioncourseproject.services.ItemService;
import com.example.itransitioncourseproject.services.TagService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/items")
@RequiredArgsConstructor
public class ItemController {

    private final CollectionService collectionService;

    private final ItemService itemService;

    private final TagService tagService;

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
        CollectionProjection collection = collectionService.getCollectionById(collectionId);
        model.addAttribute("collection", collection);

        model.addAttribute("items", itemService.getItemsByCollectionId(collectionId));
        return new ModelAndView("collection-items", model);
    }

    /**
     * PUBLIC
     */
    @GetMapping("/tag/{tagId}")
    public ModelAndView getItemsByTag(@PathVariable Long tagId, ModelMap model) {
        model.addAttribute("items", itemService.getItemsByTagId(tagId));
        return new ModelAndView("items", model);
    }

    @GetMapping("/create/{collectionId}")
    public String getItemCreatePage(@PathVariable Long collectionId, Model model) {
        List<FieldProjection> collectionFields = itemService.getCollectionFields(collectionId);
        List<TagProjection> tags = tagService.getAllTags();

        model.addAttribute("collectionId", collectionId);
        model.addAttribute("tags", tags);
        model.addAttribute("fields", collectionFields);
        return "create-item";
    }

    @PostMapping("/create/{collectionId}")
    public String createNewItem(@PathVariable Long collectionId,
                                @RequestBody ItemCreateDto itemCreateDto,
                                @AuthenticationPrincipal User currentUser,
                                RedirectAttributes redirectAttributes) {
        ApiResponse apiResponse = itemService.createItem(collectionId, itemCreateDto, currentUser);
        redirectAttributes.addFlashAttribute("response", apiResponse);
        return "redirect:/api/v1/items/collection/" + collectionId;
    }
}
