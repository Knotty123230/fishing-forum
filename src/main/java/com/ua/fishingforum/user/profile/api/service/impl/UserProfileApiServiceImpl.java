package com.ua.fishingforum.user.profile.api.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.profile.api.service.UserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileApiServiceImpl implements UserProfileApiService {
    private final UserProfileService userProfileService;

    @Override
    public UserProfile findUserProfileById(Long id) {
        return this.userProfileService.findUserProfileById(id);
    }
}
