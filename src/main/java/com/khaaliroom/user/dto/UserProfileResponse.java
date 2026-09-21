package com.khaaliroom.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserProfileResponse(
        UUID userId,
        String profileImageUrl,
        String bio,
        String gender,
        LocalDate dateOfBirth,
        String city,
        String college,
        String occupation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}