package com.example.itransitioncourseproject.security;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEventHandler {

    private final UserRepo userRepo;

    @EventListener
    public void authenticationSuccessEventHandler(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User principal = (User) authentication.getPrincipal();
            principal.setLoggedAt(LocalDateTime.now());
            userRepo.save(principal);
        }
    }
}
