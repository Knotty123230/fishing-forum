package com.ua.fishingforum.user.profile.service;


import com.ua.fishingforum.user.profile.model.UserProfile;
import org.springframework.cache.annotation.CachePut;

import java.util.Optional;

public interface UserProfileService {

    void createUserProfile(UserProfile userProfile);

    UserProfile findUserProfileById(Long userProfileId);

    Optional<UserProfile> findUserProfileByNickname(String nickname);

    UserProfile editUserProfile(UserProfile userProfile);

    boolean existsByNickname(String nickname);
}
