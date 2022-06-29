package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.payloads.request.CollectionDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import com.example.itransitioncourseproject.services.CollectionService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    /**
     * PUBLIC
     */
    @GetMapping
    public ModelAndView getCollections(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                       @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size,
                                       ModelMap model) {
        model.addAttribute("collections", collectionService.getCollections(page, size));
        return new ModelAndView("collections", model);
    }

    /**
     * AUTHENTICATED
     */
    @GetMapping("/my")
    public ModelAndView getMyCollections(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                         @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size,
                                         ModelMap model,
                                         @AuthenticationPrincipal User user) {
        Paged<CollectionProjection> myCollections = collectionService.getMyCollections(page, size, user);
        model.addAttribute("collections", myCollections);
        return new ModelAndView("my-collections", model);
    }

    /**
     * AUTHENTICATED
     */
    @GetMapping("/create")
    public String getCollectionCreatePage() {
        return "create-collection";
    }

    /**
     * AUTHENTICATED
     */
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<ApiResponse> createCollection(@RequestPart(name = "photo", required = false) MultipartFile photo,
                                                        @RequestPart(name = "collection") CollectionDto collectionDto,
                                                        @AuthenticationPrincipal User currentUser) {
        ApiResponse response = collectionService.createCollection(collectionDto, photo, currentUser);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{collectionId}")
    public RedirectView deleteCollection(@PathVariable Long collectionId, @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
        ApiResponse apiResponse = collectionService.deleteCollection(collectionId, user);
        redirectAttributes.addFlashAttribute("response", apiResponse);
        return new RedirectView(BaseUrl.API_PREFIX+BaseUrl.API_VERSION+"/collections/my");
    }
}
