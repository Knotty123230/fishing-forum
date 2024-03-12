package com.ua.fishingforum.user.profile.usecase;


import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;

public interface UserProfileCreateUseCase {
    void createUserProfile(UserProfileRequest userProfileRequest);
}
