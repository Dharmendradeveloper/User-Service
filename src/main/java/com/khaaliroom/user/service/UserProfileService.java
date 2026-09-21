package com.khaaliroom.user.service;

import com.khaaliroom.user.dto.UserProfileRequest;
import com.khaaliroom.user.dto.UserProfileResponse;
import com.khaaliroom.user.entity.UserProfile;
import com.khaaliroom.user.exception.ResourceNotFoundException;
import com.khaaliroom.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Transactional
    public UserProfileResponse createOrUpdateProfile(
            UUID userId,
            UserProfileRequest request
    ) {

        UserProfile profile = userProfileRepository
                .findById(userId)
                .orElse(
                        UserProfile.builder()
                                .userId(userId)
                                .build()
                );

        profile.setProfileImageUrl(request.profileImageUrl());
        profile.setBio(request.bio());
        profile.setGender(request.gender());
        profile.setDateOfBirth(request.dateOfBirth());
        profile.setCity(request.city());
        profile.setCollege(request.college());
        profile.setOccupation(request.occupation());

        UserProfile savedProfile =
                userProfileRepository.save(profile);

        return toResponse(savedProfile);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getProfile(UUID userId) {

        UserProfile profile = userProfileRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User profile not found")
                );

        return toResponse(profile);
    }

    private UserProfileResponse toResponse(UserProfile profile) {

        return new UserProfileResponse(
                profile.getUserId(),
                profile.getProfileImageUrl(),
                profile.getBio(),
                profile.getGender(),
                profile.getDateOfBirth(),
                profile.getCity(),
                profile.getCollege(),
                profile.getOccupation(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}