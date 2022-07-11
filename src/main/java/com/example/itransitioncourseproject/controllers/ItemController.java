package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.SearchDto;
import com.example.itransitioncourseproject.payloads.request.item.ItemDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.payloads.response.SearchResult;
import com.example.itransitioncourseproject.projections.FieldProjection;
import com.example.itransitioncourseproject.projections.ItemDetailProjection;
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
    public String getItems(Model model) {
        model.addAttribute("items", itemService.getItems());
        return "items";
    }

    /**
     * PUBLIC
     */
    @GetMapping("/collection/{collectionId}")
    public String getItemsByCollection(@PathVariable Long collectionId, Model model) {

        model.addAttribute("collection", collectionService.getCollectionById(collectionId));
        model.addAttribute("items", itemService.getItemsByCollectionId(collectionId));

        return "collection-items";
    }

    /**
     * PUBLIC
     */
    @GetMapping("/tag/{tagId}")
    public ModelAndView getItemsByTag(@PathVariable Long tagId, ModelMap model) {
        model.addAttribute("items", itemService.getItemsByTagId(tagId));
        return new ModelAndView("items", model);
    }

    @GetMapping("/{itemId}")
    public String getItemById(@PathVariable Long itemId,
                              Model model,
                              @AuthenticationPrincipal User currentUser) {
        ItemDetailProjection itemDetails = itemService.getItemDetailsById(itemId, currentUser);
        model.addAttribute("item", itemDetails);
        return "item";
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
                                @RequestBody ItemDto itemDto,
                                @AuthenticationPrincipal User currentUser,
                                RedirectAttributes redirectAttributes) {
        ApiResponse apiResponse = itemService.createItem(collectionId, itemDto, currentUser);
        redirectAttributes.addFlashAttribute("response", apiResponse);
        return "redirect:" + BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/items/collection/" + collectionId;
    }

    @DeleteMapping("/{itemId}")
    public String deleteItemById(@PathVariable Long itemId,
                                 @AuthenticationPrincipal User currentUser,
                                 RedirectAttributes redirectAttributes) {
        ApiResponse response = itemService.deleteItemById(itemId, currentUser);
        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:" + BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/items";
    }

    @GetMapping("/search")
    public String searchItems(@ModelAttribute SearchDto searchDto,
                              Model model) {
        SearchResult<Item> itemSearchResult = itemService.searchItems(searchDto);
        model.addAttribute("searchResult", itemSearchResult);
        return "search-result";
    }
}
