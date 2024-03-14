package com.ua.fishingforum.user.profile.usecase;

import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;

public interface EditUserProfileUseCase {
    UserProfileResponse editUserProfile(String nickname, UserProfileRequest userProfileRequest);
}
