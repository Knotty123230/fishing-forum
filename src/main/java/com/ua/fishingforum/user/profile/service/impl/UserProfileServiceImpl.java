package com.ua.fishingforum.user.profile.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.repository.UserProfileRepository;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfile userProfile) {
        if (userProfileRepository.existsById(userProfile.getId())) {
            String errorMessage = "Профіль користувача з таким id %s уже був створений".formatted(userProfile.getId());
            throw new CustomException(errorMessage);
        }
        if (userProfileRepository.existsByNickname(userProfile.getNickname())) {
            throw new CustomException("профіль з таким нікнеймом %s уже існує".formatted(userProfile.getNickname()));
        }
        userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile findUserProfileById(Long userProfileId) {
        return userProfileRepository.findById(userProfileId).orElseThrow(() -> new CustomException("профіля користувача з айді %s не існує".formatted(userProfileId)));
    }
}
