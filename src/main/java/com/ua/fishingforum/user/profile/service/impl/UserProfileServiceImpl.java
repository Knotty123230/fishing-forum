package com.ua.fishingforum.user.profile.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.repository.UserProfileRepository;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    @CachePut(value = "user-profiles", key = "userProfile.id")
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
    @Cacheable(value = "user-profiles", key = "#userProfileId", unless = "#result == null")
    public UserProfile findUserProfileById(Long userProfileId) {
        return userProfileRepository.findById(userProfileId).orElseThrow(() -> new CustomException("профіля користувача з айді %s не існує".formatted(userProfileId)));
    }

    @Override
    public Optional<UserProfile> findUserProfileByNickname(String nickname) {
        return userProfileRepository.findByNickname(nickname);
    }

    @Override
    @CachePut(value = "user-profiles", key = "userProfile.id")
    public UserProfile editUserProfile(UserProfile userProfile) {
        return this.userProfileRepository.save(userProfile);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return this.userProfileRepository.existsByNickname(nickname);
    }
}
