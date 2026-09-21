package com.khaaliroom.user.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserProfileRequest(

        @Size(max = 500, message = "Profile image URL must not exceed 500 characters")
        String profileImageUrl,

        @Size(max = 1000, message = "Bio must not exceed 1000 characters")
        String bio,

        @Size(max = 20, message = "Gender must not exceed 20 characters")
        String gender,

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @Size(max = 100, message = "City must not exceed 100 characters")
        String city,

        @Size(max = 200, message = "College must not exceed 200 characters")
        String college,

        @Size(max = 100, message = "Occupation must not exceed 100 characters")
        String occupation
) {}