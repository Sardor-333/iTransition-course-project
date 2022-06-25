package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.ProfileDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.UserProjection;
import com.example.itransitioncourseproject.services.UserService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping
    public ModelAndView getUsers(@RequestParam(name = "page", required = false, defaultValue = PageSizeUtils.DEFAULT_PAGE) Integer page,
                                 @RequestParam(name = "size", required = false, defaultValue = PageSizeUtils.DEFAULT_SIZE) Integer size,
                                 ModelMap model) {
        model.addAttribute("users", userService.getUsers(page, size));
        return new ModelAndView("users", model);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/profile/{userId}/enableOrDisable")
    public RedirectView enableOrDisableUser(@PathVariable Long userId,
                                            HttpServletRequest request,
                                            @AuthenticationPrincipal User currentUser,
                                            RedirectAttributes redirectAttrs) {
        ApiResponse response = userService.enableOrDisableUser(userId, request, currentUser);
        redirectAttrs.addFlashAttribute("response", response);
        return new RedirectView(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/users");
    }

    @GetMapping("/profile")
    public ModelAndView getProfileInfo(@AuthenticationPrincipal User currentUser, ModelMap model) {
        UserProjection user = userService.getUser(currentUser);
        model.addAttribute("user", user);
        return new ModelAndView("profile", model);
    }

    @PostMapping("/profile")
    public RedirectView editProfile(@ModelAttribute ProfileDto profileDto,
                                    RedirectAttributes redirectAttrs,
                                    @AuthenticationPrincipal User currentUser) {
        ApiResponse apiResponse = userService.editProfile(profileDto, currentUser);
        redirectAttrs.addFlashAttribute("response", apiResponse);
        return new RedirectView(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/users/profile");
    }
}
