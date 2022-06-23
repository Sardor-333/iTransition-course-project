package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.UserProjection;
import com.example.itransitioncourseproject.services.UserService;
import com.example.itransitioncourseproject.utils.AppConstants;
import com.example.itransitioncourseproject.utils.BaseUrl;
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
    public ModelAndView getUsers(@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE) Integer page,
                                 @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_SIZE) Integer size,
                                 ModelMap model) {
        model.addAttribute("users", userService.getUsers(page, size));
        return new ModelAndView("users", model);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/profile/{id}/enableOrDisable")
    public RedirectView enableOrDisableUser(@PathVariable Long id,
                                            HttpServletRequest request,
                                            @AuthenticationPrincipal User currentUser,
                                            RedirectAttributes redirectAttrs) {
        ApiResponse response = userService.enableOrDisableUser(id, request, currentUser);
        redirectAttrs.addFlashAttribute("response", response);
        return new RedirectView(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/users");
    }

    @GetMapping("/profile")
    public ModelAndView getUserProfileForUser(@AuthenticationPrincipal User currentUser, ModelMap model) {
        UserProjection user = userService.getUser(currentUser);
        model.addAttribute("user", user);
        return new ModelAndView("profile", model);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return null;
    }
}
