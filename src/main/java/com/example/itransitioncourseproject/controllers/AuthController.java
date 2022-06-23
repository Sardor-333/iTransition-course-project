package com.example.itransitioncourseproject.controllers;

import com.example.itransitioncourseproject.payloads.request.RegisterDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.services.AuthService;
import com.example.itransitioncourseproject.utils.BaseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping(BaseUrl.API_PREFIX + BaseUrl.API_VERSION + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * GET REGISTER PAGE
     */
    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    /**
     * REGISTER USER
     */
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDto registerDto, Model model) {
        ApiResponse response = authService.register(registerDto);
        model.addAttribute("response", response);
        return "register";
    }

    /**
     * GET LOGIN PAGE
     */
    @GetMapping("/login")
    public String getLoginPage(@AuthenticationPrincipal UserDetails principal) {
        return Objects.isNull(principal) ? "login" : "redirect:/home";
    }
}
