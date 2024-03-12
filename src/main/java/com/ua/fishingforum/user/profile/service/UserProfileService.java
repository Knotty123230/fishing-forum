package com.ua.fishingforum.user.profile.service;


import com.ua.fishingforum.user.profile.model.UserProfile;

public interface UserProfileService {
    void createUserProfile(UserProfile userProfile);

    UserProfile findUserProfileById(Long userProfileId);
}
