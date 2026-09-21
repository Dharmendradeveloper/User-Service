package com.khaaliroom.user.controller;

import com.khaaliroom.user.dto.UserProfileRequest;
import com.khaaliroom.user.dto.UserProfileResponse;
import com.khaaliroom.user.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/profile")
    public ResponseEntity<UserProfileResponse> createOrUpdateProfile(
            Authentication authentication,
            @Valid @RequestBody UserProfileRequest request
    ) {

        UUID userId = UUID.fromString(
                authentication.getName()
        );

        UserProfileResponse response =
                userProfileService.createOrUpdateProfile(
                        userId,
                        request
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(
            Authentication authentication
    ) {

        UUID userId = UUID.fromString(
                authentication.getName()
        );

        UserProfileResponse response =
                userProfileService.getProfile(userId);

        return ResponseEntity.ok(response);
    }
}