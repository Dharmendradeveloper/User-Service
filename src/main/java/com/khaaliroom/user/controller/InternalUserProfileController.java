package com.khaaliroom.user.controller;

import com.khaaliroom.user.dto.UserProfileRequest;
import com.khaaliroom.user.dto.UserProfileResponse;
import com.khaaliroom.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/internal/users")
@RequiredArgsConstructor
public class InternalUserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<UserProfileResponse> createProfile(
            @PathVariable UUID userId
    ) {

        UserProfileRequest request =
                new UserProfileRequest(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );

        UserProfileResponse response =
                userProfileService.createOrUpdateProfile(
                        userId,
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}