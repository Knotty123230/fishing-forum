package com.ua.fishingforum.user.profile.usecase.impl;

import com.ua.fishingforum.user.profile.mapper.UserProfileRequestToUserProfileMapper;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.usecase.UserProfileCreateUseCase;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileCreateUseCaseFacade implements UserProfileCreateUseCase {
    private final UserProfileService userProfileService;
    private final UserProfileRequestToUserProfileMapper mapper;

    @Override
    public void createUserProfile(UserProfileRequest userProfileRequest) {
        UserProfile map = mapper.map(userProfileRequest);
        userProfileService.createUserProfile(map);
    }
}
